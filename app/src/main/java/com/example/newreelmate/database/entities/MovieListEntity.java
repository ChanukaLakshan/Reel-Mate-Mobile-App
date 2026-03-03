package com.example.newreelmate.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "movie_lists",
    foreignKeys = @ForeignKey(
        entity = UserEntity.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = ForeignKey.CASCADE
    ),
    indices = {@Index("userId")}
)
public class MovieListEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;
    public String name;
    public String description;
    public long createdAt;

    public MovieListEntity(int userId, String name, String description) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.createdAt = System.currentTimeMillis();
    }
}

