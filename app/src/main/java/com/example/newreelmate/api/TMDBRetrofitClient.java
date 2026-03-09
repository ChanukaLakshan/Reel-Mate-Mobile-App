package com.example.newreelmate.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit client for TMDB API with Bearer token auth
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
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("Authorization", "Bearer " + TMDBConfig.TMDB_READ_ACCESS_TOKEN)
                                .header("accept", "application/json")
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
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
