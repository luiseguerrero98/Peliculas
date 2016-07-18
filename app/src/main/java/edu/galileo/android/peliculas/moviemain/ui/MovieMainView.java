package edu.galileo.android.peliculas.moviemain.ui;

import edu.galileo.android.peliculas.entities.Movie;

/**
 * Created by Luis.
 */
public interface MovieMainView {
    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();
    void saveAnimation();
    void dismissAnimation();

    void onMovieSaved();
    void setMovie(Movie movie);
    void onGetMovieError(String error);
}
