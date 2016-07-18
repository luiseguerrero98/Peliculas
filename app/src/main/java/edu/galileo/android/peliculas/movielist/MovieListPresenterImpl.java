package edu.galileo.android.peliculas.movielist;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.peliculas.entities.Movie;
import edu.galileo.android.peliculas.lib.EventBus;
import edu.galileo.android.peliculas.movielist.events.MovieListEvent;
import edu.galileo.android.peliculas.movielist.ui.MovieListView;

/**
 * Created by Luis.
 */
public class MovieListPresenterImpl implements MovieListPresenter {
    private EventBus eventBus;
    private MovieListView view;
    private MovieListInteractor listInteractor;
    private StoredMoviesInteractor storedInteractor;

    public MovieListPresenterImpl(EventBus eventBus, MovieListView view, MovieListInteractor listInteractor, StoredMoviesInteractor storedInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.listInteractor = listInteractor;
        this.storedInteractor = storedInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public MovieListView getView() {
        return this.view;
    }

    @Override
    public void getMovies() {
        listInteractor.execute();
    }

    @Override
    public void toggleFavorite(Movie movie) {
        boolean fav = movie.getFavorite();
        movie.setFavorite(!fav);
        storedInteractor.executeUpdate(movie);
    }

    @Override
    public void removeMovie(Movie movie) {
        storedInteractor.executeDelete(movie);
    }

    @Override
    @Subscribe
    public void onEventMainThread(MovieListEvent event) {
        if (this.view != null) {
            switch (event.getType()){
                case MovieListEvent.READ_EVENT:
                    view.setMovies(event.getMovies());
                    break;
                case MovieListEvent.UPDATE_EVENT:
                    view.movieUpdated();
                    break;
                case MovieListEvent.DELETE_EVENT:
                    Movie movie = event.getMovies().get(0);
                    view.movieDeleted(movie);
                    break;

            }
        }
    }
}
