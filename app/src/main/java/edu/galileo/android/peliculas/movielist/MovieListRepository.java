package edu.galileo.android.peliculas.movielist;

import edu.galileo.android.peliculas.entities.Movie;

/**
 * Created by Luis.
 */
public interface MovieListRepository {
    void getSavedMovies();
    void updateMovie(Movie movie);
    void removeMovie(Movie movie);
}
