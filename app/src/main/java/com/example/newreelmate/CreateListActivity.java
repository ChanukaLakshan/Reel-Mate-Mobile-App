package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateListActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        nameEditText = findViewById(R.id.listNameEditText);
        descriptionEditText = findViewById(R.id.listDescriptionEditText);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button saveButton = findViewById(R.id.saveListButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveList();
            }
        });
    }

    private void saveList() {
        String name = nameEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter a list name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent result = new Intent();
        result.putExtra("LIST_NAME", name);
        result.putExtra("LIST_DESCRIPTION", description);
        setResult(RESULT_OK, result);
        finish();
    }
}

