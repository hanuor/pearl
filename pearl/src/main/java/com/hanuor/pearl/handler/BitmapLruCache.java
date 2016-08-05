package com.hanuor.pearl.handler;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.hanuor.pearl.toolbox.ImageLoader;

/**
 * Created by Shantanu Johri on 01-08-2016.
 */
public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
    BitmapResizer bmpresize = new BitmapResizer();

    public BitmapLruCache(int maxSize) {
        super(maxSize);

    }


    @Override
    public Bitmap getBitmap(String url) {

        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        //do bitmap resizing here

        put(url, bmpresize.bitmapResizeforLRUcache(bitmap));
    }

    @Override
    public void eraseCache() {
        evictAll();
    }

}
