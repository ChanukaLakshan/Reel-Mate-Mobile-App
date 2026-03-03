package com.example.newreelmate.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.newreelmate.database.dao.MovieListDao;
import com.example.newreelmate.database.dao.MovieListItemDao;
import com.example.newreelmate.database.dao.ReviewDao;
import com.example.newreelmate.database.dao.UserDao;
import com.example.newreelmate.database.dao.WatchlistDao;
import com.example.newreelmate.database.entities.MovieListEntity;
import com.example.newreelmate.database.entities.MovieListItemEntity;
import com.example.newreelmate.database.entities.ReviewEntity;
import com.example.newreelmate.database.entities.UserEntity;
import com.example.newreelmate.database.entities.WatchlistEntity;

@Database(
    entities = {
        UserEntity.class,
        WatchlistEntity.class,
        MovieListEntity.class,
        MovieListItemEntity.class,
        ReviewEntity.class
    },
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract WatchlistDao watchlistDao();
    public abstract MovieListDao movieListDao();
    public abstract MovieListItemDao movieListItemDao();
    public abstract ReviewDao reviewDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "reelmate_db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}

