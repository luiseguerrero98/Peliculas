package edu.galileo.android.peliculas.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import edu.galileo.android.peliculas.entities.Movie;

/**
 * Created by Luis.
 */
public class MovieSearchResponse {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Movie getFirstMovie(){
        Movie first = null;
        if (!movies.isEmpty()) {
            first = movies.get(0);
        }
        return first;
    }
}
