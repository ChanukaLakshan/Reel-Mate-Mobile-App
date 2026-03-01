package com.example.newreelmate.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * TMDB API Service interface with Retrofit
 */
public interface TMDBApiService {

    /**
     * Get popular movies
     * @param apiKey Your TMDB API key
     * @param page Page number for pagination
     * @return Call with TMDBMovieResponse
     */
    @GET("movie/popular")
    Call<TMDBMovieResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    /**
     * Get top rated movies
     * @param apiKey Your TMDB API key
     * @param page Page number for pagination
     * @return Call with TMDBMovieResponse
     */
    @GET("movie/top_rated")
    Call<TMDBMovieResponse> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    /**
     * Get upcoming movies
     * @param apiKey Your TMDB API key
     * @param page Page number for pagination
     * @return Call with TMDBMovieResponse
     */
    @GET("movie/upcoming")
    Call<TMDBMovieResponse> getUpcomingMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    /**
     * Search for movies
     * @param apiKey Your TMDB API key
     * @param query Search query
     * @param page Page number for pagination
     * @return Call with TMDBMovieResponse
     */
    @GET("search/movie")
    Call<TMDBMovieResponse> searchMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("page") int page
    );

    /**
     * Get movie details
     * @param movieId The movie ID
     * @param apiKey Your TMDB API key
     * @param appendToResponse Additional data to append (e.g., "credits,videos")
     * @return Call with TMDBMovieDetailsResponse
     */
    @GET("movie/{movie_id}")
    Call<TMDBMovieDetailsResponse> getMovieDetails(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("append_to_response") String appendToResponse
    );

    /**
     * Get genres
     * @param apiKey Your TMDB API key
     * @return Call with TMDBGenreResponse
     */
    @GET("genre/movie/list")
    Call<TMDBGenreResponse> getGenres(
            @Query("api_key") String apiKey
    );
}

