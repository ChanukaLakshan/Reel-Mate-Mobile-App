# ReelMate - Movie Watchlist Android App

A modern Android movie watchlist application built in Java, inspired by popular movie management platforms. ReelMate helps users discover, track, and review movies with an elegant dark-themed interface.

## Features

### 🎬 Core Features
- **User Authentication**: Login and registration screens with modern UI
- **Movie Discovery**: Browse and search through a curated list of movies
- **Watchlist Management**: Add movies to your personal watchlist
- **Movie Details**: View comprehensive information including synopsis, cast, director, and ratings
- **Reviews & Ratings**: Read user reviews and write your own movie reviews
- **Custom Lists**: Create and manage custom movie lists
- **Profile Management**: Track your watched movies, lists, and reviews

### 📱 Screens
1. **Login Screen**: Beautiful authentication with email/password
2. **Home Screen**: Main feed with movies and search functionality
3. **Movie Details Screen**: Detailed view with poster, info, and reviews
4. **My Lists Screen**: Manage your custom movie lists
5. **List Detail Screen**: View movies in a specific list
6. **Profile Screen**: User stats and settings
7. **Review Screen**: Write and submit movie reviews

## Technical Stack

### Architecture & Design Patterns
- **Language**: Java
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 36 (Android 15)
- **UI Pattern**: Activity-based architecture
- **Data**: Mock data provider (ready for API integration)

### Libraries & Dependencies
- **AndroidX Libraries**:
  - AppCompat
  - Material Design Components
  - RecyclerView
  - CardView
  - ConstraintLayout

- **Image Loading**:
  - Glide 4.16.0 (with annotation processor)

- **Networking** (for future API integration):
  - Retrofit 2.9.0
  - Gson Converter

- **Navigation**:
  - ViewPager2
  - Navigation Component

### UI/UX Design
- **Color Scheme**: Dark theme with Netflix-inspired palette
  - Primary Red: #E50914
  - Primary Yellow: #F5C518
  - Background: #121212, #1E1E1E, #242424
  - Text: #FFFFFF, #B3B3B3

- **Design Elements**:
  - Rounded corners (24dp radius)
  - Card-based layouts
  - Material Design icons
  - Smooth transitions
  - Responsive layouts

## Project Structure

```
newReelMate/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/newreelmate/
│   │   │   │   ├── adapters/
│   │   │   │   │   ├── MovieAdapter.java
│   │   │   │   │   ├── MovieListAdapter.java
│   │   │   │   │   └── ReviewAdapter.java
│   │   │   │   ├── data/
│   │   │   │   │   └── DataProvider.java
│   │   │   │   ├── models/
│   │   │   │   │   ├── Movie.java
│   │   │   │   │   ├── MovieList.java
│   │   │   │   │   └── Review.java
│   │   │   │   ├── HomeActivity.java
│   │   │   │   ├── LoginActivity.java
│   │   │   │   ├── MovieDetailsActivity.java
│   │   │   │   ├── MyListsActivity.java
│   │   │   │   ├── ListDetailActivity.java
│   │   │   │   ├── ProfileActivity.java
│   │   │   │   └── ReviewActivity.java
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── button_primary.xml
│   │   │   │   │   ├── button_secondary.xml
│   │   │   │   │   ├── button_yellow.xml
│   │   │   │   │   ├── button_success.xml
│   │   │   │   │   └── logo_background.xml
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_login.xml
│   │   │   │   │   ├── activity_home.xml
│   │   │   │   │   ├── activity_movie_details.xml
│   │   │   │   │   ├── activity_my_lists.xml
│   │   │   │   │   ├── activity_list_detail.xml
│   │   │   │   │   ├── activity_profile.xml
│   │   │   │   │   ├── activity_review.xml
│   │   │   │   │   ├── item_movie.xml
│   │   │   │   │   ├── item_movie_list.xml
│   │   │   │   │   └── item_review.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── strings.xml
│   │   │   │   └── AndroidManifest.xml
│   │   └── build.gradle.kts
│   └── ...
```

## Setup & Installation

### Prerequisites
- Android Studio (latest version recommended)
- JDK 11 or higher
- Android SDK with API 36
- Gradle 8.0+

### Installation Steps

1. **Clone the repository** (or open the existing project):
   ```bash
   cd "C:\Users\Chanuka Lakshan\AndroidStudioProjects\newReelMate"
   ```

2. **Open in Android Studio**:
   - File → Open → Select the `newReelMate` folder

3. **Sync Gradle**:
   - Click "Sync Project with Gradle Files"
   - Wait for dependencies to download

4. **Build the project**:
   ```bash
   ./gradlew build
   ```

5. **Run on emulator or device**:
   - Click the "Run" button in Android Studio
   - Or use: `./gradlew installDebug`

## Usage

### Default Credentials
The app currently uses mock authentication. Any email/password combination will work for login.

### Navigation Flow
1. **Login** → Enter any credentials
2. **Home** → Browse movies, add to watchlist
3. **Movie Details** → Click on any movie to view details
4. **My Lists** → Access from the top menu to view your custom lists
5. **Profile** → Access user profile and statistics
6. **Write Review** → Click "Write Review" on movie details page

## Data Models

### Movie
- id, title, poster, genre(s), year, rating
- runtime, director, description, cast
- inWatchlist, isWatched flags

### MovieList
- id, name, description, movieCount
- createdDate, previewImages

### Review
- id, userName, userAvatar, rating
- comment, likes, date, isLiked flag

## API Integration (Future)

The app is structured to easily integrate with a REST API:

1. Replace mock data in `DataProvider.java`
2. Use Retrofit services for API calls
3. Implement proper error handling
4. Add loading states and progress indicators

Example structure:
```java
public interface MovieApiService {
    @GET("movies")
    Call<List<Movie>> getMovies();
    
    @GET("movies/{id}")
    Call<Movie> getMovieDetails(@Path("id") int id);
    
    @POST("reviews")
    Call<Review> submitReview(@Body Review review);
}
```

## Customization

### Changing Colors
Edit `res/values/colors.xml` to customize the color scheme.

### Adding More Movies
Update `DataProvider.java` to add more movie data or connect to an API.

### Modifying Layouts
All layouts are in `res/layout/` and can be customized using the visual editor or XML.

## Building for Release

1. **Generate signed APK**:
   - Build → Generate Signed Bundle / APK
   - Select APK
   - Create or select a keystore
   - Build release APK

2. **Or via command line**:
   ```bash
   ./gradlew assembleRelease
   ```

## Known Issues & Future Enhancements

### Known Issues
- Mock data only (no real API integration)
- Image loading requires internet connection
- No data persistence (using in-memory storage)

### Future Enhancements
- [ ] Real API integration (TMDB API)
- [ ] Room database for offline storage
- [ ] Firebase authentication
- [ ] Social sharing features
- [ ] Advanced search filters
- [ ] Notifications for new releases
- [ ] Dark/Light theme toggle
- [ ] Tablet UI optimization
- [ ] Movie trailers (YouTube API)
- [ ] User-to-user list sharing

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is created for educational purposes.

## Acknowledgments

- UI Design inspired by the ReelMate Figma design
- Icons from Material Design Icons
- Sample images from Unsplash (placeholders)

## Contact

For questions or support, please contact the development team.

---

**Built with ❤️ using Java and Android Studio**

