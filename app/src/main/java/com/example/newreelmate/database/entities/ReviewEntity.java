package com.example.newreelmate.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "reviews",
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
public class ReviewEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;
    public String userName;
    public int movieId;
    public String movieTitle;
    public float rating;
    public String comment;
    public long createdAt;

    public ReviewEntity(int userId, String userName, int movieId, String movieTitle, float rating, String comment) {
        this.userId = userId;
        this.userName = userName;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = System.currentTimeMillis();
    }
}

