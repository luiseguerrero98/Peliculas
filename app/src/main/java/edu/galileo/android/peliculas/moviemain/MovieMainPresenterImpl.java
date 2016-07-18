package edu.galileo.android.peliculas.moviemain;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.peliculas.entities.Movie;
import edu.galileo.android.peliculas.lib.EventBus;
import edu.galileo.android.peliculas.moviemain.events.MovieMainEvent;
import edu.galileo.android.peliculas.moviemain.ui.MovieMainView;

/**
 * Created by Luis.
 */
public class MovieMainPresenterImpl implements MovieMainPresenter {
    EventBus eventBus;
    MovieMainView view;
    SaveMovieInteractor saveMovie;
    GetNextMovieInteractor getNextMovie;

    public MovieMainPresenterImpl(EventBus eventBus, MovieMainView view, SaveMovieInteractor saveMovie, GetNextMovieInteractor getNextMovie) {
        this.view = view;
        this.eventBus = eventBus;
        this.saveMovie = saveMovie;
        this.getNextMovie = getNextMovie;
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
    public MovieMainView getView() {
        return this.view;
    }

    @Override
    public void saveMovie(Movie movie) {
        if (this.view != null){
            view.saveAnimation();
            view.hideUIElements();
            view.showProgress();
        }
        saveMovie.execute(movie);
    }

    @Override
    public void dismissMovie() {
        if (this.view != null) {
            view.dismissAnimation();
        }
        getNextMovie();
    }

    @Override
    public void getNextMovie(){
        if (this.view != null) {
            view.showProgress();
            view.hideUIElements();
        }
        getNextMovie.execute();

    }

    @Override
    @Subscribe
    public void onEventMainThread(MovieMainEvent event) {
        if (this.view != null){
            String error = event.getError();
            if (error == null) {
                if (event.getType() == MovieMainEvent.NEXT_EVENT) {
                    view.setMovie(event.getMovie());
                } else if (event.getType() == MovieMainEvent.SAVE_EVENT) {
                    view.onMovieSaved();
                    getNextMovie.execute();
                }

            } else {
                view.hideProgress();
                view.onGetMovieError(error);
            }
        }
    }
}
