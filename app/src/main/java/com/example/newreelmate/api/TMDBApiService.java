package com.example.newreelmate.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * TMDB API Service — auth is handled via Bearer token in OkHttp interceptor
 */
public interface TMDBApiService {

    /**
     * Get popular movies
     * @param page Page number for pagination
     * @return Call with TMDBMovieResponse
     */
    @GET("movie/popular")
    Call<TMDBMovieResponse> getPopularMovies(
            @Query("page") int page
    );

    /**
     * Get top rated movies
     * @param page Page number for pagination
     * @return Call with TMDBMovieResponse
     */
    @GET("movie/top_rated")
    Call<TMDBMovieResponse> getTopRatedMovies(
            @Query("page") int page
    );

    /**
     * Get upcoming movies
     * @param page Page number for pagination
     * @return Call with TMDBMovieResponse
     */
    @GET("movie/upcoming")
    Call<TMDBMovieResponse> getUpcomingMovies(
            @Query("page") int page
    );

    /**
     * Search for movies
     * @param query Search query
     * @param page Page number for pagination
     * @return Call with TMDBMovieResponse
     */
    @GET("search/movie")
    Call<TMDBMovieResponse> searchMovies(
            @Query("query") String query,
            @Query("page") int page
    );

    /**
     * Get movie details
     * @param movieId The movie ID
     * @param appendToResponse Additional data to append (e.g., "credits,videos")
     * @return Call with TMDBMovieDetailsResponse
     */
    @GET("movie/{movie_id}")
    Call<TMDBMovieDetailsResponse> getMovieDetails(
            @Path("movie_id") int movieId,
            @Query("append_to_response") String appendToResponse
    );

    /**
     * Get genres
     * @return Call with TMDBGenreResponse
     */
    @GET("genre/movie/list")
    Call<TMDBGenreResponse> getGenres();

    /**
     * Discover movies by genre IDs
     * @param genreIds Pipe-separated genre IDs (e.g. "28|35")
     * @param page Page number
     * @param sortBy Sort order (e.g. "popularity.desc")
     * @return Call with TMDBMovieResponse
     */
    @GET("discover/movie")
    Call<TMDBMovieResponse> discoverMoviesByGenres(
            @Query("with_genres") String genreIds,
            @Query("page") int page,
            @Query("sort_by") String sortBy
    );
}
