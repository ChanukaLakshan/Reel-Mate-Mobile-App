package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView toggleAuthTextView;
    private TextView forgotPasswordTextView;
    private TextView authPromptTextView;
    private TextView titleTextView;
    private boolean isLoginMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        toggleAuthTextView = findViewById(R.id.toggleAuthTextView);
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        authPromptTextView = findViewById(R.id.authPromptTextView);
        titleTextView = findViewById(R.id.titleTextView);
    }

    private void setupListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAuth();
            }
        });

        toggleAuthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAuthMode();
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }

    private void handleAuth() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Basic validation
        if (email.isEmpty() || password.isEmpty()) {
            return;
        }

        // Navigate to Home Screen
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void toggleAuthMode() {
        isLoginMode = !isLoginMode;

        if (isLoginMode) {
            titleTextView.setText(R.string.welcome_back);
            loginButton.setText(R.string.login);
            authPromptTextView.setText(R.string.dont_have_account);
            toggleAuthTextView.setText(R.string.register);
            forgotPasswordTextView.setVisibility(View.VISIBLE);
        } else {
            titleTextView.setText(R.string.join_reelmate);
            loginButton.setText(R.string.create_account);
            authPromptTextView.setText(R.string.already_have_account);
            toggleAuthTextView.setText(R.string.login);
            forgotPasswordTextView.setVisibility(View.GONE);
        }
    }
}
