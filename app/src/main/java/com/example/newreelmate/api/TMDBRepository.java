package com.example.newreelmate.api;

import android.util.Log;

import com.example.newreelmate.models.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository for handling TMDB API calls
 */
public class TMDBRepository {

    private static final String TAG = "TMDBRepository";
    private final TMDBApiService apiService;

    public TMDBRepository() {
        this.apiService = TMDBRetrofitClient.getApiService();
    }

    /**
     * Fetch popular movies from TMDB
     * @param page Page number for pagination
     * @param callback Callback to handle response
     */
    public void getPopularMovies(int page, final RepositoryCallback<List<Movie>> callback) {
        Call<TMDBMovieResponse> call = apiService.getPopularMovies(TMDBConfig.TMDB_API_KEY, page);

        call.enqueue(new Callback<TMDBMovieResponse>() {
            @Override
            public void onResponse(Call<TMDBMovieResponse> call, Response<TMDBMovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = convertTMDBMoviesToMovies(response.body().getResults());
                    callback.onSuccess(movies);
                    Log.d(TAG, "Popular movies fetched successfully: " + movies.size());
                } else {
                    callback.onError("Failed to fetch popular movies: " + response.code());
                    Log.e(TAG, "Error response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TMDBMovieResponse> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
                Log.e(TAG, "Network error", t);
            }
        });
    }

    /**
     * Fetch top rated movies from TMDB
     * @param page Page number for pagination
     * @param callback Callback to handle response
     */
    public void getTopRatedMovies(int page, final RepositoryCallback<List<Movie>> callback) {
        Call<TMDBMovieResponse> call = apiService.getTopRatedMovies(TMDBConfig.TMDB_API_KEY, page);

        call.enqueue(new Callback<TMDBMovieResponse>() {
            @Override
            public void onResponse(Call<TMDBMovieResponse> call, Response<TMDBMovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = convertTMDBMoviesToMovies(response.body().getResults());
                    callback.onSuccess(movies);
                    Log.d(TAG, "Top rated movies fetched successfully: " + movies.size());
                } else {
                    callback.onError("Failed to fetch top rated movies: " + response.code());
                    Log.e(TAG, "Error response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TMDBMovieResponse> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
                Log.e(TAG, "Network error", t);
            }
        });
    }

    /**
     * Fetch upcoming movies from TMDB
     * @param page Page number for pagination
     * @param callback Callback to handle response
     */
    public void getUpcomingMovies(int page, final RepositoryCallback<List<Movie>> callback) {
        Call<TMDBMovieResponse> call = apiService.getUpcomingMovies(TMDBConfig.TMDB_API_KEY, page);

        call.enqueue(new Callback<TMDBMovieResponse>() {
            @Override
            public void onResponse(Call<TMDBMovieResponse> call, Response<TMDBMovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = convertTMDBMoviesToMovies(response.body().getResults());
                    callback.onSuccess(movies);
                    Log.d(TAG, "Upcoming movies fetched successfully: " + movies.size());
                } else {
                    callback.onError("Failed to fetch upcoming movies: " + response.code());
                    Log.e(TAG, "Error response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TMDBMovieResponse> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
                Log.e(TAG, "Network error", t);
            }
        });
    }

    /**
     * Search movies by query
     * @param query Search query
     * @param page Page number for pagination
     * @param callback Callback to handle response
     */
    public void searchMovies(String query, int page, final RepositoryCallback<List<Movie>> callback) {
        Call<TMDBMovieResponse> call = apiService.searchMovies(TMDBConfig.TMDB_API_KEY, query, page);

        call.enqueue(new Callback<TMDBMovieResponse>() {
            @Override
            public void onResponse(Call<TMDBMovieResponse> call, Response<TMDBMovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = convertTMDBMoviesToMovies(response.body().getResults());
                    callback.onSuccess(movies);
                    Log.d(TAG, "Movies search successful: " + movies.size());
                } else {
                    callback.onError("Failed to search movies: " + response.code());
                    Log.e(TAG, "Error response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TMDBMovieResponse> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
                Log.e(TAG, "Network error", t);
            }
        });
    }

    /**
     * Get movie details including credits and videos
     * @param movieId Movie ID
     * @param callback Callback to handle response
     */
    public void getMovieDetails(int movieId, final RepositoryCallback<Movie> callback) {
        Call<TMDBMovieDetailsResponse> call = apiService.getMovieDetails(
                movieId,
                TMDBConfig.TMDB_API_KEY,
                "credits,videos"
        );

        call.enqueue(new Callback<TMDBMovieDetailsResponse>() {
            @Override
            public void onResponse(Call<TMDBMovieDetailsResponse> call, Response<TMDBMovieDetailsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Movie movie = convertTMDBMovieDetailsToMovie(response.body());
                    callback.onSuccess(movie);
                    Log.d(TAG, "Movie details fetched successfully: " + movie.getTitle());
                } else {
                    callback.onError("Failed to fetch movie details: " + response.code());
                    Log.e(TAG, "Error response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TMDBMovieDetailsResponse> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
                Log.e(TAG, "Network error", t);
            }
        });
    }

    /**
     * Convert TMDB movies to Movie model
     * @param tmdbMovies List of TMDB movies
     * @return List of Movie objects
     */
    private List<Movie> convertTMDBMoviesToMovies(List<TMDBMovie> tmdbMovies) {
        List<Movie> movies = new ArrayList<>();

        if (tmdbMovies != null) {
            for (TMDBMovie tmdbMovie : tmdbMovies) {
                String year = "";
                if (tmdbMovie.getReleaseDate() != null && !tmdbMovie.getReleaseDate().isEmpty()) {
                    year = tmdbMovie.getReleaseDate().substring(0, 4);
                }

                Movie movie = new Movie(
                        tmdbMovie.getId(),
                        tmdbMovie.getTitle(),
                        tmdbMovie.getPosterUrl(),
                        "Movies",
                        year,
                        tmdbMovie.getVoteAverage()
                );

                movies.add(movie);
            }
        }

        return movies;
    }

    /**
     * Convert TMDB movie details to Movie model
     * @param response TMDB movie details response
     * @return Movie object
     */
    private Movie convertTMDBMovieDetailsToMovie(TMDBMovieDetailsResponse response) {
        List<String> genres = new ArrayList<>();
        if (response.getGenres() != null) {
            for (TMDBMovieDetailsResponse.GenreDetail genre : response.getGenres()) {
                genres.add(genre.getName());
            }
        }

        List<String> cast = new ArrayList<>();
        if (response.getCredits() != null && response.getCredits().getCast() != null) {
            for (TMDBMovie.CastMember member : response.getCredits().getCast()) {
                cast.add(member.getName());
            }
        }

        String year = "";
        if (response.getReleaseDate() != null && !response.getReleaseDate().isEmpty()) {
            year = response.getReleaseDate().substring(0, 4);
        }

        String director = "";
        if (response.getCredits() != null && response.getCredits().getCrew() != null) {
            for (TMDBMovie.CrewMember member : response.getCredits().getCrew()) {
                if ("Director".equals(member.getJob())) {
                    director = member.getName();
                    break;
                }
            }
        }

        String runtime = response.getRuntime() > 0 ? response.getRuntime() + " min" : "";

        Movie movie = new Movie(
                response.getId(),
                response.getTitle(),
                response.getPosterUrl(),
                genres,
                year,
                response.getVoteAverage(),
                runtime,
                director,
                response.getOverview(),
                cast
        );

        return movie;
    }

    /**
     * Callback interface for repository operations
     * @param <T> Type of data returned
     */
    public interface RepositoryCallback<T> {
        void onSuccess(T data);
        void onError(String errorMessage);
    }
}

