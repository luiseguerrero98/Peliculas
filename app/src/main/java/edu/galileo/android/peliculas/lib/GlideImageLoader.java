package edu.galileo.android.peliculas.lib;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import edu.galileo.android.peliculas.moviemain.ui.MovieMainActivity;

/**
 * Created by Luis.
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;
    private RequestListener onFinishedImageLoadingListener;

    public void setLoaderContext(Activity activity) {
        this.glideRequestManager = Glide.with(activity);
    }

    @Override
    public void load(ImageView imageView, String URL) {
        if (onFinishedImageLoadingListener != null) {
            glideRequestManager
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .listener(onFinishedImageLoadingListener)
                    .into(imageView);
        } else {
            glideRequestManager
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageView);
        }
    }

/*    public void urlToDraw(final RelativeLayout container, String URL){
        if (onFinishedImageLoadingListener != null) {
            glideRequestManager.
                    load(URL).
                    asBitmap()
                    .listener(onFinishedImageLoadingListener)
                    .into(new SimpleTarget<Bitmap>(480, 800) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable = new BitmapDrawable(resource);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        container.setBackground(drawable);
                    }
                }
            });
        } else {
            glideRequestManager.
                    load(URL).
                    asBitmap().into(new SimpleTarget<Bitmap>(480, 800) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable = new BitmapDrawable(resource);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        container.setBackground(drawable);
                    }
                }
            });
        }

    }*/

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        try {
            this.onFinishedImageLoadingListener = (RequestListener) listener;
        } catch (ClassCastException e) {
            Log.e(this.getClass().getName(),e.getMessage());
        }
    }
}
