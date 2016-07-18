package edu.galileo.android.peliculas.moviemain.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.peliculas.api.MovieClient;
import edu.galileo.android.peliculas.api.MovieService;
import edu.galileo.android.peliculas.lib.EventBus;
import edu.galileo.android.peliculas.moviemain.GetNextMovieInteractor;
import edu.galileo.android.peliculas.moviemain.GetNextMovieInteractorImpl;
import edu.galileo.android.peliculas.moviemain.MovieMainPresenter;
import edu.galileo.android.peliculas.moviemain.MovieMainPresenterImpl;
import edu.galileo.android.peliculas.moviemain.MovieMainRepository;
import edu.galileo.android.peliculas.moviemain.MovieMainRepositoryImpl;
import edu.galileo.android.peliculas.moviemain.SaveMovieInteractor;
import edu.galileo.android.peliculas.moviemain.SaveMovieInteractorImpl;
import edu.galileo.android.peliculas.moviemain.ui.MovieMainView;

/**
 * Created by Luis.
 */
@Module
public class MovieMainModule {
    MovieMainView view;

    public MovieMainModule(MovieMainView view) {
        this.view = view;
    }

    @Provides @Singleton
    MovieMainView provideMovieMainView() {
        return this.view;
    }

    @Provides @Singleton
    MovieMainPresenter provideMovieMainPresenter(EventBus eventBus, MovieMainView view, SaveMovieInteractor save, GetNextMovieInteractor getNext) {
        return new MovieMainPresenterImpl(eventBus, view, save, getNext);
    }

    @Provides @Singleton
    SaveMovieInteractor provideSaveMovieInteractor(MovieMainRepository repository) {
        return new SaveMovieInteractorImpl(repository);
    }

    @Provides @Singleton
    GetNextMovieInteractor provideGetNextMovieInteractor(MovieMainRepository repository) {
        return new GetNextMovieInteractorImpl(repository);
    }

    @Provides @Singleton
    MovieMainRepository provideMovieMainRepository(EventBus eventBus, MovieService service) {
        return new MovieMainRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    MovieService provideMovieService() {
        MovieClient client = new MovieClient();
        return client.getMovieService();
    }
}
