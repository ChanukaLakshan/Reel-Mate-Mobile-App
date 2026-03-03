package com.example.newreelmate.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newreelmate.database.entities.MovieListItemEntity;

import java.util.List;

@Dao
public interface MovieListItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertItem(MovieListItemEntity item);

    @Query("DELETE FROM movie_list_items WHERE listId = :listId AND movieId = :movieId")
    void removeItem(int listId, int movieId);

    @Query("SELECT * FROM movie_list_items WHERE listId = :listId ORDER BY addedAt DESC")
    LiveData<List<MovieListItemEntity>> getItemsForList(int listId);

    @Query("SELECT * FROM movie_list_items WHERE listId = :listId ORDER BY addedAt DESC")
    List<MovieListItemEntity> getItemsForListSync(int listId);

    @Query("SELECT COUNT(*) FROM movie_list_items WHERE listId = :listId")
    int getItemCount(int listId);

    @Query("SELECT COUNT(*) FROM movie_list_items WHERE listId = :listId AND movieId = :movieId")
    int isMovieInList(int listId, int movieId);
}

