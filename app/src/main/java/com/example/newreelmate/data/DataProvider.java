package com.example.newreelmate.data;

import com.example.newreelmate.models.Movie;
import com.example.newreelmate.models.MovieList;
import com.example.newreelmate.models.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProvider {

    public static List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie(
            1,
            "Midnight Shadows",
            "https://images.unsplash.com/photo-1653853301139-f57c5fdc069a",
            "Thriller",
            "2024",
            4.5
        ));

        movies.add(new Movie(
            2,
            "Beyond the Stars",
            "https://images.unsplash.com/photo-1619960535209-fc795018bbe1",
            "Sci-Fi",
            "2024",
            4.8
        ));

        movies.add(new Movie(
            3,
            "Love in Paris",
            "https://images.unsplash.com/photo-1708787788824-07d6d97b0111",
            "Romance",
            "2024",
            4.3
        ));

        movies.add(new Movie(
            4,
            "Dark Manor",
            "https://images.unsplash.com/photo-1767048264833-5b65aacd1039",
            "Horror",
            "2023",
            4.1
        ));

        movies.add(new Movie(
            5,
            "The Last Stand",
            "https://images.unsplash.com/photo-1645808651017-c5e3018553c7",
            "Action",
            "2024",
            4.6
        ));

        movies.add(new Movie(
            6,
            "Laugh Out Loud",
            "https://images.unsplash.com/photo-1604674725989-52c312835516",
            "Comedy",
            "2024",
            4.4
        ));

        return movies;
    }

    public static Movie getMovieById(int id) {
        Movie movie = new Movie(
            1,
            "Midnight Shadows",
            "https://images.unsplash.com/photo-1653853301139-f57c5fdc069a",
            Arrays.asList("Thriller", "Mystery", "Drama"),
            "2024",
            4.5,
            "2h 15m",
            "Sofia Chen",
            "A gripping psychological thriller that follows a detective's journey into the depths of a mysterious case that blurs the line between reality and illusion. As shadows grow longer, the truth becomes harder to find.",
            Arrays.asList("Emma Thompson", "John Rivera", "Michelle Park", "David Chen")
        );
        return movie;
    }

    public static List<Review> getReviews() {
        List<Review> reviews = new ArrayList<>();

        reviews.add(new Review(
            1,
            "Sarah Miller",
            "https://images.unsplash.com/photo-1631885628966-a14af9faaa9b",
            5,
            "Absolutely mind-blowing! The cinematography and plot twists kept me on the edge of my seat.",
            24,
            "2 days ago"
        ));

        reviews.add(new Review(
            2,
            "Marcus Johnson",
            "https://images.unsplash.com/photo-1627729205753-52d2ddeefce1",
            4,
            "Great movie with fantastic performances. Some parts were a bit slow but overall excellent.",
            18,
            "5 days ago"
        ));

        return reviews;
    }

    public static List<MovieList> getMovieLists() {
        List<MovieList> lists = new ArrayList<>();

        lists.add(new MovieList(
            1,
            "My Favorite Thrillers",
            "Edge-of-your-seat suspense movies",
            12,
            "Jan 15, 2026",
            Arrays.asList(
                "https://images.unsplash.com/photo-1653853301139-f57c5fdc069a",
                "https://images.unsplash.com/photo-1563905463861-7d77975b3a44",
                "https://images.unsplash.com/photo-1767048264833-5b65aacd1039"
            )
        ));

        lists.add(new MovieList(
            2,
            "Sci-Fi Masterpieces",
            "Mind-bending science fiction films",
            8,
            "Jan 20, 2026",
            Arrays.asList(
                "https://images.unsplash.com/photo-1619960535209-fc795018bbe1",
                "https://images.unsplash.com/photo-1645808651017-c5e3018553c7",
                "https://images.unsplash.com/photo-1653853301139-f57c5fdc069a"
            )
        ));

        lists.add(new MovieList(
            3,
            "Romantic Getaways",
            "Love stories that warm the heart",
            15,
            "Feb 1, 2026",
            Arrays.asList(
                "https://images.unsplash.com/photo-1708787788824-07d6d97b0111",
                "https://images.unsplash.com/photo-1604674725989-52c312835516",
                "https://images.unsplash.com/photo-1771502045792-232148a24dae"
            )
        ));

        return lists;
    }
}

