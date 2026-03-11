package com.example.newreelmate.database;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.newreelmate.database.Converters;
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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReelMateRepository {

    private final UserDao userDao;
    private final WatchlistDao watchlistDao;
    private final MovieListDao movieListDao;
    private final MovieListItemDao movieListItemDao;
    private final ReviewDao reviewDao;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public interface Callback<T> {
        void onResult(T result);
    }

    public ReelMateRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        userDao = db.userDao();
        watchlistDao = db.watchlistDao();
        movieListDao = db.movieListDao();
        movieListItemDao = db.movieListItemDao();
        reviewDao = db.reviewDao();
    }

    // ─── USER ────────────────────────────────────────────────────────────────

    public void registerUser(String name, String email, String password, Callback<Boolean> callback) {
        executor.execute(() -> {
            boolean success;
            try {
                if (userDao.emailExists(email) > 0) {
                    success = false;
                } else {
                    userDao.insertUser(new UserEntity(name, email, password));
                    success = true;
                }
            } catch (Exception e) {
                success = false;
            }
            boolean finalSuccess = success;
            mainHandler.post(() -> callback.onResult(finalSuccess));
        });
    }

    public void loginUser(String email, String password, Callback<UserEntity> callback) {
        executor.execute(() -> {
            UserEntity user = userDao.getUserByEmail(email);
            UserEntity result = (user != null && user.password.equals(password)) ? user : null;
            mainHandler.post(() -> callback.onResult(result));
        });
    }

    public void updateProfile(int userId, String name, String email, Callback<Boolean> callback) {
        executor.execute(() -> {
            try {
                userDao.updateProfile(userId, name, email);
                mainHandler.post(() -> callback.onResult(true));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onResult(false));
            }
        });
    }

    public void updateProfilePhoto(int userId, String uri, Callback<Boolean> callback) {
        executor.execute(() -> {
            try {
                userDao.updateProfilePhoto(userId, uri);
                mainHandler.post(() -> callback.onResult(true));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onResult(false));
            }
        });
    }

    public void resetPassword(String email, String newPassword, Callback<Boolean> callback) {
        executor.execute(() -> {
            UserEntity user = userDao.getUserByEmail(email);
            if (user == null) {
                mainHandler.post(() -> callback.onResult(false));
                return;
            }
            userDao.updatePassword(email, newPassword);
            mainHandler.post(() -> callback.onResult(true));
        });
    }

    public void getUserById(int userId, Callback<UserEntity> callback) {
        executor.execute(() -> {
            UserEntity user = userDao.getUserById(userId);
            mainHandler.post(() -> callback.onResult(user));
        });
    }

    // ─── WATCHLIST ────────────────────────────────────────────────────────────

    public LiveData<List<WatchlistEntity>> getWatchlist(int userId) {
        return watchlistDao.getWatchlistForUser(userId);
    }

    public LiveData<Integer> getWatchlistCount(int userId) {
        return watchlistDao.getWatchlistCount(userId);
    }

    public void addToWatchlist(int userId, int movieId, String title, String poster,
                               String year, double rating, Callback<Boolean> callback) {
        executor.execute(() -> {
            try {
                WatchlistEntity item = new WatchlistEntity(userId, movieId, title, poster, year, rating);
                watchlistDao.insertWatchlistItem(item);
                mainHandler.post(() -> callback.onResult(true));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onResult(false));
            }
        });
    }

    public void removeFromWatchlist(int userId, int movieId, Callback<Boolean> callback) {
        executor.execute(() -> {
            watchlistDao.removeFromWatchlist(userId, movieId);
            mainHandler.post(() -> callback.onResult(true));
        });
    }

    public void isInWatchlist(int userId, int movieId, Callback<Boolean> callback) {
        executor.execute(() -> {
            boolean result = watchlistDao.isInWatchlist(userId, movieId) > 0;
            mainHandler.post(() -> callback.onResult(result));
        });
    }

    public void setMovieWatched(int userId, int movieId, boolean watched, Callback<Boolean> callback) {
        executor.execute(() -> {
            watchlistDao.setWatched(userId, movieId, watched);
            mainHandler.post(() -> callback.onResult(true));
        });
    }

    // ─── MOVIE LISTS ──────────────────────────────────────────────────────────

    public LiveData<List<MovieListEntity>> getMovieLists(int userId) {
        return movieListDao.getListsForUser(userId);
    }

    public void createMovieList(int userId, String name, String description, Callback<Long> callback) {
        executor.execute(() -> {
            long id = movieListDao.insertList(new MovieListEntity(userId, name, description));
            mainHandler.post(() -> callback.onResult(id));
        });
    }

    public void deleteMovieList(int listId, int userId, Callback<Boolean> callback) {
        executor.execute(() -> {
            movieListDao.deleteList(listId, userId);
            mainHandler.post(() -> callback.onResult(true));
        });
    }

    public void addMovieToList(int listId, int movieId, String title, String poster,
                               String year, double rating, Callback<Boolean> callback) {
        executor.execute(() -> {
            try {
                MovieListItemEntity item = new MovieListItemEntity(listId, movieId, title, poster, year, rating);
                movieListItemDao.insertItem(item);
                mainHandler.post(() -> callback.onResult(true));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onResult(false));
            }
        });
    }

    public void removeMovieFromList(int listId, int movieId, Callback<Boolean> callback) {
        executor.execute(() -> {
            movieListItemDao.removeItem(listId, movieId);
            mainHandler.post(() -> callback.onResult(true));
        });
    }

    public LiveData<List<MovieListItemEntity>> getMoviesInList(int listId) {
        return movieListItemDao.getItemsForList(listId);
    }

    public void getListById(int listId, Callback<MovieListEntity> callback) {
        executor.execute(() -> {
            MovieListEntity list = movieListDao.getListById(listId);
            mainHandler.post(() -> callback.onResult(list));
        });
    }

    // ─── REVIEWS ─────────────────────────────────────────────────────────────

    public void submitReview(int userId, String userName, int movieId, String movieTitle,
                             float rating, String comment, Callback<Boolean> callback) {
        executor.execute(() -> {
            try {
                ReviewEntity review = new ReviewEntity(userId, userName, movieId, movieTitle, rating, comment);
                reviewDao.insertReview(review);
                mainHandler.post(() -> callback.onResult(true));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onResult(false));
            }
        });
    }

    public LiveData<List<ReviewEntity>> getReviewsForMovie(int movieId) {
        return reviewDao.getReviewsForMovie(movieId);
    }

    public LiveData<List<ReviewEntity>> getReviewsByUser(int userId) {
        return reviewDao.getReviewsByUser(userId);
    }

    public void getUserReviewForMovie(int userId, int movieId, Callback<ReviewEntity> callback) {
        executor.execute(() -> {
            ReviewEntity review = reviewDao.getReviewByUserAndMovie(userId, movieId);
            mainHandler.post(() -> callback.onResult(review));
        });
    }

    // ─── FAVORITE GENRES ──────────────────────────────────────────────────────

    public void saveFavoriteGenres(int userId, List<String> genres, Callback<Boolean> callback) {
        executor.execute(() -> {
            try {
                String genresStr = Converters.fromList(genres);
                userDao.updateFavoriteGenres(userId, genresStr);
                mainHandler.post(() -> callback.onResult(true));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onResult(false));
            }
        });
    }

    public void getFavoriteGenres(int userId, Callback<List<String>> callback) {
        executor.execute(() -> {
            UserEntity user = userDao.getUserById(userId);
            List<String> genres = (user != null && user.favoriteGenres != null)
                    ? user.favoriteGenres : new java.util.ArrayList<>();
            mainHandler.post(() -> callback.onResult(genres));
        });
    }

    public void registerUserAndGetId(String name, String email, String password, Callback<Integer> callback) {
        executor.execute(() -> {
            try {
                if (userDao.emailExists(email) > 0) {
                    mainHandler.post(() -> callback.onResult(-1));
                    return;
                }
                userDao.insertUser(new UserEntity(name, email, password));
                // Fetch the inserted user to get the auto-generated id
                UserEntity inserted = userDao.getUserByEmail(email);
                int id = (inserted != null) ? inserted.id : -1;
                mainHandler.post(() -> callback.onResult(id));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onResult(-1));
            }
        });
    }
}

