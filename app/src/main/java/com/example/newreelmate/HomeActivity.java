package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newreelmate.adapters.MovieAdapter;
import com.example.newreelmate.api.TMDBRepository;
import com.example.newreelmate.data.DataProvider;
import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;
import com.example.newreelmate.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private TextView watchlistCountTextView;
    private TMDBRepository tmdbRepository;
    private ReelMateRepository repository;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tmdbRepository = new TMDBRepository();
        repository = new ReelMateRepository(this);
        sessionManager = new SessionManager(this);

        initializeViews();
        setupRecyclerView();
        observeWatchlistCount();
        loadMoviesFromTMDB();
        BottomNavHelper.setup(this, R.id.nav_home);
    }

    private void initializeViews() {
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView);
        watchlistCountTextView = findViewById(R.id.watchlistCountTextView);
    }

    private void setupRecyclerView() {
        movieList = new ArrayList<>();
        int userId = sessionManager.getUserId();

        movieAdapter = new MovieAdapter(this, movieList, new MovieAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = new Intent(HomeActivity.this, MovieDetailsActivity.class);
                intent.putExtra("MOVIE_ID", movie.getId());
                startActivity(intent);
            }

            @Override
            public void onWatchlistClick(Movie movie) {
                if (movie.isInWatchlist()) {
                    repository.removeFromWatchlist(userId, movie.getId(), success -> {
                        movie.setInWatchlist(false);
                        movieAdapter.notifyDataSetChanged();
                    });
                } else {
                    repository.addToWatchlist(userId, movie.getId(), movie.getTitle(),
                        movie.getPoster(), movie.getYear(), movie.getRating(), success -> {
                            movie.setInWatchlist(true);
                            movieAdapter.notifyDataSetChanged();
                        });
                }
            }

            @Override
            public void onWatchedClick(Movie movie) {
                boolean newState = !movie.isWatched();
                repository.setMovieWatched(userId, movie.getId(), newState, success -> {
                    movie.setWatched(newState);
                    movieAdapter.notifyDataSetChanged();
                });
            }
        });

        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setAdapter(movieAdapter);
    }

    private void observeWatchlistCount() {
        int userId = sessionManager.getUserId();
        repository.getWatchlistCount(userId).observe(this, count -> {
            int c = count != null ? count : 0;
            watchlistCountTextView.setText(getString(R.string.movies_in_watchlist, c));
        });
    }

    private void loadMoviesFromTMDB() {
        Toast.makeText(this, "Loading movies from TMDB...", Toast.LENGTH_SHORT).show();

        tmdbRepository.getPopularMovies(1, new TMDBRepository.RepositoryCallback<List<Movie>>() {
            @Override
            public void onSuccess(List<Movie> movies) {
                movieList.clear();
                movieList.addAll(movies);
                // Sync watchlist state from DB
                syncWatchlistState();
                movieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(HomeActivity.this,
                    "Error loading movies: " + errorMessage, Toast.LENGTH_LONG).show();
                loadDemoMovies();
            }
        });
    }

    private void syncWatchlistState() {
        int userId = sessionManager.getUserId();
        for (Movie movie : movieList) {
            repository.isInWatchlist(userId, movie.getId(), isIn ->
                movie.setInWatchlist(isIn)
            );
        }
        movieAdapter.notifyDataSetChanged();
    }

    private void loadDemoMovies() {
        movieList.clear();
        movieList.addAll(DataProvider.getMovies());
        syncWatchlistState();
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        syncWatchlistState();
    }
}
