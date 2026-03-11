package com.example.newreelmate.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract WatchlistDao watchlistDao();
    public abstract MovieListDao movieListDao();
    public abstract MovieListItemDao movieListItemDao();
    public abstract ReviewDao reviewDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users ADD COLUMN favoriteGenres TEXT");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE reviews ADD COLUMN userName TEXT");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users ADD COLUMN profilePhotoUri TEXT");
        }
    };

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "reelmate_db"
                    )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}

