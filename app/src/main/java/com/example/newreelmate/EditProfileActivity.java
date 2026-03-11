package com.example.newreelmate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;

public class EditProfileActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private ImageView editProfileImageView;
    private ReelMateRepository repository;
    private SessionManager sessionManager;
    private String currentPhotoUri;
    private ActivityResultLauncher<String> pickImageLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        repository = new ReelMateRepository(this);
        sessionManager = new SessionManager(this);

        nameEditText = findViewById(R.id.profileNameEditText);
        emailEditText = findViewById(R.id.profileEmailEditText);
        editProfileImageView = findViewById(R.id.editProfileImageView);

        String currentName = getIntent().getStringExtra("PROFILE_NAME");
        String currentEmail = getIntent().getStringExtra("PROFILE_EMAIL");
        currentPhotoUri = getIntent().getStringExtra("PROFILE_PHOTO_URI");

        if (currentName != null) nameEditText.setText(currentName);
        if (currentEmail != null) emailEditText.setText(currentEmail);
        if (currentPhotoUri != null && !currentPhotoUri.isEmpty()) {
            editProfileImageView.setImageURI(Uri.parse(currentPhotoUri));
        }

        // Image picker launcher
        pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    getContentResolver().takePersistableUriPermission(
                        uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    currentPhotoUri = uri.toString();
                    editProfileImageView.setImageURI(uri);
                }
            }
        );

        editProfileImageView.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
        ImageView editChangePhotoButton = findViewById(R.id.editChangePhotoButton);
        editChangePhotoButton.setOnClickListener(v -> pickImageLauncher.launch("image/*"));

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        Button saveButton = findViewById(R.id.saveProfileButton);
        saveButton.setOnClickListener(v -> saveProfile());
    }

    private void saveProfile() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter name and email", Toast.LENGTH_SHORT).show();
            return;
        }

        int userId = sessionManager.getUserId();
        repository.updateProfile(userId, name, email, success -> {
            if (success) {
                sessionManager.updateName(name);
                sessionManager.updateEmail(email);

                // Save photo URI if changed
                if (currentPhotoUri != null && !currentPhotoUri.isEmpty()) {
                    sessionManager.updateProfilePhotoUri(currentPhotoUri);
                    repository.updateProfilePhoto(userId, currentPhotoUri, photoSuccess -> {});
                }

                Intent result = new Intent();
                result.putExtra("PROFILE_NAME", name);
                result.putExtra("PROFILE_EMAIL", email);
                if (currentPhotoUri != null) result.putExtra("PROFILE_PHOTO_URI", currentPhotoUri);
                setResult(RESULT_OK, result);
                Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
