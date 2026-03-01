package com.example.newreelmate.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * TMDB API Response model for genres endpoint
 */
public class TMDBGenreResponse {

    @SerializedName("genres")
    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Inner class for Genre
     */
    public static class Genre {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

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
    }
}

