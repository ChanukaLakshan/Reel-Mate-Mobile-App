package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newreelmate.adapters.ReviewAdapter;
import com.example.newreelmate.data.DataProvider;
import com.example.newreelmate.models.Movie;
import com.example.newreelmate.models.Review;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView ratingTextView;
    private TextView yearTextView;
    private TextView runtimeTextView;
    private TextView synopsisTextView;
    private TextView directorTextView;
    private TextView castTextView;
    private Button watchlistButton;
    private Button reviewButton;
    private RecyclerView reviewsRecyclerView;
    private ReviewAdapter reviewAdapter;

    private Movie movie;
    private boolean inWatchlist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initializeViews();
        loadMovieData();
        setupReviews();
    }

    private void initializeViews() {
        posterImageView = findViewById(R.id.posterImageView);
        titleTextView = findViewById(R.id.titleTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        yearTextView = findViewById(R.id.yearTextView);
        runtimeTextView = findViewById(R.id.runtimeTextView);
        synopsisTextView = findViewById(R.id.synopsisTextView);
        directorTextView = findViewById(R.id.directorTextView);
        castTextView = findViewById(R.id.castTextView);
        watchlistButton = findViewById(R.id.watchlistButton);
        reviewButton = findViewById(R.id.reviewButton);
        reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        watchlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWatchlist();
            }
        });

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailsActivity.this, ReviewActivity.class);
                intent.putExtra("MOVIE_ID", movie.getId());
                startActivity(intent);
            }
        });
    }

    private void loadMovieData() {
        int movieId = getIntent().getIntExtra("MOVIE_ID", 1);
        movie = DataProvider.getMovieById(movieId);

        titleTextView.setText(movie.getTitle());
        ratingTextView.setText(String.format("★ %.1f", movie.getRating()));
        yearTextView.setText(movie.getYear());
        runtimeTextView.setText(movie.getRuntime());
        synopsisTextView.setText(movie.getDescription());
        directorTextView.setText(movie.getDirector());

        // Display cast
        if (movie.getCast() != null) {
            StringBuilder castBuilder = new StringBuilder();
            for (int i = 0; i < movie.getCast().size(); i++) {
                castBuilder.append(movie.getCast().get(i));
                if (i < movie.getCast().size() - 1) {
                    castBuilder.append(", ");
                }
            }
            castTextView.setText(castBuilder.toString());
        }

        // Load poster
        Glide.with(this)
                .load(movie.getPoster())
                .placeholder(R.drawable.ic_launcher_background)
                .into(posterImageView);

        updateWatchlistButton();
    }

    private void setupReviews() {
        List<Review> reviews = DataProvider.getReviews();
        reviewAdapter = new ReviewAdapter(this, reviews);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecyclerView.setAdapter(reviewAdapter);
    }

    private void toggleWatchlist() {
        inWatchlist = !inWatchlist;
        updateWatchlistButton();
    }

    private void updateWatchlistButton() {
        if (inWatchlist) {
            watchlistButton.setText(R.string.in_watchlist);
            watchlistButton.setBackgroundResource(R.drawable.button_secondary);
        } else {
            watchlistButton.setText(R.string.add_to_watchlist);
            watchlistButton.setBackgroundResource(R.drawable.button_primary);
        }
    }
}

