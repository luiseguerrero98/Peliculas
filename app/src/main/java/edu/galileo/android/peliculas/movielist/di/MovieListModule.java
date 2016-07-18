package edu.galileo.android.peliculas.movielist.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.peliculas.entities.Movie;
import edu.galileo.android.peliculas.lib.EventBus;
import edu.galileo.android.peliculas.lib.ImageLoader;
import edu.galileo.android.peliculas.movielist.MovieListInteractor;
import edu.galileo.android.peliculas.movielist.MovieListInteractorImpl;
import edu.galileo.android.peliculas.movielist.MovieListPresenter;
import edu.galileo.android.peliculas.movielist.MovieListPresenterImpl;
import edu.galileo.android.peliculas.movielist.MovieListRepository;
import edu.galileo.android.peliculas.movielist.MovieListRepositoryImpl;
import edu.galileo.android.peliculas.movielist.StoredMoviesInteractor;
import edu.galileo.android.peliculas.movielist.StoredMoviesInteractorImpl;
import edu.galileo.android.peliculas.movielist.ui.MovieListView;
import edu.galileo.android.peliculas.movielist.ui.adapters.OnItemClickListener;
import edu.galileo.android.peliculas.movielist.ui.adapters.MoviesAdapter;

/**
 * Created by Luis.
 */
@Module
public class MovieListModule {
    MovieListView view;
    OnItemClickListener onItemClickListener;

    public MovieListModule(MovieListView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides @Singleton
    MovieListView provideMovieListView() {
        return this.view;
    }

    @Provides @Singleton
    MovieListPresenter provideMovieListPresenter(EventBus eventBus, MovieListView view, MovieListInteractor listInteractor, StoredMoviesInteractor storedInteractor) {
        return new MovieListPresenterImpl(eventBus, view, listInteractor, storedInteractor);
    }

    @Provides @Singleton
    MovieListInteractor provideMovieListInteractor(MovieListRepository repository) {
        return new MovieListInteractorImpl(repository);
    }

    @Provides @Singleton
    StoredMoviesInteractor provideStoredMoviesInteractor(MovieListRepository repository) {
        return new StoredMoviesInteractorImpl(repository);
    }

    @Provides @Singleton
    MovieListRepository provideMovieListRepository(EventBus eventBus) {
        return new MovieListRepositoryImpl(eventBus);
    }

    @Provides @Singleton
    MoviesAdapter provideMoviesAdapter(List<Movie> movies, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        return new MoviesAdapter(movies, imageLoader, onItemClickListener);
    }

    @Provides @Singleton
    OnItemClickListener provideOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides @Singleton
    List<Movie> provideMoviesList() {
        return new ArrayList<Movie>();
    }

}
