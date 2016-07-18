package edu.galileo.android.peliculas.movielist;

import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.Arrays;
import java.util.List;

import edu.galileo.android.peliculas.entities.Movie;
import edu.galileo.android.peliculas.lib.EventBus;
import edu.galileo.android.peliculas.movielist.events.MovieListEvent;

/**
 * Created by Luis.
 */
public class MovieListRepositoryImpl implements MovieListRepository {
    private EventBus eventBus;

    public MovieListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedMovies() {
        FlowCursorList<Movie> storedMovies = new FlowCursorList<Movie>(true, Movie.class);
        post(MovieListEvent.READ_EVENT, storedMovies.getAll());
    }

    @Override
    public void updateMovie(Movie movie) {
        movie.update();
        post(MovieListEvent.UPDATE_EVENT);
    }

    @Override
    public void removeMovie(Movie movie) {
        movie.delete();
        post(MovieListEvent.DELETE_EVENT, Arrays.asList(movie));
    }

    private void post(int type, List<Movie> movies) {
        MovieListEvent event = new MovieListEvent();
        event.setMovies(movies);
        event.setType(type);
        eventBus.post(event);
    }

    private void post(int type) {
        post(type, null);
    }

}
