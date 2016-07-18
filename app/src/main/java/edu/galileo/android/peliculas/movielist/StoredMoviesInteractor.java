package edu.galileo.android.peliculas.movielist;

import edu.galileo.android.peliculas.entities.Movie;

/**
 * Created by Luis.
 */
public interface StoredMoviesInteractor {
    void executeUpdate(Movie movie);
    void executeDelete(Movie movie);
}
