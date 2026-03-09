package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;

public class ProfileActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> editProfileLauncher;
    private TextView userNameTextView;
    private TextView userEmailTextView;
    private SessionManager sessionManager;
    private ReelMateRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(this);
        repository = new ReelMateRepository(this);


        userNameTextView = findViewById(R.id.userNameTextView);
        userEmailTextView = findViewById(R.id.userEmailTextView);

        // Load user from DB
        loadUserProfile();

        editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String name = result.getData().getStringExtra("PROFILE_NAME");
                        String email = result.getData().getStringExtra("PROFILE_EMAIL");
                        if (name != null && !name.trim().isEmpty()) {
                            userNameTextView.setText(name.trim());
                            sessionManager.updateName(name.trim());
                        }
                        if (email != null && !email.trim().isEmpty()) {
                            userEmailTextView.setText(email.trim());
                            sessionManager.updateEmail(email.trim());
                        }
                    }
                }
            }
        );

        Button editProfileButton = findViewById(R.id.editProfileButton);
        Button settingsButton = findViewById(R.id.settingsButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            intent.putExtra("PROFILE_NAME", userNameTextView.getText().toString());
            intent.putExtra("PROFILE_EMAIL", userEmailTextView.getText().toString());
            editProfileLauncher.launch(intent);
        });

        settingsButton.setOnClickListener(v ->
            startActivity(new Intent(ProfileActivity.this, SettingsActivity.class))
        );

        logoutButton.setOnClickListener(v -> {
            sessionManager.clearSession();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

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
                } else {
                    userNameTextView.setText(sessionManager.getUserName());
                    userEmailTextView.setText(sessionManager.getUserEmail());
                }
            });
        } else {
            userNameTextView.setText(sessionManager.getUserName());
            userEmailTextView.setText(sessionManager.getUserEmail());
        }
    }
}
