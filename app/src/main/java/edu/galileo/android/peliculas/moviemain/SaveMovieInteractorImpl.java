package edu.galileo.android.peliculas.moviemain;

import edu.galileo.android.peliculas.entities.Movie;

/**
 * Created by Luis.
 */
public class SaveMovieInteractorImpl implements SaveMovieInteractor {
    MovieMainRepository repository;

    public SaveMovieInteractorImpl(MovieMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Movie movie) {
        repository.saveMovie(movie);
    }
}
