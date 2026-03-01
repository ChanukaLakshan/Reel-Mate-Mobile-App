package com.example.newreelmate.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * TMDB Movie model from API response
 */
public class TMDBMovie {

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

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("credits")
    private CreditsResponse credits;

    // Constructors
    public TMDBMovie() {
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

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public CreditsResponse getCredits() {
        return credits;
    }

    public void setCredits(CreditsResponse credits) {
        this.credits = credits;
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
     * Inner class for credits response
     */
    public static class CreditsResponse {
        @SerializedName("cast")
        private List<CastMember> cast;

        @SerializedName("crew")
        private List<CrewMember> crew;

        public List<CastMember> getCast() {
            return cast;
        }

        public void setCast(List<CastMember> cast) {
            this.cast = cast;
        }

        public List<CrewMember> getCrew() {
            return crew;
        }

        public void setCrew(List<CrewMember> crew) {
            this.crew = crew;
        }
    }

    /**
     * Inner class for cast member
     */
    public static class CastMember {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("character")
        private String character;

        @SerializedName("profile_path")
        private String profilePath;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCharacter() {
            return character;
        }

        public String getProfilePath() {
            return profilePath;
        }
    }

    /**
     * Inner class for crew member
     */
    public static class CrewMember {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("job")
        private String job;

        @SerializedName("department")
        private String department;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getJob() {
            return job;
        }

        public String getDepartment() {
            return department;
        }
    }
}

