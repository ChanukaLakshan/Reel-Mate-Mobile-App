package com.example.newreelmate.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit client for TMDB API
 */
public class TMDBRetrofitClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit;

    /**
     * Get or create Retrofit instance
     * @return Retrofit instance
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Get TMDB API service
     * @return TMDBApiService instance
     */
    public static TMDBApiService getApiService() {
        return getRetrofitInstance().create(TMDBApiService.class);
    }
}

