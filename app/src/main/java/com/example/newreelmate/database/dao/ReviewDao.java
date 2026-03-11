package com.example.newreelmate.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.newreelmate.database.entities.ReviewEntity;

import java.util.List;

@Dao
public interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertReview(ReviewEntity review);

    @Update
    void updateReview(ReviewEntity review);

    @Query("DELETE FROM reviews WHERE id = :reviewId AND userId = :userId")
    void deleteReview(int reviewId, int userId);

    @Query("SELECT * FROM reviews WHERE movieId = :movieId ORDER BY createdAt DESC")
    LiveData<List<ReviewEntity>> getReviewsForMovie(int movieId);

    @Query("SELECT * FROM reviews WHERE userId = :userId ORDER BY createdAt DESC")
    LiveData<List<ReviewEntity>> getReviewsByUser(int userId);

    @Query("SELECT * FROM reviews WHERE userId = :userId AND movieId = :movieId LIMIT 1")
    ReviewEntity getReviewByUserAndMovie(int userId, int movieId);

    @Query("SELECT AVG(rating) FROM reviews WHERE movieId = :movieId")
    float getAverageRating(int movieId);

    @Query("SELECT COUNT(*) FROM reviews WHERE userId = :userId")
    LiveData<Integer> getReviewsCount(int userId);
}

