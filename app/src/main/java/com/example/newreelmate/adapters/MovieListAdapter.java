package com.example.newreelmate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newreelmate.R;
import com.example.newreelmate.models.MovieList;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ListViewHolder> {

    private Context context;
    private List<MovieList> movieLists;
    private OnListClickListener listener;

    public interface OnListClickListener {
        void onListClick(MovieList movieList);
        void onDeleteClick(MovieList movieList);
        void onShareClick(MovieList movieList);
    }

    public MovieListAdapter(Context context, List<MovieList> movieLists, OnListClickListener listener) {
        this.context = context;
        this.movieLists = movieLists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        MovieList movieList = movieLists.get(position);

        holder.nameTextView.setText(movieList.getName());
        holder.descriptionTextView.setText(movieList.getDescription());
        holder.movieCountTextView.setText(movieList.getMovieCount() + " movies");
        holder.dateTextView.setText(movieList.getCreatedDate());

        // Load preview images
        if (movieList.getPreviewImages() != null && movieList.getPreviewImages().size() > 0) {
            Glide.with(context)
                    .load(movieList.getPreviewImages().get(0))
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.preview1ImageView);

            if (movieList.getPreviewImages().size() > 1) {
                Glide.with(context)
                        .load(movieList.getPreviewImages().get(1))
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(holder.preview2ImageView);
            }

            if (movieList.getPreviewImages().size() > 2) {
                Glide.with(context)
                        .load(movieList.getPreviewImages().get(2))
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(holder.preview3ImageView);
            }
        }

        holder.itemView.setOnClickListener(v -> listener.onListClick(movieList));
        holder.deleteButton.setOnClickListener(v -> listener.onDeleteClick(movieList));
        holder.shareButton.setOnClickListener(v -> listener.onShareClick(movieList));
    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView preview1ImageView, preview2ImageView, preview3ImageView;
        TextView nameTextView, descriptionTextView, movieCountTextView, dateTextView;
        ImageButton deleteButton, shareButton;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            preview1ImageView = itemView.findViewById(R.id.preview1ImageView);
            preview2ImageView = itemView.findViewById(R.id.preview2ImageView);
            preview3ImageView = itemView.findViewById(R.id.preview3ImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            movieCountTextView = itemView.findViewById(R.id.movieCountTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            shareButton = itemView.findViewById(R.id.shareButton);
        }
    }
}

