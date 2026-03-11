package com.example.newreelmate.database.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.newreelmate.database.Converters;

import java.util.List;

@Entity(tableName = "users", indices = {@Index(value = "email", unique = true)})
@TypeConverters(Converters.class)
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String email;
    public String password;
    public long createdAt;
    public List<String> favoriteGenres;
    public String profilePhotoUri;

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = System.currentTimeMillis();
        this.favoriteGenres = new java.util.ArrayList<>();
    }
}
