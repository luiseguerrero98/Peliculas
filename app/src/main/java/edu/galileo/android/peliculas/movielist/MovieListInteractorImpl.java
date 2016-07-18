package edu.galileo.android.peliculas.movielist;

/**
 * Created by Luis.
 */
public class MovieListInteractorImpl implements MovieListInteractor {
    private MovieListRepository repository;

    public MovieListInteractorImpl(MovieListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getSavedMovies();
    }
}
