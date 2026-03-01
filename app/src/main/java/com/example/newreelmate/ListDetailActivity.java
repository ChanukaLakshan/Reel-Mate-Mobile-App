package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newreelmate.adapters.MovieAdapter;
import com.example.newreelmate.data.DataProvider;
import com.example.newreelmate.models.Movie;

import java.util.List;

public class ListDetailActivity extends AppCompatActivity {

    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        initializeViews();
        setupRecyclerView();
    }

    private void initializeViews() {
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupRecyclerView() {
        List<Movie> movies = DataProvider.getMovies();
        movieAdapter = new MovieAdapter(this, movies, new MovieAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = new Intent(ListDetailActivity.this, MovieDetailsActivity.class);
                intent.putExtra("MOVIE_ID", movie.getId());
                startActivity(intent);
            }

            @Override
            public void onWatchlistClick(Movie movie) {
                movie.setInWatchlist(!movie.isInWatchlist());
                movieAdapter.notifyDataSetChanged();
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
}
