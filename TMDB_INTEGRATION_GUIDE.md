# TMDB API Integration Guide

This guide will help you integrate The Movie Database (TMDB) API into the ReelMate Android application.

## 1. Getting Your TMDB API Key

### Step 1: Create a TMDB Account
1. Go to [TMDB Official Website](https://www.themoviedb.org)
2. Click on "Sign Up" in the top right corner
3. Fill in your details and create an account
4. Verify your email

### Step 2: Request API Access
1. Log in to your TMDB account
2. Go to [API Settings](https://www.themoviedb.org/settings/api)
3. Click on "Create" or "Create an API Key"
4. Select "Developer" as your application type
5. Accept the terms and provide information about your application
6. Your API key will be generated

### Step 3: Copy Your API Key
- You will see two versions of the API key:
  - **v3 auth (Bearer token)** - We use this one
  - **v4 auth (Bearer token)**
- Copy the **v3 auth** API key

## 2. Adding the API Key to Your Project

1. Open the file: `app/src/main/java/com/example/newreelmate/api/TMDBConfig.java`

2. Find this line:
```java
public static final String TMDB_API_KEY = "YOUR_TMDB_API_KEY_HERE";
```

3. Replace `"YOUR_TMDB_API_KEY_HERE"` with your actual API key:
```java
public static final String TMDB_API_KEY = "your_actual_api_key_123456789";
```

4. Save the file

## 3. Available API Methods

### Popular Movies
```java
TMDBRepository repository = new TMDBRepository();
repository.getPopularMovies(1, new TMDBRepository.RepositoryCallback<List<Movie>>() {
    @Override
    public void onSuccess(List<Movie> movies) {
        // Use the movies list
    }
    
    @Override
    public void onError(String errorMessage) {
        // Handle error
    }
});
```

### Top Rated Movies
```java
repository.getTopRatedMovies(1, new TMDBRepository.RepositoryCallback<List<Movie>>() {
    @Override
    public void onSuccess(List<Movie> movies) {
        // Use the movies list
    }
    
    @Override
    public void onError(String errorMessage) {
        // Handle error
    }
});
```

### Upcoming Movies
```java
repository.getUpcomingMovies(1, new TMDBRepository.RepositoryCallback<List<Movie>>() {
    @Override
    public void onSuccess(List<Movie> movies) {
        // Use the movies list
    }
    
    @Override
    public void onError(String errorMessage) {
        // Handle error
    }
});
```

### Search Movies
```java
repository.searchMovies("Inception", 1, new TMDBRepository.RepositoryCallback<List<Movie>>() {
    @Override
    public void onSuccess(List<Movie> movies) {
        // Use the search results
    }
    
    @Override
    public void onError(String errorMessage) {
        // Handle error
    }
});
```

### Get Movie Details (with credits and videos)
```java
repository.getMovieDetails(550, new TMDBRepository.RepositoryCallback<Movie>() {
    @Override
    public void onSuccess(Movie movie) {
        // Use detailed movie information
        // Includes: director, cast, runtime, etc.
    }
    
    @Override
    public void onError(String errorMessage) {
        // Handle error
    }
});
```

## 4. Files Created

The TMDB integration consists of the following files in `app/src/main/java/com/example/newreelmate/api/`:

### Model Classes
- **TMDBMovie.java** - Represents a single movie from TMDB API
- **TMDBMovieResponse.java** - API response wrapper for movie lists
- **TMDBMovieDetailsResponse.java** - Detailed movie information response
- **TMDBGenreResponse.java** - Movie genres response

### API Communication
- **TMDBApiService.java** - Retrofit interface defining all API endpoints
- **TMDBRetrofitClient.java** - Singleton Retrofit client
- **TMDBRepository.java** - Repository pattern implementation for API calls

### Configuration
- **TMDBConfig.java** - API key and base URLs configuration

### Documentation
- **TMDBIntegrationGuide.java** - Code examples and usage patterns

## 5. Update Your Activities

### Example: Update HomeActivity

```java
import com.example.newreelmate.api.TMDBRepository;
import com.example.newreelmate.api.TMDBRepository.RepositoryCallback;

public class HomeActivity extends AppCompatActivity {
    
    private TMDBRepository repository;
    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        repository = new TMDBRepository();
        
        // ... existing code ...
        
        loadMoviesFromTMDB();
    }
    
    private void loadMoviesFromTMDB() {
        // Load popular movies
        repository.getPopularMovies(1, new RepositoryCallback<List<Movie>>() {
            @Override
            public void onSuccess(List<Movie> movies) {
                movieList.clear();
                movieList.addAll(movies);
                movieAdapter.notifyDataSetChanged();
            }
            
            @Override
            public void onError(String errorMessage) {
                Toast.makeText(HomeActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```

## 6. Image URLs

TMDB provides poster and backdrop images. The repository automatically generates full image URLs:

```java
movie.getPoster() // Returns full URL for poster image (500px width)
movie.getBackdropPath() // Returns full URL for backdrop (1280px width)
```

These can be used directly with Glide:
```java
Glide.with(context)
    .load(movie.getPoster())
    .into(imageView);
```

## 7. Pagination

All list endpoints support pagination. Page numbers start at 1:

```java
// Get page 2 of popular movies
repository.getPopularMovies(2, callback);

// Get page 3 of search results
repository.searchMovies("Avatar", 3, callback);
```

## 8. Error Handling

Always handle errors in your callbacks:

```java
@Override
public void onError(String errorMessage) {
    Log.e("TMDB", "Error: " + errorMessage);
    Toast.makeText(context, "Failed to load movies", Toast.LENGTH_SHORT).show();
    
    // Optional: Show fallback data or retry button
}
```

## 9. Dependencies Already Added

The following dependencies are already added to your `build.gradle.kts`:
- Retrofit 2.9.0 - HTTP client
- Gson 2.9.0 - JSON serialization
- Glide 4.16.0 - Image loading

## 10. Troubleshooting

### Issue: "Invalid API key"
- Make sure you copied the v3 auth key (not v4)
- Verify the key is correctly added to TMDBConfig.java
- Check for extra spaces or special characters

### Issue: "Network error"
- Ensure your device has internet connection
- Check that INTERNET permission is enabled in AndroidManifest.xml (already added)
- Verify the TMDB API service is online

### Issue: "Empty movie list"
- The API might be returning no results for your search
- Try with different search terms
- Check the error message in Logcat

## 11. Next Steps

1. ✅ Get your TMDB API key (see step 1)
2. ✅ Add the API key to TMDBConfig.java (see step 2)
3. ✅ Update your Activities to use TMDBRepository (see step 5)
4. ✅ Test the integration by running the app
5. ✅ Implement error handling as needed

## 12. Additional Resources

- [TMDB API Documentation](https://developers.themoviedb.org/3)
- [TMDB API Reference](https://developers.themoviedb.org/3/getting-started/introduction)
- [TMDB Images](https://developers.themoviedb.org/3/getting-started/images)

## 13. API Rate Limits

- TMDB allows up to 40 requests per 10 seconds for free accounts
- Implement caching to avoid excessive requests
- Use pagination to load data in batches

---

**Happy coding! Your ReelMate app is now connected to TMDB's extensive movie database! 🎬**

