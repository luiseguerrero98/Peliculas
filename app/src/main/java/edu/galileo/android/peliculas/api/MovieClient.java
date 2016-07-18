package edu.galileo.android.peliculas.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Luis.
 */
public class MovieClient {
    private Retrofit retrofit;
    private final static String BASE_URL = "https://api.themoviedb.org/3/";

    public MovieClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MovieService getMovieService() {
        return retrofit.create(MovieService.class);
    }
}
