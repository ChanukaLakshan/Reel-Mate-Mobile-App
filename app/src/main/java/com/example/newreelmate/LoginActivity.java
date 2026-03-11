package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView toggleAuthTextView;
    private TextView forgotPasswordTextView;
    private TextView authPromptTextView;
    private TextView titleTextView;
    private boolean isLoginMode = true;

    private ReelMateRepository repository;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        repository = new ReelMateRepository(this);
        sessionManager = new SessionManager(this);

        // If already logged in, go straight to Home
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
            return;
        }

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
        loginButton.setOnClickListener(v -> handleAuth());

        toggleAuthTextView.setOnClickListener(v -> toggleAuthMode());

        forgotPasswordTextView.setOnClickListener(v ->
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class))
        );
    }

    private void handleAuth() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        loginButton.setEnabled(false);

        if (isLoginMode) {
            repository.loginUser(email, password, user -> {
                loginButton.setEnabled(true);
                if (user != null) {
                    sessionManager.saveSession(user.id, user.name, user.email);
                    // Welcome notification
                    repository.addNotification(
                        user.id,
                        "welcome",
                        "Welcome back, " + user.name + "! 👋",
                        "You have successfully logged in to ReelMate."
                    );
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Register mode — need name field too; use email prefix as name if not available
            String name = email.split("@")[0];
            if (password.length() < 6) {
                loginButton.setEnabled(true);
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }
            repository.registerUserAndGetId(name, email, password, userId -> {
                loginButton.setEnabled(true);
                if (userId != null && userId > 0) {
                    // Save session immediately
                    sessionManager.saveSession(userId, name, email);
                    // Welcome notification for new user
                    repository.addNotification(
                        userId,
                        "welcome",
                        "Welcome to ReelMate, " + name + "! 🎬",
                        "Your account has been created. Start exploring movies!"
                    );
                    // Navigate to genre selection
                    Intent intent = new Intent(LoginActivity.this, GenreSelectionActivity.class);
                    intent.putExtra(GenreSelectionActivity.EXTRA_USER_ID, userId);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
