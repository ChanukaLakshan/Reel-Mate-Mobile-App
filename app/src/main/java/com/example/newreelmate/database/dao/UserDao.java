package com.example.newreelmate.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.newreelmate.database.entities.UserEntity;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insertUser(UserEntity user);

    @Update
    void updateUser(UserEntity user);

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    UserEntity getUserByEmail(String email);

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    UserEntity getUserById(int id);

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    LiveData<UserEntity> observeUserById(int id);

    @Query("SELECT COUNT(*) FROM users WHERE email = :email")
    int emailExists(String email);

    @Query("UPDATE users SET name = :name, email = :email WHERE id = :id")
    void updateProfile(int id, String name, String email);

    @Query("UPDATE users SET password = :newPassword WHERE email = :email")
    void updatePassword(String email, String newPassword);

    @Query("UPDATE users SET favoriteGenres = :favoriteGenres WHERE id = :id")
    void updateFavoriteGenres(int id, String favoriteGenres);
}
