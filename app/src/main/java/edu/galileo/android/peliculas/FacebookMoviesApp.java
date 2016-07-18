package edu.galileo.android.peliculas;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.raizlabs.android.dbflow.config.FlowManager;

import edu.galileo.android.peliculas.lib.di.LibsModule;
import edu.galileo.android.peliculas.movielist.di.DaggerMovieListComponent;
import edu.galileo.android.peliculas.movielist.di.MovieListComponent;
import edu.galileo.android.peliculas.movielist.di.MovieListModule;
import edu.galileo.android.peliculas.movielist.ui.MovieListActivity;
import edu.galileo.android.peliculas.movielist.ui.MovieListView;
import edu.galileo.android.peliculas.movielist.ui.adapters.OnItemClickListener;
import edu.galileo.android.peliculas.moviemain.di.DaggerMovieMainComponent;
import edu.galileo.android.peliculas.moviemain.di.MovieMainComponent;
import edu.galileo.android.peliculas.moviemain.di.MovieMainModule;
import edu.galileo.android.peliculas.moviemain.ui.MovieMainActivity;
import edu.galileo.android.peliculas.moviemain.ui.MovieMainView;

/**
 * Created by Luis.
 */
public class FacebookMoviesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
        initFacebook();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public MovieMainComponent getMovieMainComponent(MovieMainActivity activity, MovieMainView view) {
        return DaggerMovieMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .movieMainModule(new MovieMainModule(view))
                .build();
    }

    public MovieListComponent getMovieListComponent(MovieListActivity activity, MovieListView view, OnItemClickListener onItemClickListener) {
        return DaggerMovieListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .movieListModule(new MovieListModule(view, onItemClickListener))
                .build();
    }

}
