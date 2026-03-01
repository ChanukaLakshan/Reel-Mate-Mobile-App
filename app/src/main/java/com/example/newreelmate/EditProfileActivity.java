package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nameEditText = findViewById(R.id.profileNameEditText);
        emailEditText = findViewById(R.id.profileEmailEditText);

        String currentName = getIntent().getStringExtra("PROFILE_NAME");
        String currentEmail = getIntent().getStringExtra("PROFILE_EMAIL");
        if (currentName != null) {
            nameEditText.setText(currentName);
        }
        if (currentEmail != null) {
            emailEditText.setText(currentEmail);
        }

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button saveButton = findViewById(R.id.saveProfileButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });
    }

    private void saveProfile() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter name and email", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent result = new Intent();
        result.putExtra("PROFILE_NAME", name);
        result.putExtra("PROFILE_EMAIL", email);
        setResult(RESULT_OK, result);
        finish();
    }
}

