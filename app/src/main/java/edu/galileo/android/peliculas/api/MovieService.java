package edu.galileo.android.peliculas.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Luis.
 */
public interface MovieService {
    @GET("discover/movie")
    Call<MovieSearchResponse> search(@Query("api_key") String key,
                                      @Query("sort_by") String sort,
                                      @Query("page") int page);

    @GET("movie/popular")
    Call<MovieSearchResponse> searchPopular(@Query("api_key") String key);


}
