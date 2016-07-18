package edu.galileo.android.peliculas.moviemain;

import java.util.Random;

import edu.galileo.android.peliculas.BuildConfig;
import edu.galileo.android.peliculas.api.MovieSearchResponse;
import edu.galileo.android.peliculas.api.MovieService;
import edu.galileo.android.peliculas.entities.Movie;
import edu.galileo.android.peliculas.lib.EventBus;
import edu.galileo.android.peliculas.moviemain.events.MovieMainEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luis.
 */
public class MovieMainRepositoryImpl implements MovieMainRepository {
    private int moviePage;
    private EventBus eventBus;
    private MovieService service;

    public MovieMainRepositoryImpl(EventBus eventBus, MovieService service) {
        this.eventBus = eventBus;
        this.service = service;
        this.moviePage = new Random().nextInt(RECIPE_RANGE);
    }

    @Override
    public void setMoviePage(int moviePage) {
        this.moviePage = moviePage;
    }

    @Override
    public void getNextMovie() {
        Call<MovieSearchResponse> call = service.search(BuildConfig.FOOD_API_KEY, RECENT_SORT, moviePage);
        call.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.isSuccess()) {
                    MovieSearchResponse movieSearchResponse = response.body();
                        setMoviePage(new Random().nextInt(RECIPE_RANGE));
                        Movie movie = movieSearchResponse.getFirstMovie();
                        if (movie != null) {
                            post(movie);
                        } else {
                            post(response.message(), MovieMainEvent.NEXT_EVENT);
                        }
                } else {
                    post(response.message(), MovieMainEvent.NEXT_EVENT);
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                post(t.getLocalizedMessage(), MovieMainEvent.NEXT_EVENT);
            }
        });
    }

    @Override
    public void saveMovie(Movie movie) {
        movie.save();
        post();
    }

    private void post() {
        MovieMainEvent event = new MovieMainEvent();
        event.setType(MovieMainEvent.SAVE_EVENT);
        eventBus.post(event);
    }

    private void post(String error, int type) {
        MovieMainEvent event = new MovieMainEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }

    private void post(Movie movie) {
        MovieMainEvent event = new MovieMainEvent();
        event.setType(MovieMainEvent.NEXT_EVENT);
        event.setMovie(movie);
        eventBus.post(event);
    }
}
