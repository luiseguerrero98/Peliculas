package edu.galileo.android.peliculas.moviemain.events;

import edu.galileo.android.peliculas.entities.Movie;

/**
 * Created by Luis.
 */
public class MovieMainEvent {
    private int type;
    private String error;
    private Movie movie;

    public final static int NEXT_EVENT = 0;
    public final static int SAVE_EVENT = 1;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
