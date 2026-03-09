# 🎬 ReelMate — Personal Movie Watchlist App

A fully-featured Android movie companion app built with **Java**, powered by the **TMDB API** for real movie data, and backed by a local **Room database** for offline persistence.

---

## 📱 Screenshots

> Home Screen · My Lists · Movie Details · Profile

---

## ✨ Features

### 🏠 Home
- Loads **real popular movies** from TMDB API
- Search movies by title (live search)
- Add/remove movies from your personal watchlist
- Mark movies as watched
- Shows watchlist count

### 🎥 Movie Details
- Full movie info: title, rating, year, runtime, synopsis
- Director & cast list (from TMDB credits)
- Add to watchlist / mark watched from detail page
- Write a review with star rating

### 📋 My Lists
- Create custom movie lists (e.g. "Weekend Thrillers")
- Add movies to any list
- View, edit, delete, and share lists
- All lists stored in local Room database

### 🔔 Notifications
- Notifications screen (UI ready, extensible)

### 👤 Profile
- Displays user name, email and stats (movies watched, lists created, reviews written)
- Edit profile (name & email)
- Settings (notifications toggle, auto-play toggle)
- Logout

### 🔐 Authentication
- Register / Login with email & password
- Session management via `SharedPreferences`
- Forgot password screen
- Auto-login if session is active

---

## 🏗️ Architecture

```
app/
├── api/                        # TMDB API layer
│   ├── TMDBConfig.java         # API key & Bearer token config
│   ├── TMDBRetrofitClient.java # Retrofit + OkHttp client (Bearer auth interceptor)
│   ├── TMDBApiService.java     # Retrofit interface (endpoints)
│   ├── TMDBRepository.java     # API call methods & model conversion
│   ├── TMDBMovie.java          # TMDB movie model
│   ├── TMDBMovieResponse.java  # Paginated list response
│   ├── TMDBMovieDetailsResponse.java
│   └── TMDBGenreResponse.java
│
├── database/                   # Room local database
│   ├── AppDatabase.java        # Room DB singleton
│   ├── ReelMateRepository.java # DB operations (background threads)
│   ├── SessionManager.java     # User session (SharedPreferences)
│   ├── dao/
│   │   ├── UserDao.java
│   │   ├── WatchlistDao.java
│   │   ├── MovieListDao.java
│   │   ├── MovieListItemDao.java
│   │   └── ReviewDao.java
│   └── entities/
│       ├── UserEntity.java
│       ├── WatchlistEntity.java
│       ├── MovieListEntity.java
│       ├── MovieListItemEntity.java
│       └── ReviewEntity.java
│
├── models/                     # UI data models
│   ├── Movie.java
│   ├── MovieList.java
│   └── Review.java
│
├── adapters/                   # RecyclerView adapters
│   ├── MovieAdapter.java
│   ├── MovieListAdapter.java
│   └── ReviewAdapter.java
│
├── Activities
│   ├── LoginActivity.java      # Login / Register (Launcher)
│   ├── ForgotPasswordActivity.java
│   ├── HomeActivity.java       # Main feed (TMDB popular movies)
│   ├── MovieDetailsActivity.java
│   ├── ReviewActivity.java
│   ├── MyListsActivity.java
│   ├── CreateListActivity.java
│   ├── ListDetailActivity.java
│   ├── NotificationsActivity.java
│   ├── ProfileActivity.java
│   ├── EditProfileActivity.java
│   └── SettingsActivity.java
│
└── BottomNavHelper.java        # Shared bottom navigation setup
```

---

## 🗄️ Room Database Schema

| Table | Description |
|---|---|
| `users` | Registered user accounts (name, email, password) |
| `watchlist` | Per-user movie watchlist with watched state |
| `movie_lists` | Custom user-created movie lists |
| `movie_list_items` | Movies inside each custom list |
| `reviews` | User reviews with rating and text |

**Database name:** `reelmate_db` · **Version:** 1

---

## 🌐 TMDB API Integration

This app uses the [TMDB (The Movie Database) API](https://www.themoviedb.org/documentation/api).

### Authentication
All requests use **Bearer token** authentication via an OkHttp interceptor — no `api_key` query param needed on individual calls.

### Endpoints Used
| Endpoint | Purpose |
|---|---|
| `GET /movie/popular` | Home screen movie feed |
| `GET /movie/top_rated` | Top rated movies |
| `GET /movie/upcoming` | Upcoming movies |
| `GET /search/movie` | Search by title |
| `GET /movie/{id}?append_to_response=credits,videos` | Full movie details |
| `GET /genre/movie/list` | Genre list |

### Image URLs
| Size | Prefix |
|---|---|
| Poster (500px) | `https://image.tmdb.org/t/p/w500` |
| Backdrop (1280px) | `https://image.tmdb.org/t/p/w1280` |
| Profile (185px) | `https://image.tmdb.org/t/p/w185` |

---

## ⚙️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 36 |
| UI | XML Layouts, Material Components 1.13.0 |
| Navigation | Bottom Navigation Bar (4 tabs) |
| Networking | Retrofit 2.9.0 + OkHttp 4.12.0 |
| JSON Parsing | Gson (converter-gson 2.9.0) |
| Image Loading | Glide 4.16.0 |
| Local Database | Room 2.6.1 |
| Lifecycle | LiveData + ViewModel 2.8.3 |
| Session | SharedPreferences (SessionManager) |
| Build System | Gradle 8.13.2 (Kotlin DSL) |

---

## 🚀 Getting Started

### Prerequisites
- Android Studio (Hedgehog or newer)
- Android device or emulator running API 24+
- Internet connection (for TMDB data)

### Setup

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd "Reel Mate Mobile App"
   ```

2. **Open in Android Studio**
   - File → Open → select the `Reel Mate Mobile App` folder

3. **TMDB API Key** *(already configured)*
   - API Key and Bearer token are set in `TMDBConfig.java`
   - No extra setup required

4. **Build & Run**
   ```bash
   ./gradlew assembleDebug
   ```
   Or press **Run ▶** in Android Studio

---

## 🧭 Navigation

The app uses a **Bottom Navigation Bar** with 4 tabs:

| Tab | Icon | Activity |
|---|---|---|
| Home | 🏠 | `HomeActivity` — movie feed |
| Lists | ☰ | `MyListsActivity` — custom lists |
| Alerts | 🔔 | `NotificationsActivity` |
| Profile | 👤 | `ProfileActivity` |

---

## 📂 Project Structure (top-level)

```
Reel Mate Mobile App/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/newreelmate/   # All Java source
│   │   ├── res/
│   │   │   ├── layout/     # XML activity layouts
│   │   │   ├── menu/       # Bottom nav menu
│   │   │   ├── drawable/   # Icons & backgrounds
│   │   │   ├── color/      # Color state lists
│   │   │   └── values/     # strings, colors, themes
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml
└── README.md
```

---

## 🎨 Theme & Colors

The app uses a **dark cinema-style theme**:

| Color | Hex | Usage |
|---|---|---|
| Background Primary | `#121212` | Screen backgrounds |
| Background Secondary | `#1E1E1E` | Cards, app bar, nav bar |
| Background Tertiary | `#242424` | Active nav indicator |
| Primary Red | `#E50914` | Logout, accent |
| Primary Yellow | `#F5C518` | Selected nav item, ratings |
| Text Primary | `#FFFFFF` | Headings, labels |
| Text Secondary | `#B3B3B3` | Subtitles, hints |

---

## 📋 Permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

---

## 📄 License

This project is for educational purposes. Movie data is provided by [TMDB](https://www.themoviedb.org/).

> This product uses the TMDB API but is not endorsed or certified by TMDB.
