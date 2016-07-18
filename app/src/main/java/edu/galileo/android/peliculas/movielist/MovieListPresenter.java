package edu.galileo.android.peliculas.movielist;

import edu.galileo.android.peliculas.entities.Movie;
import edu.galileo.android.peliculas.movielist.events.MovieListEvent;
import edu.galileo.android.peliculas.movielist.ui.MovieListView;

/**
 * Created by Luis.
 */
public interface MovieListPresenter {
    void onCreate();
    void onDestroy();

    void getMovies();
    void removeMovie(Movie movie);
    void toggleFavorite(Movie movie);
    void onEventMainThread(MovieListEvent event);

    MovieListView getView();
}
