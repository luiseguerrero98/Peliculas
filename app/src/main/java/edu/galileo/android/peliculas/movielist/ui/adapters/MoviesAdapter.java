package edu.galileo.android.peliculas.movielist.ui.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.SendButton;
import com.facebook.share.widget.ShareButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.peliculas.BuildConfig;
import edu.galileo.android.peliculas.R;
import edu.galileo.android.peliculas.entities.Movie;
import edu.galileo.android.peliculas.lib.ImageLoader;

/**
 * Created by Luis.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    List<Movie> movies;
    ImageLoader imageLoader;
    OnItemClickListener onItemClickListener;

    public MoviesAdapter(List<Movie> movies, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.movies = movies;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void removeMovie(Movie movie){
        movies.remove(movie);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_stored_movies, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);

        imageLoader.load(holder.imgMovie, "https://image.tmdb.org/t/p/original/"+currentMovie.getImageURL());
        holder.txtMovieName.setText(currentMovie.getTitle());
        holder.imgFav.setTag(currentMovie.getFavorite());
        if (currentMovie.getFavorite()) {
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_off);
        }
        holder.setOnItemClickListener(currentMovie, this.onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgMovie)
        ImageView imgMovie;
        @Bind(R.id.txtMovieName)
        TextView txtMovieName;
        @Bind(R.id.imgFav)
        ImageButton imgFav;
        @Bind(R.id.fbShare)
        ShareButton imgShare;
        @Bind(R.id.fbSend)
        SendButton btnSend;
        @Bind(R.id.imgDelete)
        ImageButton imgDelete;

        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final Movie movie,
                                           final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(movie);
                }
            });

            imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onFavClick(movie);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteClick(movie);
                }
            });
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://www.themoviedb.org/movie/"+movie.getMovieId()))
                        .build();
                imgShare.setShareContent(content);
                btnSend.setShareContent(content);
        }
    }
}
