package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newreelmate.adapters.MovieAdapter;
import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;
import com.example.newreelmate.database.entities.MovieListItemEntity;
import com.example.newreelmate.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class ListDetailActivity extends AppCompatActivity {

    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private ReelMateRepository repository;
    private SessionManager sessionManager;
    private int listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        repository = new ReelMateRepository(this);
        sessionManager = new SessionManager(this);
        listId = getIntent().getIntExtra("LIST_ID", -1);

        initializeViews();
        setupRecyclerView();
        loadListDetails();
        loadMoviesInList();
    }

    private void initializeViews() {
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    private void loadListDetails() {
        if (listId == -1) return;
        repository.getListById(listId, listEntity -> {
            if (listEntity != null) {
                TextView titleView = findViewById(R.id.listTitleTextView);
                if (titleView != null) titleView.setText(listEntity.name);
            }
        });
    }

    private void setupRecyclerView() {
        movieList = new ArrayList<>();
        int userId = sessionManager.getUserId();

        movieAdapter = new MovieAdapter(this, movieList, new MovieAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = new Intent(ListDetailActivity.this, MovieDetailsActivity.class);
                intent.putExtra("MOVIE_ID", movie.getId());
                startActivity(intent);
            }

            @Override
            public void onWatchlistClick(Movie movie) {
                if (movie.isInWatchlist()) {
                    repository.removeFromWatchlist(userId, movie.getId(), s -> {
                        movie.setInWatchlist(false);
                        movieAdapter.notifyDataSetChanged();
                    });
                } else {
                    repository.addToWatchlist(userId, movie.getId(), movie.getTitle(),
                        movie.getPoster(), movie.getYear(), movie.getRating(), s -> {
                            movie.setInWatchlist(true);
                            movieAdapter.notifyDataSetChanged();
                        });
                }
            }

            @Override
            public void onWatchedClick(Movie movie) {
                boolean newState = !movie.isWatched();
                repository.setMovieWatched(userId, movie.getId(), newState, s -> {
                    movie.setWatched(newState);
                    movieAdapter.notifyDataSetChanged();
                });
            }
        });

        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setAdapter(movieAdapter);
    }

    private void loadMoviesInList() {
        if (listId == -1) return;
        repository.getMoviesInList(listId).observe(this, items -> {
            movieList.clear();
            if (items != null) {
                for (MovieListItemEntity item : items) {
                    Movie m = new Movie(item.movieId, item.movieTitle, item.moviePoster,
                        "Unknown", item.movieYear, item.movieRating);
                    movieList.add(m);
                }
            }
            movieAdapter.notifyDataSetChanged();
        });
    }
}
