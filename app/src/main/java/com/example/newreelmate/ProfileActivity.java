package com.example.newreelmate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;

public class ProfileActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> editProfileLauncher;
    private ActivityResultLauncher<String> pickImageLauncher;
    private TextView userNameTextView;
    private TextView userEmailTextView;
    private TextView moviesWatchedTextView;
    private TextView listsCreatedTextView;
    private TextView reviewsWrittenTextView;
    private ImageView profileImageView;
    private SessionManager sessionManager;
    private ReelMateRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(this);
        repository = new ReelMateRepository(this);

        userNameTextView      = findViewById(R.id.userNameTextView);
        userEmailTextView     = findViewById(R.id.userEmailTextView);
        profileImageView      = findViewById(R.id.profileImageView);
        moviesWatchedTextView = findViewById(R.id.moviesWatchedTextView);
        listsCreatedTextView  = findViewById(R.id.listsCreatedTextView);
        reviewsWrittenTextView = findViewById(R.id.reviewsWrittenTextView);

        // Load user profile from DB
        loadUserProfile();

        // Observe stats – auto-updates whenever DB changes
        loadProfileStats();

        // Image picker launcher
        pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    // Persist read permission across app restarts
                    getContentResolver().takePersistableUriPermission(
                        uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    String uriString = uri.toString();
                    profileImageView.setImageURI(uri);
                    sessionManager.updateProfilePhotoUri(uriString);
                    int userId = sessionManager.getUserId();
                    repository.updateProfilePhoto(userId, uriString, success -> {
                        if (!success) {
                            Toast.makeText(this, "Failed to save photo", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        );

        editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String name     = result.getData().getStringExtra("PROFILE_NAME");
                    String email    = result.getData().getStringExtra("PROFILE_EMAIL");
                    String photoUri = result.getData().getStringExtra("PROFILE_PHOTO_URI");
                    if (name != null && !name.trim().isEmpty()) {
                        userNameTextView.setText(name.trim());
                        sessionManager.updateName(name.trim());
                    }
                    if (email != null && !email.trim().isEmpty()) {
                        userEmailTextView.setText(email.trim());
                        sessionManager.updateEmail(email.trim());
                    }
                    if (photoUri != null && !photoUri.isEmpty()) {
                        profileImageView.setImageURI(Uri.parse(photoUri));
                        sessionManager.updateProfilePhotoUri(photoUri);
                    }
                }
            }
        );

        // Tap profile image or camera badge to pick a new photo
        profileImageView.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
        ImageView changePhotoButton = findViewById(R.id.changePhotoButton);
        changePhotoButton.setOnClickListener(v -> pickImageLauncher.launch("image/*"));

        Button editProfileButton  = findViewById(R.id.editProfileButton);
        Button settingsButton     = findViewById(R.id.settingsButton);
        Button changeGenresButton = findViewById(R.id.changeGenresButton);
        Button logoutButton       = findViewById(R.id.logoutButton);

        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            intent.putExtra("PROFILE_NAME",  userNameTextView.getText().toString());
            intent.putExtra("PROFILE_EMAIL", userEmailTextView.getText().toString());
            String photoUri = sessionManager.getProfilePhotoUri();
            if (photoUri != null) intent.putExtra("PROFILE_PHOTO_URI", photoUri);
            editProfileLauncher.launch(intent);
        });

        settingsButton.setOnClickListener(v ->
            startActivity(new Intent(ProfileActivity.this, SettingsActivity.class))
        );

        changeGenresButton.setOnClickListener(v -> {
            int userId = sessionManager.getUserId();
            repository.getFavoriteGenres(userId, genres -> {
                Intent intent = new Intent(ProfileActivity.this, GenreSelectionActivity.class);
                intent.putExtra(GenreSelectionActivity.EXTRA_USER_ID, userId);
                if (genres != null && !genres.isEmpty()) {
                    intent.putStringArrayListExtra(
                        GenreSelectionActivity.EXTRA_EXISTING_GENRES,
                        new java.util.ArrayList<>(genres));
                }
                startActivity(intent);
            });
        });

        logoutButton.setOnClickListener(v -> {
            sessionManager.clearSession();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        BottomNavHelper.setup(this, R.id.nav_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavHelper.setup(this, R.id.nav_profile);
    }

    private void loadUserProfile() {
        int userId = sessionManager.getUserId();
        if (userId != -1) {
            repository.getUserById(userId, user -> {
                if (user != null) {
                    userNameTextView.setText(user.name);
                    userEmailTextView.setText(user.email);
                    sessionManager.updateName(user.name);
                    sessionManager.updateEmail(user.email);
                    if (user.profilePhotoUri != null && !user.profilePhotoUri.isEmpty()) {
                        sessionManager.updateProfilePhotoUri(user.profilePhotoUri);
                        profileImageView.setImageURI(Uri.parse(user.profilePhotoUri));
                    }
                } else {
                    userNameTextView.setText(sessionManager.getUserName());
                    userEmailTextView.setText(sessionManager.getUserEmail());
                    String cachedUri = sessionManager.getProfilePhotoUri();
                    if (cachedUri != null) profileImageView.setImageURI(Uri.parse(cachedUri));
                }
            });
        } else {
            userNameTextView.setText(sessionManager.getUserName());
            userEmailTextView.setText(sessionManager.getUserEmail());
            String cachedUri = sessionManager.getProfilePhotoUri();
            if (cachedUri != null) profileImageView.setImageURI(Uri.parse(cachedUri));
        }
    }

    private void loadProfileStats() {
        int userId = sessionManager.getUserId();
        if (userId == -1) return;

        repository.getMoviesWatchedCount(userId)
            .observe(this, count ->
                moviesWatchedTextView.setText(String.valueOf(count != null ? count : 0)));

        repository.getListsCount(userId)
            .observe(this, count ->
                listsCreatedTextView.setText(String.valueOf(count != null ? count : 0)));

        repository.getReviewsCount(userId)
            .observe(this, count ->
                reviewsWrittenTextView.setText(String.valueOf(count != null ? count : 0)));
    }
}
