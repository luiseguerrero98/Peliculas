package edu.galileo.android.peliculas.movielist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.login.LoginManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.peliculas.FacebookMoviesApp;
import edu.galileo.android.peliculas.R;
import edu.galileo.android.peliculas.entities.Movie;
import edu.galileo.android.peliculas.login.ui.LoginActivity;
import edu.galileo.android.peliculas.movielist.MovieListPresenter;
import edu.galileo.android.peliculas.movielist.di.MovieListComponent;
import edu.galileo.android.peliculas.movielist.ui.adapters.OnItemClickListener;
import edu.galileo.android.peliculas.movielist.ui.adapters.MoviesAdapter;
import edu.galileo.android.peliculas.moviemain.ui.MovieMainActivity;

public class MovieListActivity extends AppCompatActivity implements MovieListView, OnItemClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private MoviesAdapter adapter;
    private MovieListPresenter presenter;
    private MovieListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
        presenter.getMovies();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.toolbar)
    public void onToolbarClick(){
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_main) {
            navigateToMainScreen();
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

    private void navigateToMainScreen() {
        startActivity(new Intent(this, MovieMainActivity.class));
    }

    private void setupInjection() {
        FacebookMoviesApp app = (FacebookMoviesApp)getApplication();
        component = app.getMovieListComponent(this, this, this);
        //app.getMovieListComponent(this, this, this).inject(this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    public MoviesAdapter getAdapter() {
        return component.getAdapter();
    }

    public MovieListPresenter getPresenter() {
        return component.getPresenter();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/movie/"+movie.getMovieId()));
        startActivity(intent);
    }

    @Override
    public void onFavClick(Movie movie) {
        presenter.toggleFavorite(movie);
    }

    @Override
    public void onDeleteClick(Movie movie) {
        presenter.removeMovie(movie);
    }

    @Override
    public void setMovies(List<Movie> data) {
        adapter.setMovies(data);
    }

    @Override
    public void movieUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void movieDeleted(Movie movie) {
        adapter.removeMovie(movie);
    }
}
