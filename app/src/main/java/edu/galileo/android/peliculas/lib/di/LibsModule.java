package edu.galileo.android.peliculas.lib.di;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.peliculas.lib.EventBus;
import edu.galileo.android.peliculas.lib.GlideImageLoader;
import edu.galileo.android.peliculas.lib.GreenRobotEventBus;
import edu.galileo.android.peliculas.lib.ImageLoader;

/**
 * Created by Luis.
 */
@Module
public class LibsModule {
    Activity activity;

    public LibsModule() {
    }
    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Activity activity) {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (activity != null) {
            imageLoader.setLoaderContext(activity);
        }
        return imageLoader;
    }

    @Provides
    @Singleton
    Activity provideActivity(){
        return this.activity;
    }

}
