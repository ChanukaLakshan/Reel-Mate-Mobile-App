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
import com.example.newreelmate.models.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context context;
    private List<Review> reviewList;

    public ReviewAdapter(Context context, List<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);

        holder.userNameTextView.setText(review.getUserName());
        holder.commentTextView.setText(review.getComment());
        holder.dateTextView.setText(review.getDate());
        holder.likesTextView.setText(String.valueOf(review.getLikes()));

        // Display rating stars
        holder.ratingTextView.setText(getStars(review.getRating()));

        // Load user avatar
        Glide.with(context)
                .load(review.getUserAvatar())
                .placeholder(R.drawable.ic_launcher_background)
                .circleCrop()
                .into(holder.avatarImageView);

        // Like button
        holder.likeButton.setOnClickListener(v -> {
            review.setLiked(!review.isLiked());
            if (review.isLiked()) {
                review.setLikes(review.getLikes() + 1);
            } else {
                review.setLikes(review.getLikes() - 1);
            }
            holder.likesTextView.setText(String.valueOf(review.getLikes()));
        });
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    private String getStars(int rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                stars.append("★");
            } else {
                stars.append("☆");
            }
        }
        return stars.toString();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView userNameTextView;
        TextView dateTextView;
        TextView ratingTextView;
        TextView commentTextView;
        ImageButton likeButton;
        TextView likesTextView;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            commentTextView = itemView.findViewById(R.id.commentTextView);
            likeButton = itemView.findViewById(R.id.likeButton);
            likesTextView = itemView.findViewById(R.id.likesTextView);
        }
    }
}

