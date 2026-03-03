package com.example.newreelmate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;

public class ReviewActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText reviewEditText;
    private Button submitButton;
    private ReelMateRepository repository;
    private SessionManager sessionManager;
    private int movieId;
    private String movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        repository = new ReelMateRepository(this);
        sessionManager = new SessionManager(this);

        movieId = getIntent().getIntExtra("MOVIE_ID", -1);
        movieTitle = getIntent().getStringExtra("MOVIE_TITLE");
        if (movieTitle == null) movieTitle = "Unknown Movie";

        initializeViews();
        loadExistingReview();
    }

    private void initializeViews() {
        ratingBar = findViewById(R.id.ratingBar);
        reviewEditText = findViewById(R.id.reviewEditText);
        submitButton = findViewById(R.id.submitButton);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        submitButton.setOnClickListener(v -> submitReview());
    }

    private void loadExistingReview() {
        int userId = sessionManager.getUserId();
        if (userId == -1 || movieId == -1) return;

        repository.getUserReviewForMovie(userId, movieId, review -> {
            if (review != null) {
                ratingBar.setRating(review.rating);
                reviewEditText.setText(review.comment);
                submitButton.setText("Update Review");
            }
        });
    }

    private void submitReview() {
        float rating = ratingBar.getRating();
        String reviewText = reviewEditText.getText().toString().trim();

        if (rating == 0) {
            Toast.makeText(this, "Please provide a rating", Toast.LENGTH_SHORT).show();
            return;
        }
        if (reviewText.isEmpty()) {
            Toast.makeText(this, "Please write a review", Toast.LENGTH_SHORT).show();
            return;
        }

        int userId = sessionManager.getUserId();
        if (userId == -1) {
            Toast.makeText(this, "Please login to submit a review", Toast.LENGTH_SHORT).show();
            return;
        }

        submitButton.setEnabled(false);

        repository.submitReview(userId, movieId, movieTitle, rating, reviewText, success -> {
            submitButton.setEnabled(true);
            if (success) {
                Toast.makeText(this, "Review submitted successfully!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Failed to submit review", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

