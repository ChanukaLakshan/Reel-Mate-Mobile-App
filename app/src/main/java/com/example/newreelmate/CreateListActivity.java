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

public class CreateListActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText descriptionEditText;
    private ReelMateRepository repository;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        repository = new ReelMateRepository(this);
        sessionManager = new SessionManager(this);

        nameEditText = findViewById(R.id.listNameEditText);
        descriptionEditText = findViewById(R.id.listDescriptionEditText);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        Button saveButton = findViewById(R.id.saveListButton);
        saveButton.setOnClickListener(v -> saveList());
    }

    private void saveList() {
        String name = nameEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter a list name", Toast.LENGTH_SHORT).show();
            return;
        }

        int userId = sessionManager.getUserId();
        repository.createMovieList(userId, name, description, id -> {
            if (id > 0) {
                // Add notification for list creation
                repository.addNotification(
                    userId,
                    "list",
                    "New list created! 📋",
                    "Your list \"" + name + "\" has been created successfully."
                );
                Intent result = new Intent();
                result.putExtra("LIST_NAME", name);
                result.putExtra("LIST_DESCRIPTION", description);
                setResult(RESULT_OK, result);
                Toast.makeText(this, "List created!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to create list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

