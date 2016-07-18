package edu.galileo.android.peliculas.moviemain;

import java.util.Random;

/**
 * Created by Luis.
 */
public class GetNextMovieInteractorImpl implements GetNextMovieInteractor {
    MovieMainRepository repository;

    public GetNextMovieInteractorImpl(MovieMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        int moviePage = new Random().nextInt(MovieMainRepository.RECIPE_RANGE);
        repository.setMoviePage(moviePage);
        repository.getNextMovie();
    }

}
