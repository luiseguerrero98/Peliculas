package edu.galileo.android.peliculas.movielist.ui;

import java.util.List;

import edu.galileo.android.peliculas.entities.Movie;

/**
 * Created by Luis.
 */
public interface MovieListView {
    void setMovies(List<Movie> data);
    void movieUpdated();
    void movieDeleted(Movie movie);
}
