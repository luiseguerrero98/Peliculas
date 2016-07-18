package edu.galileo.android.peliculas.moviemain;

import edu.galileo.android.peliculas.entities.Movie;

/**
 * Created by Luis.
 */
public interface MovieMainRepository {
    public final static int COUNT = 1;
    public final static String RECENT_SORT = "popularity.desc";
    public final static int RECIPE_RANGE = 1000;

    void getNextMovie();
    void saveMovie(Movie movie);
    void setMoviePage(int moviePage);
}
