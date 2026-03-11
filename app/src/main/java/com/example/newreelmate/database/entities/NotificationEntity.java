package com.example.newreelmate.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notifications")
public class NotificationEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;
    public String type;     // "welcome", "review", "list"
    public String title;
    public String message;
    public long createdAt;
    public boolean isRead;

    public NotificationEntity(int userId, String type, String title, String message) {
        this.userId    = userId;
        this.type      = type;
        this.title     = title;
        this.message   = message;
        this.createdAt = System.currentTimeMillis();
        this.isRead    = false;
    }
}

