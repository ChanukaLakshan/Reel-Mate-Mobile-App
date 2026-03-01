# TMDB API Integration - Architecture & Structure

## 📊 Project Structure

```
newReelMate/
├── app/
│   └── src/main/java/com/example/newreelmate/
│       ├── api/                          ← NEW PACKAGE
│       │   ├── TMDBMovie.java                    ← Movie model
│       │   ├── TMDBMovieResponse.java            ← API response wrapper
│       │   ├── TMDBMovieDetailsResponse.java     ← Detailed response
│       │   ├── TMDBGenreResponse.java            ← Genres
│       │   ├── TMDBApiService.java               ← Retrofit interface
│       │   ├── TMDBRetrofitClient.java           ← HTTP client
│       │   ├── TMDBRepository.java               ← Main repository
│       │   ├── TMDBConfig.java                   ← Configuration
│       │   ├── TMDBIntegrationGuide.java         ← Code examples
│       │   └── ImplementationExamples.java       ← Activity examples
│       │
│       ├── models/
│       │   ├── Movie.java                (existing, updated for TMDB)
│       │   ├── MovieList.java            (existing)
│       │   └── Review.java               (existing)
│       │
│       ├── adapters/
│       │   └── MovieAdapter.java         (existing)
│       │
│       ├── data/
│       │   └── DataProvider.java         (existing, can be updated)
│       │
│       ├── HomeActivity.java             (update to use TMDB)
│       ├── MovieDetailsActivity.java     (update to use TMDB)
│       ├── LoginActivity.java
│       ├── ProfileActivity.java
│       └── ... (other activities)
│
├── TMDB_API_SETUP_COMPLETE.md        ← This file
├── TMDB_INTEGRATION_GUIDE.md         ← Detailed guide
├── TMDB_INTEGRATION_SUMMARY.md       ← Quick overview
└── TMDB_INTEGRATION_CHECKLIST.md     ← Step-by-step checklist
```

---

## 🔄 Data Flow Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    Android Activity                         │
│                   (HomeActivity.java)                       │
└────────────────────────┬────────────────────────────────────┘
                         │
                    Creates/calls
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│              TMDBRepository                                 │
│        (com.example.newreelmate.api)                        │
│                                                             │
│  • getPopularMovies(page, callback)                         │
│  • getTopRatedMovies(page, callback)                        │
│  • getUpcomingMovies(page, callback)                        │
│  • searchMovies(query, page, callback)                      │
│  • getMovieDetails(movieId, callback)                       │
│                                                             │
│  ↓ Calls ↓                                                  │
│                                                             │
│  apiService.getPopularMovies(apiKey, page)                 │
└────────────────────────┬────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│            TMDBApiService (Retrofit)                        │
│        (Interface defining all API endpoints)               │
│                                                             │
│  @GET("movie/popular")                                      │
│  @GET("movie/top_rated")                                    │
│  @GET("movie/upcoming")                                     │
│  @GET("search/movie")                                       │
│  @GET("movie/{movie_id}")                                   │
│  @GET("genre/movie/list")                                   │
└────────────────────────┬────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│          TMDBRetrofitClient                                 │
│     (Singleton HTTP Client)                                 │
│                                                             │
│  Base URL: https://api.themoviedb.org/3/                   │
└────────────────────────┬────────────────────────────────────┘
                         │
            ┌────────────┴────────────┐
            │                         │
            ▼                         ▼
    ┌──────────────┐        ┌──────────────────────┐
    │   Network    │        │   GSON Converter    │
    │  Connection  │        │ (JSON → Java Objects)│
    └──────────────┘        └──────────────────────┘
            │                         │
            └────────────┬────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│       TMDB API Server (Online)                              │
│       https://api.themoviedb.org/3/                         │
│                                                             │
│  Returns JSON response:                                     │
│  {                                                          │
│    "page": 1,                                               │
│    "results": [                                             │
│      {                                                      │
│        "id": 550,                                           │
│        "title": "Fight Club",                               │
│        "poster_path": "/...",                               │
│        "release_date": "1999-10-15",                        │
│        "vote_average": 8.8,                                 │
│        ...                                                  │
│      },                                                     │
│      ...                                                    │
│    ],                                                       │
│    "total_pages": 500,                                      │
│    "total_results": 10000                                   │
│  }                                                          │
└─────────────────────────────────────────────────────────────┘
            │
            ▼
┌─────────────────────────────────────────────────────────────┐
│        GSON Deserialization                                 │
│  JSON Response → TMDBMovieResponse object                   │
│                                                             │
│  {                                                          │
│    page: 1,                                                 │
│    results: [                                               │
│      TMDBMovie {                                            │
│        id: 550,                                             │
│        title: "Fight Club",                                 │
│        posterPath: "/...",                                  │
│        ...                                                  │
│      },                                                     │
│      ...                                                    │
│    ],                                                       │
│    totalPages: 500,                                         │
│    totalResults: 10000                                      │
│  }                                                          │
└─────────────────────────────────────────────────────────────┘
            │
            ▼
┌─────────────────────────────────────────────────────────────┐
│       Repository Conversion                                 │
│  TMDBMovie objects → Your Movie objects                     │
│                                                             │
│  Movie movie = new Movie(                                   │
│    tmdbMovie.getId(),                                       │
│    tmdbMovie.getTitle(),                                    │
│    tmdbMovie.getPosterUrl(),     ← Generated URL           │
│    "Movies",                                                │
│    2023,                                                    │
│    tmdbMovie.getVoteAverage()                              │
│  );                                                         │
└─────────────────────────────────────────────────────────────┘
            │
            ▼
┌─────────────────────────────────────────────────────────────┐
│        Callback Execution                                   │
│                                                             │
│  callback.onSuccess(List<Movie> movies)                     │
│                                                             │
│  OR                                                         │
│                                                             │
│  callback.onError(String errorMessage)                      │
└─────────────────────────────────────────────────────────────┘
            │
            ▼
┌─────────────────────────────────────────────────────────────┐
│     Activity UI Update                                      │
│                                                             │
│  movieList.clear();                                         │
│  movieList.addAll(movies);                                  │
│  movieAdapter.notifyDataSetChanged();                       │
│                                                             │
│  ↓ Displays ↓                                               │
│                                                             │
│  RecyclerView with Movies:                                  │
│  ┌─────────────────────────┐                                │
│  │ [Image] Fight Club      │                                │
│  │         ⭐ 8.8         │                                │
│  │         1999            │                                │
│  ├─────────────────────────┤                                │
│  │ [Image] Inception       │                                │
│  │         ⭐ 8.8         │                                │
│  │         2010            │                                │
│  ├─────────────────────────┤                                │
│  │ [Image] The Matrix      │                                │
│  │         ⭐ 8.7         │                                │
│  │         1999            │                                │
│  └─────────────────────────┘                                │
└─────────────────────────────────────────────────────────────┘
```

---

## 🏗️ Class Hierarchy

```
TMDBMovie (API Model)
├── id: int
├── title: String
├── posterPath: String
├── backdropPath: String
├── overview: String
├── releaseDate: String
├── voteAverage: double
├── voteCount: int
├── genreIds: List<Integer>
├── runtime: int
└── credits: CreditsResponse
    ├── cast: List<CastMember>
    │   ├── id: int
    │   ├── name: String
    │   ├── character: String
    │   └── profilePath: String
    └── crew: List<CrewMember>
        ├── id: int
        ├── name: String
        ├── job: String
        └── department: String

        ↓ Converts to ↓

Movie (Your Model - from models/Movie.java)
├── id: int
├── title: String
├── poster: String
├── genre: String
├── year: String
├── rating: double
├── runtime: String
├── director: String
├── description: String
├── genres: List<String>
├── cast: List<String>
├── inWatchlist: boolean
└── isWatched: boolean
```

---

## 🔐 API Key Flow

```
┌───────────────────────────────┐
│  Get API Key from TMDB        │
│  https://themoviedb.org       │
│                               │
│  Copy v3 auth key:            │
│  abc123def456ghi789jkl012...  │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│  Add to TMDBConfig.java       │
│                               │
│  public static final String   │
│  TMDB_API_KEY =               │
│  "abc123def456ghi789jkl012..."│
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│  Used by TMDBRepository       │
│                               │
│  apiService.getPopularMovies( │
│    TMDB_API_KEY,  ← Here      │
│    page                       │
│  )                            │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│  Sent in HTTP Request         │
│                               │
│  GET /3/movie/popular?        │
│  api_key=abc123def456...      │
│  &page=1                      │
│                               │
│  https://api.themoviedb.      │
│  org/3/movie/popular?         │
│  api_key=abc123def456...      │
└───────────────────────────────┘
```

---

## 🔄 Callback Pattern

```
Activity Code:
┌─────────────────────────────────────────────────────────┐
│ repository.getPopularMovies(1,                          │
│     new RepositoryCallback<List<Movie>>() {             │
│         @Override                                       │
│         public void onSuccess(List<Movie> movies) {     │
│             // Handle success                           │
│             movieList.clear();                          │
│             movieList.addAll(movies);                   │
│             movieAdapter.notifyDataSetChanged();        │
│         }                                               │
│                                                         │
│         @Override                                       │
│         public void onError(String errorMessage) {      │
│             // Handle error                             │
│             Toast.makeText(this,                        │
│                 "Error: " + errorMessage,               │
│                 Toast.LENGTH_SHORT).show();             │
│         }                                               │
│     }                                                   │
│ );                                                      │
└─────────────────────────────────────────────────────────┘

Timeline:
┌──────────────────────────────────────────┐
│ 0ms: repository.getPopularMovies() called│
│     (Main thread continues)              │
├──────────────────────────────────────────┤
│ 100ms: Network request sent              │
│        (Background thread)               │
├──────────────────────────────────────────┤
│ 1500ms: Response received                │
│         (Background thread)              │
├──────────────────────────────────────────┤
│ 1505ms: callback.onSuccess() called      │
│        (Main thread)                     │
├──────────────────────────────────────────┤
│ 1510ms: RecyclerView updated             │
│        (Main thread)                     │
└──────────────────────────────────────────┘

NO BLOCKING! The app stays responsive.
```

---

## 📊 API Endpoints

```
┌──────────────────────────────────────────────────────┐
│ Popular Movies                                       │
├──────────────────────────────────────────────────────┤
│ GET /movie/popular?api_key=KEY&page=1               │
│ Returns: 20 most popular movies                      │
└──────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────┐
│ Top Rated Movies                                    │
├──────────────────────────────────────────────────────┤
│ GET /movie/top_rated?api_key=KEY&page=1            │
│ Returns: 20 highest-rated movies                     │
└──────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────┐
│ Upcoming Movies                                      │
├──────────────────────────────────────────────────────┤
│ GET /movie/upcoming?api_key=KEY&page=1              │
│ Returns: 20 upcoming movie releases                  │
└──────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────┐
│ Search Movies                                        │
├──────────────────────────────────────────────────────┤
│ GET /search/movie?api_key=KEY&query=Inception&page=1│
│ Returns: Movies matching search query                │
└──────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────┐
│ Movie Details                                        │
├──────────────────────────────────────────────────────┤
│ GET /movie/550?api_key=KEY&append_to_response=     │
│                     credits,videos                  │
│ Returns: Full movie details with cast & trailers     │
└──────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────┐
│ Genres                                               │
├──────────────────────────────────────────────────────┤
│ GET /genre/movie/list?api_key=KEY                   │
│ Returns: List of all movie genres                    │
└──────────────────────────────────────────────────────┘
```

---

## 🎬 Image URL Generation

```
From TMDB API Response:
"poster_path": "/6CoRTJTmijWbPvnF2p2GB1IOo5f.jpg"

Your Code:
getPosterUrl() method in TMDBMovie.java

Returns:
"https://image.tmdb.org/t/p/w500/6CoRTJTmijWbPvnF2p2GB1IOo5f.jpg"
                           ↑
                    Width: 500px

Used with Glide:
Glide.with(context)
    .load("https://image.tmdb.org/t/p/w500/6CoRTJTmijWbPvnF2p2GB1IOo5f.jpg")
    .into(imageView);

Result:
┌──────────────────┐
│                  │
│     🎬 Movie     │
│    Poster        │
│    Image         │
│                  │
└──────────────────┘
```

---

## ✅ Setup Verification

```
Before Running App:
┌─────────────────────────────────────────────┐
│ ✅ Retrofit dependency added                │
│ ✅ GSON dependency added                    │
│ ✅ Glide dependency added                   │
│ ✅ INTERNET permission granted              │
│ ✅ All API classes created                  │
│ ✅ Repository pattern implemented           │
│ ❌ API key added (YOU NEED TO DO THIS)      │
│ ❌ Activities updated (YOU NEED TO DO THIS) │
└─────────────────────────────────────────────┘

After Adding API Key:
┌─────────────────────────────────────────────┐
│ ✅ Retrofit dependency added                │
│ ✅ GSON dependency added                    │
│ ✅ Glide dependency added                   │
│ ✅ INTERNET permission granted              │
│ ✅ All API classes created                  │
│ ✅ Repository pattern implemented           │
│ ✅ API key added                            │
│ ❌ Activities updated (YOU NEED TO DO THIS) │
└─────────────────────────────────────────────┘

After Updating Activities:
┌─────────────────────────────────────────────┐
│ ✅ Retrofit dependency added                │
│ ✅ GSON dependency added                    │
│ ✅ Glide dependency added                   │
│ ✅ INTERNET permission granted              │
│ ✅ All API classes created                  │
│ ✅ Repository pattern implemented           │
│ ✅ API key added                            │
│ ✅ Activities updated                       │
│                                             │
│ 🚀 READY TO RUN!                           │
└─────────────────────────────────────────────┘
```

---

**Your TMDB API integration is complete and ready to use! 🎬**

