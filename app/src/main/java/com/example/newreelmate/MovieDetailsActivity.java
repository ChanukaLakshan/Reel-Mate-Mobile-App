package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newreelmate.adapters.ReviewAdapter;
import com.example.newreelmate.api.TMDBRepository;
import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;
import com.example.newreelmate.models.Movie;
import com.example.newreelmate.models.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    private ProgressBar progressBar;
    private View contentLayout;

    private Movie currentMovie;
    private boolean inWatchlist = false;
    private int movieId;

    private TMDBRepository tmdbRepository;
    private ReelMateRepository repository;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tmdbRepository = new TMDBRepository();
        repository = new ReelMateRepository(this);
        sessionManager = new SessionManager(this);

        movieId = getIntent().getIntExtra("MOVIE_ID", -1);

        initializeViews();
        setupReviews();

        if (movieId != -1) {
            fetchMovieDetails(movieId);
        } else {
            Toast.makeText(this, "Invalid movie ID", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initializeViews() {
        posterImageView    = findViewById(R.id.posterImageView);
        titleTextView      = findViewById(R.id.titleTextView);
        ratingTextView     = findViewById(R.id.ratingTextView);
        yearTextView       = findViewById(R.id.yearTextView);
        runtimeTextView    = findViewById(R.id.runtimeTextView);
        synopsisTextView   = findViewById(R.id.synopsisTextView);
        directorTextView   = findViewById(R.id.directorTextView);
        castTextView       = findViewById(R.id.castTextView);
        watchlistButton    = findViewById(R.id.watchlistButton);
        reviewButton       = findViewById(R.id.reviewButton);
        reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);
        progressBar        = findViewById(R.id.detailsProgressBar);
        contentLayout      = findViewById(R.id.detailsContentLayout);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        watchlistButton.setOnClickListener(v -> toggleWatchlist());
        reviewButton.setOnClickListener(v -> {
            if (currentMovie == null) return;
            Intent intent = new Intent(MovieDetailsActivity.this, ReviewActivity.class);
            intent.putExtra("MOVIE_ID", currentMovie.getId());
            intent.putExtra("MOVIE_TITLE", currentMovie.getTitle());
            startActivity(intent);
        });
    }

    private void fetchMovieDetails(int id) {
        showLoading(true);
        tmdbRepository.getMovieDetails(id, new TMDBRepository.RepositoryCallback<Movie>() {
            @Override
            public void onSuccess(Movie movie) {
                currentMovie = movie;
                showLoading(false);
                populateUI(movie);
                checkWatchlistStatus();
            }

            @Override
            public void onError(String errorMessage) {
                showLoading(false);
                Toast.makeText(MovieDetailsActivity.this,
                        "Failed to load movie details: " + errorMessage,
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void populateUI(Movie movie) {
        titleTextView.setText(movie.getTitle());
        ratingTextView.setText(String.format(Locale.US, "★ %.1f", movie.getRating()));
        yearTextView.setText(movie.getYear() != null ? movie.getYear() : "");
        runtimeTextView.setText(movie.getRuntime() != null && !movie.getRuntime().isEmpty()
                ? movie.getRuntime() : "");

        String synopsis = movie.getDescription();
        synopsisTextView.setText((synopsis != null && !synopsis.isEmpty())
                ? synopsis : "No synopsis available.");

        String director = movie.getDirector();
        directorTextView.setText((director != null && !director.isEmpty())
                ? director : "N/A");

        List<String> cast = movie.getCast();
        if (cast != null && !cast.isEmpty()) {
            // Show top 6 cast members
            int limit = Math.min(cast.size(), 6);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < limit; i++) {
                sb.append(cast.get(i));
                if (i < limit - 1) sb.append(", ");
            }
            castTextView.setText(sb.toString());
        } else {
            castTextView.setText("N/A");
        }

        // Load poster with Glide
        Glide.with(this)
                .load(movie.getPoster())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(posterImageView);
    }

    private void checkWatchlistStatus() {
        if (currentMovie == null) return;
        int userId = sessionManager.getUserId();
        repository.isInWatchlist(userId, currentMovie.getId(), isIn -> {
            inWatchlist = isIn;
            updateWatchlistButton();
        });
    }

    private void setupReviews() {
        List<Review> reviews = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(this, reviews);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecyclerView.setAdapter(reviewAdapter);

        // Observe DB reviews for this movie
        int userId = sessionManager.getUserId();
        repository.getReviewsForMovie(movieId).observe(this, reviewEntities -> {
            reviews.clear();
            if (reviewEntities != null) {
                for (com.example.newreelmate.database.entities.ReviewEntity re : reviewEntities) {
                    reviews.add(new Review(
                            re.id,
                            "User " + re.userId,   // userName
                            "",                     // userAvatar
                            (int) re.rating,        // rating
                            re.comment,             // comment
                            0,                      // likes
                            ""                      // date
                    ));
                }
            }
            reviewAdapter.notifyDataSetChanged();
        });
    }

    private void toggleWatchlist() {
        if (currentMovie == null) return;
        int userId = sessionManager.getUserId();
        if (inWatchlist) {
            repository.removeFromWatchlist(userId, currentMovie.getId(), success -> {
                inWatchlist = false;
                updateWatchlistButton();
            });
        } else {
            repository.addToWatchlist(userId, currentMovie.getId(), currentMovie.getTitle(),
                currentMovie.getPoster(), currentMovie.getYear(), currentMovie.getRating(), success -> {
                    inWatchlist = true;
                    updateWatchlistButton();
                });
        }
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

    private void showLoading(boolean loading) {
        if (progressBar != null)
            progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        if (contentLayout != null)
            contentLayout.setVisibility(loading ? View.GONE : View.VISIBLE);
    }
}
