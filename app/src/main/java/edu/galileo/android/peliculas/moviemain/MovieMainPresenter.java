package edu.galileo.android.peliculas.moviemain;

import edu.galileo.android.peliculas.entities.Movie;
import edu.galileo.android.peliculas.moviemain.events.MovieMainEvent;
import edu.galileo.android.peliculas.moviemain.ui.MovieMainView;

/**
 * Created by Luis.
 */
public interface MovieMainPresenter {
    void onCreate();
    void onDestroy();

    void dismissMovie();
    void getNextMovie();
    void saveMovie(Movie movie);
    void onEventMainThread(MovieMainEvent event);

    MovieMainView getView();
}
