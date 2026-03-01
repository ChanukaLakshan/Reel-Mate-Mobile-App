package com.example.newreelmate.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * TMDB API Response model for movie list endpoint
 */
public class TMDBMovieResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<TMDBMovie> results;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;

    // Getters and Setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TMDBMovie> getResults() {
        return results;
    }

    public void setResults(List<TMDBMovie> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}

