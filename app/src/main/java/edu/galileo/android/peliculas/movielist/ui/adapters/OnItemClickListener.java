package edu.galileo.android.peliculas.movielist.ui.adapters;

import edu.galileo.android.peliculas.entities.Movie;

/**
 * Created by Luis.
 */
public interface OnItemClickListener {
    void onFavClick(Movie movie);
    void onItemClick(Movie movie);
    void onDeleteClick(Movie movie);
}
