package com.example.newreelmate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newreelmate.R;
import com.example.newreelmate.database.entities.WatchlistEntity;

import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.ViewHolder> {

    private final Context context;
    private final List<WatchlistEntity> items;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(WatchlistEntity item);
        void onRemoveClick(WatchlistEntity item);
    }

    public WatchlistAdapter(Context context, List<WatchlistEntity> items, OnItemClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_watchlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WatchlistEntity item = items.get(position);

        holder.titleTextView.setText(item.movieTitle);
        holder.yearTextView.setText(item.movieYear != null ? item.movieYear : "");
        holder.ratingTextView.setText(String.format(java.util.Locale.US, "★ %.1f", item.movieRating));
        holder.watchedBadge.setVisibility(item.isWatched ? View.VISIBLE : View.GONE);

        Glide.with(context)
                .load(item.moviePoster)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.posterImageView);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
        holder.removeButton.setOnClickListener(v -> listener.onRemoveClick(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;
        TextView titleTextView;
        TextView yearTextView;
        TextView ratingTextView;
        TextView watchedBadge;
        View removeButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView   = itemView.findViewById(R.id.titleTextView);
            yearTextView    = itemView.findViewById(R.id.yearTextView);
            ratingTextView  = itemView.findViewById(R.id.ratingTextView);
            watchedBadge    = itemView.findViewById(R.id.watchedBadge);
            removeButton    = itemView.findViewById(R.id.removeButton);
        }
    }
}

