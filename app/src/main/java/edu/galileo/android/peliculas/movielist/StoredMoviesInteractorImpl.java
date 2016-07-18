package edu.galileo.android.peliculas.movielist;

import edu.galileo.android.peliculas.entities.Movie;

/**
 * Created by Luis.
 */
public class StoredMoviesInteractorImpl implements StoredMoviesInteractor {
    private MovieListRepository repository;

    public StoredMoviesInteractorImpl(MovieListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeUpdate(Movie movie) {
        repository.updateMovie(movie);
    }

    @Override
    public void executeDelete(Movie movie) {
        repository.removeMovie(movie);
    }
}
