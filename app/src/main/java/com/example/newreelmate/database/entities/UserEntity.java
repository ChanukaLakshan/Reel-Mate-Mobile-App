package com.example.newreelmate.database.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = "email", unique = true)})
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String email;
    public String password;
    public long createdAt;

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = System.currentTimeMillis();
    }
}

