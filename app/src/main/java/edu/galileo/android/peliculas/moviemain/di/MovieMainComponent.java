package edu.galileo.android.peliculas.moviemain.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.peliculas.lib.ImageLoader;
import edu.galileo.android.peliculas.lib.di.LibsModule;
import edu.galileo.android.peliculas.moviemain.MovieMainPresenter;

/**
 * Created by Luis.
 */
@Singleton
@Component(modules = {MovieMainModule.class, LibsModule.class})
public interface MovieMainComponent {
    //void inject(MovieMainActivity activity);
    ImageLoader getImageLoader();
    MovieMainPresenter getPresenter();
}
