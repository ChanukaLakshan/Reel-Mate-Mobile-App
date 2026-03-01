package com.example.newreelmate.api;

/**
 * TMDB Integration Guide and Examples
 *
 * SETUP INSTRUCTIONS:
 * ===================
 *
 * 1. Get TMDB API Key:
 *    - Go to https://www.themoviedb.org/settings/api
 *    - Create a free account
 *    - Request an API key
 *    - Copy the API key
 *
 * 2. Add API Key to TMDBConfig.java:
 *    - Open TMDBConfig.java
 *    - Replace "YOUR_TMDB_API_KEY_HERE" with your actual API key
 *
 * 3. Use in Your Activities:
 *    - See examples below for how to use the repository
 *
 * USAGE EXAMPLES:
 * ===============
 */
public class TMDBIntegrationGuide {

    /**
     * EXAMPLE 1: Loading Popular Movies in an Activity
     *
     * In your Activity (e.g., HomeActivity.java):
     *
     * private TMDBRepository repository;
     *
     * @Override
     * protected void onCreate(Bundle savedInstanceState) {
     *     super.onCreate(savedInstanceState);
     *     setContentView(R.layout.activity_home);
     *
     *     repository = new TMDBRepository();
     *
     *     loadPopularMovies();
     * }
     *
     * private void loadPopularMovies() {
     *     repository.getPopularMovies(1, new TMDBRepository.RepositoryCallback<List<Movie>>() {
     *         @Override
     *         public void onSuccess(List<Movie> movies) {
     *             movieList.clear();
     *             movieList.addAll(movies);
     *             movieAdapter.notifyDataSetChanged();
     *             Toast.makeText(HomeActivity.this, "Loaded " + movies.size() + " movies", Toast.LENGTH_SHORT).show();
     *         }
     *
     *         @Override
     *         public void onError(String errorMessage) {
     *             Toast.makeText(HomeActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
     *         }
     *     });
     * }
     */

    /**
     * EXAMPLE 2: Searching Movies
     *
     * private void searchMovies(String query) {
     *     repository.searchMovies(query, 1, new TMDBRepository.RepositoryCallback<List<Movie>>() {
     *         @Override
     *         public void onSuccess(List<Movie> movies) {
     *             movieList.clear();
     *             movieList.addAll(movies);
     *             movieAdapter.notifyDataSetChanged();
     *         }
     *
     *         @Override
     *         public void onError(String errorMessage) {
     *             Toast.makeText(HomeActivity.this, "Search error: " + errorMessage, Toast.LENGTH_SHORT).show();
     *         }
     *     });
     * }
     */

    /**
     * EXAMPLE 3: Getting Movie Details
     *
     * In your MovieDetailsActivity.java:
     *
     * private void loadMovieDetails(int movieId) {
     *     repository.getMovieDetails(movieId, new TMDBRepository.RepositoryCallback<Movie>() {
     *         @Override
     *         public void onSuccess(Movie movie) {
     *             // Update UI with movie details
     *             titleTextView.setText(movie.getTitle());
     *             ratingTextView.setText(String.format("%.1f", movie.getRating()));
     *             descriptionTextView.setText(movie.getDescription());
     *             // Load poster with Glide
     *             Glide.with(MovieDetailsActivity.this)
     *                  .load(movie.getPoster())
     *                  .into(posterImageView);
     *         }
     *
     *         @Override
     *         public void onError(String errorMessage) {
     *             Toast.makeText(MovieDetailsActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
     *         }
     *     });
     * }
     */

    /**
     * EXAMPLE 4: Getting Top Rated Movies
     *
     * private void loadTopRatedMovies() {
     *     repository.getTopRatedMovies(1, new TMDBRepository.RepositoryCallback<List<Movie>>() {
     *         @Override
     *         public void onSuccess(List<Movie> movies) {
     *             movieList.clear();
     *             movieList.addAll(movies);
     *             movieAdapter.notifyDataSetChanged();
     *         }
     *
     *         @Override
     *         public void onError(String errorMessage) {
     *             // Handle error
     *         }
     *     });
     * }
     */

    /**
     * EXAMPLE 5: Getting Upcoming Movies
     *
     * private void loadUpcomingMovies() {
     *     repository.getUpcomingMovies(1, new TMDBRepository.RepositoryCallback<List<Movie>>() {
     *         @Override
     *         public void onSuccess(List<Movie> movies) {
     *             movieList.clear();
     *             movieList.addAll(movies);
     *             movieAdapter.notifyDataSetChanged();
     *         }
     *
     *         @Override
     *         public void onError(String errorMessage) {
     *             // Handle error
     *         }
     *     });
     * }
     */

    /**
     * CLASSES CREATED:
     * ================
     *
     * 1. TMDBMovie.java - Represents a movie from TMDB API
     * 2. TMDBMovieResponse.java - Response wrapper for movie lists
     * 3. TMDBMovieDetailsResponse.java - Detailed movie information
     * 4. TMDBGenreResponse.java - Movie genres
     * 5. TMDBApiService.java - Retrofit API interface
     * 6. TMDBRetrofitClient.java - Retrofit client singleton
     * 7. TMDBRepository.java - Repository for API calls
     * 8. TMDBConfig.java - Configuration (API key storage)
     */
}

