package com.example.newreelmate.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "watchlist",
    foreignKeys = @ForeignKey(
        entity = UserEntity.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = ForeignKey.CASCADE
    ),
    indices = {
        @Index(value = {"userId", "movieId"}, unique = true)
    }
)
public class WatchlistEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;
    public int movieId;
    public String movieTitle;
    public String moviePoster;
    public String movieYear;
    public double movieRating;
    public boolean isWatched;
    public long addedAt;

    public WatchlistEntity(int userId, int movieId, String movieTitle, String moviePoster,
                           String movieYear, double movieRating) {
        this.userId = userId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.moviePoster = moviePoster;
        this.movieYear = movieYear;
        this.movieRating = movieRating;
        this.isWatched = false;
        this.addedAt = System.currentTimeMillis();
    }
}

