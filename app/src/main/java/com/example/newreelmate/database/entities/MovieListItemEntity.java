package com.example.newreelmate.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "movie_list_items",
    foreignKeys = @ForeignKey(
        entity = MovieListEntity.class,
        parentColumns = "id",
        childColumns = "listId",
        onDelete = ForeignKey.CASCADE
    ),
    indices = {
        @Index(value = {"listId", "movieId"}, unique = true)
    }
)
public class MovieListItemEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int listId;
    public int movieId;
    public String movieTitle;
    public String moviePoster;
    public String movieYear;
    public double movieRating;
    public long addedAt;

    public MovieListItemEntity(int listId, int movieId, String movieTitle,
                               String moviePoster, String movieYear, double movieRating) {
        this.listId = listId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.moviePoster = moviePoster;
        this.movieYear = movieYear;
        this.movieRating = movieRating;
        this.addedAt = System.currentTimeMillis();
    }
}

