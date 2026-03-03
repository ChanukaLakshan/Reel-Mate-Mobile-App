package com.example.newreelmate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newreelmate.database.ReelMateRepository;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText newPasswordEditText;
    private Button sendButton;
    private ReelMateRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        repository = new ReelMateRepository(this);

        emailEditText = findViewById(R.id.resetEmailEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        sendButton = findViewById(R.id.sendResetButton);
        sendButton.setOnClickListener(v -> handleReset());
    }

    private void handleReset() {
        String email = emailEditText.getText().toString().trim();
        String newPassword = newPasswordEditText != null
                ? newPasswordEditText.getText().toString().trim() : "";

        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (newPassword.isEmpty()) {
            Toast.makeText(this, "Please enter a new password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (newPassword.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        sendButton.setEnabled(false);

        repository.resetPassword(email, newPassword, success -> {
            sendButton.setEnabled(true);
            if (success) {
                Toast.makeText(this, "Password reset successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "No account found with that email", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

