package edu.galileo.android.peliculas.moviemain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.login.LoginManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.peliculas.FacebookMoviesApp;
import edu.galileo.android.peliculas.R;
import edu.galileo.android.peliculas.entities.Movie;
import edu.galileo.android.peliculas.lib.ImageLoader;
import edu.galileo.android.peliculas.login.ui.LoginActivity;
import edu.galileo.android.peliculas.movielist.ui.MovieListActivity;
import edu.galileo.android.peliculas.moviemain.MovieMainPresenter;
import edu.galileo.android.peliculas.moviemain.di.MovieMainComponent;

public class MovieMainActivity extends AppCompatActivity implements MovieMainView, SwipeGestureListener {
    @Bind(R.id.imgMovie)
    ImageView imgMovie;

    @Bind(R.id.imgKeep)
    ImageButton btnKeep;

    @Bind(R.id.imgDismiss)
    ImageButton btnDismiss;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.layoutContainer)
    RelativeLayout container;

    private Movie currentMovie;
    private ImageLoader imageLoader;
    private MovieMainPresenter presenter;
    private MovieMainComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_main);
        ButterKnife.bind(this);
        setupInjection();
        setupImageLoading();
        setupGestureDetection();

        presenter.onCreate();
        presenter.getNextMovie();
    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_list) {
            navigateToListScreen();
            return true;
        } else if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void navigateToListScreen() {
        startActivity(new Intent(this, MovieListActivity.class));
    }

    private void setupGestureDetection() {
        final GestureDetector gestureDetector = new GestureDetector(this, new SwipeGestureDetector(this));
        View.OnTouchListener gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        imgMovie.setOnTouchListener(gestureListener);
    }

    private void setupImageLoading() {
        imageLoader.setOnFinishedImageLoadingListener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                Snackbar.make(container, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgress();
                showUIElements();
                return false;
            }
        });
    }

    private void setupInjection() {
        FacebookMoviesApp app = (FacebookMoviesApp)getApplication();
        //app.getMovieMainComponent(this, this).inject(this);
        component = app.getMovieMainComponent(this, this);
        imageLoader = getImageLoader();
        presenter = getPresenter();
    }

    public ImageLoader getImageLoader() {
        return component.getImageLoader();
    }

    public MovieMainPresenter getPresenter() {
        return component.getPresenter();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUIElements() {
        btnKeep.setVisibility(View.VISIBLE);
        btnDismiss.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        btnKeep.setVisibility(View.GONE);
        btnDismiss.setVisibility(View.GONE);
    }

    private void clearImage(){
        imgMovie.setImageResource(0);
    }

    @Override
    public void saveAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_save);
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearImage();
            }
        });
        imgMovie.startAnimation(anim);
    }

    @Override
    public void dismissAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_dismiss);
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearImage();
            }
        });
        imgMovie.startAnimation(anim);
    }

    @Override
    @OnClick(R.id.imgKeep)
    public void onKeep() {
        if (currentMovie != null) {
            presenter.saveMovie(currentMovie);
        }
    }

    @Override
    @OnClick(R.id.imgDismiss)
    public void onDismiss() {
        presenter.dismissMovie();
    }

    @Override
    public void setMovie(Movie movie) {
        this.currentMovie = movie;
        imageLoader.load(imgMovie, "https://image.tmdb.org/t/p/original/"+movie.getImageURL());
        //imageLoader.urlToDraw(container, "https://image.tmdb.org/t/p/original/"+currentMovie.getBackgroundURL());
    }

    @Override
    public void onGetMovieError(String error) {
        String msgError = String.format(getString(R.string.moviemain_error), error);
        Snackbar.make(container, msgError, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieSaved() {
        Snackbar.make(container, R.string.moviemain_notice_saved, Snackbar.LENGTH_SHORT).show();
    }
}
