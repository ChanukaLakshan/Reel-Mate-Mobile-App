package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;

public class EditProfileActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private ReelMateRepository repository;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        repository = new ReelMateRepository(this);
        sessionManager = new SessionManager(this);

        nameEditText = findViewById(R.id.profileNameEditText);
        emailEditText = findViewById(R.id.profileEmailEditText);

        String currentName = getIntent().getStringExtra("PROFILE_NAME");
        String currentEmail = getIntent().getStringExtra("PROFILE_EMAIL");
        if (currentName != null) nameEditText.setText(currentName);
        if (currentEmail != null) emailEditText.setText(currentEmail);

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
                Intent result = new Intent();
                result.putExtra("PROFILE_NAME", name);
                result.putExtra("PROFILE_EMAIL", email);
                setResult(RESULT_OK, result);
                Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

