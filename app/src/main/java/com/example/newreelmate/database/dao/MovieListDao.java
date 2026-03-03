package com.example.newreelmate.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.newreelmate.database.entities.MovieListEntity;

import java.util.List;

@Dao
public interface MovieListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertList(MovieListEntity list);

    @Update
    void updateList(MovieListEntity list);

    @Query("DELETE FROM movie_lists WHERE id = :listId AND userId = :userId")
    void deleteList(int listId, int userId);

    @Query("SELECT * FROM movie_lists WHERE userId = :userId ORDER BY createdAt DESC")
    LiveData<List<MovieListEntity>> getListsForUser(int userId);

    @Query("SELECT * FROM movie_lists WHERE userId = :userId ORDER BY createdAt DESC")
    List<MovieListEntity> getListsForUserSync(int userId);

    @Query("SELECT * FROM movie_lists WHERE id = :listId LIMIT 1")
    MovieListEntity getListById(int listId);
}

