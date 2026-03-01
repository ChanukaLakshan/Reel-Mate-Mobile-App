package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newreelmate.adapters.MovieAdapter;
import com.example.newreelmate.api.TMDBRepository;
import com.example.newreelmate.data.DataProvider;
import com.example.newreelmate.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private TextView watchlistCountTextView;
    private ImageButton profileButton;
    private ImageButton listsButton;
    private ImageButton notificationsButton;
    private TMDBRepository tmdbRepository;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize TMDB Repository
        tmdbRepository = new TMDBRepository();

        initializeViews();
        setupRecyclerView();
        loadMoviesFromTMDB();
    }

    private void initializeViews() {
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView);
        watchlistCountTextView = findViewById(R.id.watchlistCountTextView);
        profileButton = findViewById(R.id.profileButton);
        listsButton = findViewById(R.id.listsButton);
        notificationsButton = findViewById(R.id.notificationsButton);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

        listsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MyListsActivity.class));
            }
        });

        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NotificationsActivity.class));
            }
        });
    }

    private void setupRecyclerView() {
        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movieList, new MovieAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = new Intent(HomeActivity.this, MovieDetailsActivity.class);
                intent.putExtra("MOVIE_ID", movie.getId());
                startActivity(intent);
            }

            @Override
            public void onWatchlistClick(Movie movie) {
                movie.setInWatchlist(!movie.isInWatchlist());
                movieAdapter.notifyDataSetChanged();
                updateWatchlistCount();
            }

            @Override
            public void onWatchedClick(Movie movie) {
                movie.setWatched(!movie.isWatched());
                movieAdapter.notifyDataSetChanged();
            }
        });

        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setAdapter(movieAdapter);
    }

    private void loadMoviesFromTMDB() {
        // Show loading indicator
        Toast.makeText(this, "Loading movies from TMDB...", Toast.LENGTH_SHORT).show();

        // Fetch real TMDB data
        tmdbRepository.getPopularMovies(1, new TMDBRepository.RepositoryCallback<List<Movie>>() {
            @Override
            public void onSuccess(List<Movie> movies) {
                movieList.clear();
                movieList.addAll(movies);
                movieAdapter.notifyDataSetChanged();
                updateWatchlistCount();
                Toast.makeText(HomeActivity.this,
                    "Loaded " + movies.size() + " movies from TMDB",
                    Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(HomeActivity.this,
                    "Error loading movies: " + errorMessage,
                    Toast.LENGTH_LONG).show();

                // Fallback to demo data if API fails
                loadDemoMovies();
            }
        });
    }

    private void loadDemoMovies() {
        movieList.clear();
        movieList.addAll(DataProvider.getMovies());
        movieAdapter.notifyDataSetChanged();
        updateWatchlistCount();
    }

    private void loadMovies() {
        // Legacy method - kept for compatibility
        loadDemoMovies();
    }

    private void updateWatchlistCount() {
        int count = 0;
        for (Movie movie : movieList) {
            if (movie.isInWatchlist()) {
                count++;
            }
        }
        watchlistCountTextView.setText(getString(R.string.movies_in_watchlist, count));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateWatchlistCount();
    }
}
