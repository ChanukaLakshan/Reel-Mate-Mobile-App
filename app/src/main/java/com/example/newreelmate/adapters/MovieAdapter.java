package com.example.newreelmate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newreelmate.R;
import com.example.newreelmate.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private OnMovieClickListener listener;

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
        void onWatchlistClick(Movie movie);
        void onWatchedClick(Movie movie);
    }

    public MovieAdapter(Context context, List<Movie> movieList, OnMovieClickListener listener) {
        this.context = context;
        this.movieList = movieList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        // Display real TMDB film details
        holder.titleTextView.setText(movie.getTitle());

        // Display genres (from TMDB API)
        if (movie.getGenres() != null && !movie.getGenres().isEmpty()) {
            holder.genreTextView.setText(String.join(", ", movie.getGenres()));
        } else {
            holder.genreTextView.setText(movie.getGenre() != null ? movie.getGenre() : "N/A");
        }

        // Display year
        holder.yearTextView.setText(movie.getYear());

        // Display rating from TMDB
        holder.ratingTextView.setText(String.format("★ %.1f/10", movie.getRating()));

        // Load poster image using Glide from TMDB URL
        Glide.with(context)
                .load(movie.getPoster())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.posterImageView);

        // Set watchlist button state
        if (movie.isInWatchlist()) {
            holder.watchlistButton.setText(R.string.listed);
            holder.watchlistButton.setBackgroundResource(R.drawable.button_secondary);
        } else {
            holder.watchlistButton.setText(R.string.add_to_list);
            holder.watchlistButton.setBackgroundResource(R.drawable.button_primary);
        }

        // Set watched button state
        if (movie.isWatched()) {
            holder.watchedButton.setText(R.string.watched);
            holder.watchedButton.setBackgroundResource(R.drawable.button_success);
        } else {
            holder.watchedButton.setText(R.string.mark_watched);
            holder.watchedButton.setBackgroundResource(R.drawable.button_secondary);
        }

        // Click listeners
        holder.itemView.setOnClickListener(v -> listener.onMovieClick(movie));
        holder.posterImageView.setOnClickListener(v -> listener.onMovieClick(movie));
        holder.titleTextView.setOnClickListener(v -> listener.onMovieClick(movie));
        holder.watchlistButton.setOnClickListener(v -> listener.onWatchlistClick(movie));
        holder.watchedButton.setOnClickListener(v -> listener.onWatchedClick(movie));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;
        TextView titleTextView;
        TextView genreTextView;
        TextView yearTextView;
        TextView ratingTextView;
        Button watchlistButton;
        Button watchedButton;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            genreTextView = itemView.findViewById(R.id.genreTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            watchlistButton = itemView.findViewById(R.id.watchlistButton);
            watchedButton = itemView.findViewById(R.id.watchedButton);
        }
    }
}

