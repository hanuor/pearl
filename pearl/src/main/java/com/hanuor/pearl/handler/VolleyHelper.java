package com.hanuor.pearl.handler;

import android.content.Context;

import com.hanuor.pearl.toolbox.ImageLoader;
import com.hanuor.pearl.toolbox.Volley;
import com.hanuor.pearl.volleysingleton.RequestQueue;

/**
 * Created by Shantanu Johri on 01-08-2016.
 */
public class VolleyHelper {
    private static final int MAX_IMAGE_CACHE_ENTIRES  = 100;
    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;
    private static BitmapLruCache bitmapLruCache;
    private VolleyHelper() {
    }

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

        mImageLoader = new ImageLoader(context, mRequestQueue, new BitmapLruCache(MAX_IMAGE_CACHE_ENTIRES));
    }

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    /**
     * Returns instance of ImageLoader initialized with {@see FakeImageCache} which effectively means
     * that no memory caching is used. This is useful for images that you know that will be show
     * only once.
     */
    public static ImageLoader getImageLoader() {
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }
}
