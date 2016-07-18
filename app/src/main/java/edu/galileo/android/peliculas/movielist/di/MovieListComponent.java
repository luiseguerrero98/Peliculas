package edu.galileo.android.peliculas.movielist.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.peliculas.lib.di.LibsModule;
import edu.galileo.android.peliculas.movielist.MovieListPresenter;
import edu.galileo.android.peliculas.movielist.ui.adapters.MoviesAdapter;

/**
 * Created by Luis.
 */
@Singleton
@Component(modules = {MovieListModule.class, LibsModule.class})
public interface MovieListComponent {
    //void inject(MovieListActivity activity);
    MovieListPresenter getPresenter();
    MoviesAdapter getAdapter();
}
