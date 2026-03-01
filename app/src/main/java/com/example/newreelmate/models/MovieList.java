package com.example.newreelmate.models;

import java.util.List;

public class MovieList {
    private int id;
    private String name;
    private String description;
    private int movieCount;
    private String createdDate;
    private List<String> previewImages;
    private List<Movie> movies;

    public MovieList(int id, String name, String description, int movieCount,
                     String createdDate, List<String> previewImages) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.movieCount = movieCount;
        this.createdDate = createdDate;
        this.previewImages = previewImages;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getPreviewImages() {
        return previewImages;
    }

    public void setPreviewImages(List<String> previewImages) {
        this.previewImages = previewImages;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

