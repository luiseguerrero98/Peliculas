package edu.galileo.android.peliculas.lib;

import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Luis.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    //void urlToDraw(RelativeLayout container, String URL);
    void setOnFinishedImageLoadingListener(Object object);
}
