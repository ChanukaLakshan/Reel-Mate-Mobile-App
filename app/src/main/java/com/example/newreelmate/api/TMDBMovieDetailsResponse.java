package com.example.newreelmate.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * TMDB API Response model for movie details endpoint
 */
public class TMDBMovieDetailsResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("genres")
    private List<GenreDetail> genres;

    @SerializedName("credits")
    private TMDBMovie.CreditsResponse credits;

    @SerializedName("videos")
    private VideosResponse videos;

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

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public List<GenreDetail> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDetail> genres) {
        this.genres = genres;
    }

    public TMDBMovie.CreditsResponse getCredits() {
        return credits;
    }

    public void setCredits(TMDBMovie.CreditsResponse credits) {
        this.credits = credits;
    }

    public VideosResponse getVideos() {
        return videos;
    }

    public void setVideos(VideosResponse videos) {
        this.videos = videos;
    }

    public String getPosterUrl() {
        if (posterPath == null || posterPath.isEmpty()) {
            return null;
        }
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }

    public String getBackdropUrl() {
        if (backdropPath == null || backdropPath.isEmpty()) {
            return null;
        }
        return "https://image.tmdb.org/t/p/w1280" + backdropPath;
    }

    /**
     * Inner class for Genre details
     */
    public static class GenreDetail {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Inner class for Videos response
     */
    public static class VideosResponse {
        @SerializedName("results")
        private List<Video> results;

        public List<Video> getResults() {
            return results;
        }

        public void setResults(List<Video> results) {
            this.results = results;
        }
    }

    /**
     * Inner class for Video
     */
    public static class Video {
        @SerializedName("id")
        private String id;

        @SerializedName("key")
        private String key;

        @SerializedName("name")
        private String name;

        @SerializedName("site")
        private String site;

        @SerializedName("type")
        private String type;

        public String getId() {
            return id;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String getSite() {
            return site;
        }

        public String getType() {
            return type;
        }

        public String getYouTubeUrl() {
            if ("YouTube".equals(site)) {
                return "https://www.youtube.com/watch?v=" + key;
            }
            return null;
        }
    }
}

