package edu.galileo.android.peliculas.entities;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import edu.galileo.android.peliculas.db.MoviesDatabase;

/**
 * Created by Luis.
 */
@Table(database = MoviesDatabase.class)
public class Movie extends BaseModel {
    @SerializedName("id")
    @PrimaryKey private String movieId;

    @Column private String title;

    @SerializedName("poster_path")
    @Column private String imageURL;

    public String getBackgroundURL() {
        return backgroundURL;
    }

    public void setBackgroundURL(String backgroundURL) {
        this.backgroundURL = backgroundURL;
    }

    @SerializedName("backdrop_path")
    @Column private String backgroundURL;

    @Column private boolean favorite;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj instanceof Movie){
            Movie movie = (Movie)obj;
            equal = this.movieId.equals(movie.getMovieId());
        }

        return equal;
    }
}
