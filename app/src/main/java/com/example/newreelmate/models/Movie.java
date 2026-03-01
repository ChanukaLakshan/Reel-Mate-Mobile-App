package com.example.newreelmate.models;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private String poster;
    private String genre;
    private String year;
    private double rating;
    private String runtime;
    private String director;
    private String description;
    private List<String> genres;
    private List<String> cast;
    private boolean inWatchlist;
    private boolean isWatched;

    public Movie(int id, String title, String poster, String genre, String year, double rating) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.inWatchlist = false;
        this.isWatched = false;
    }

    // Full constructor
    public Movie(int id, String title, String poster, List<String> genres, String year,
                 double rating, String runtime, String director, String description, List<String> cast) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.genres = genres;
        this.year = year;
        this.rating = rating;
        this.runtime = runtime;
        this.director = director;
        this.description = description;
        this.cast = cast;
        this.inWatchlist = false;
        this.isWatched = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public boolean isInWatchlist() {
        return inWatchlist;
    }

    public void setInWatchlist(boolean inWatchlist) {
        this.inWatchlist = inWatchlist;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }
}

