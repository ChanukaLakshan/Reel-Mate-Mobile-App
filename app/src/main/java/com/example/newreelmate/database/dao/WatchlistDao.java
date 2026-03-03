package com.example.newreelmate.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.newreelmate.database.entities.WatchlistEntity;

import java.util.List;

@Dao
public interface WatchlistDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertWatchlistItem(WatchlistEntity item);

    @Update
    void updateWatchlistItem(WatchlistEntity item);

    @Query("DELETE FROM watchlist WHERE userId = :userId AND movieId = :movieId")
    void removeFromWatchlist(int userId, int movieId);

    @Query("SELECT * FROM watchlist WHERE userId = :userId ORDER BY addedAt DESC")
    LiveData<List<WatchlistEntity>> getWatchlistForUser(int userId);

    @Query("SELECT * FROM watchlist WHERE userId = :userId ORDER BY addedAt DESC")
    List<WatchlistEntity> getWatchlistForUserSync(int userId);

    @Query("SELECT COUNT(*) FROM watchlist WHERE userId = :userId AND movieId = :movieId")
    int isInWatchlist(int userId, int movieId);

    @Query("SELECT COUNT(*) FROM watchlist WHERE userId = :userId")
    LiveData<Integer> getWatchlistCount(int userId);

    @Query("UPDATE watchlist SET isWatched = :watched WHERE userId = :userId AND movieId = :movieId")
    void setWatched(int userId, int movieId, boolean watched);

    @Query("SELECT COUNT(*) FROM watchlist WHERE userId = :userId AND isWatched = 1")
    int getWatchedCount(int userId);
}

