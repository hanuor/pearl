package com.hanuor.pearl.handler;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Shantanu Johri on 01-08-2016.
 */public class LRUImageLoader implements ComponentCallbacks2 {
    private TCLruCache cache;
    ContentResolver mcontent;
    public LRUImageLoader(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE);
        int maxKb = am.getMemoryClass() * 1024;
        int limitKb = maxKb / 8; // 1/8th of total ram
        cache = new TCLruCache(limitKb);
        mcontent = context.getContentResolver();
    }

    public void display(String url, ImageView imageview, int defaultresource) {
        Log.d("LRU","Herer "+cache.size());
        iterator();
        imageview.setImageResource(defaultresource);
        Bitmap image = cache.get(url);
        if (image != null) {
            Log.d("LRU","Image Exists " +cache.size());
            imageview.setImageBitmap(image);
        }
        else {
            new SetImageTask(imageview).execute(url);
        }
    }

    private class TCLruCache extends LruCache<String, Bitmap> {

        public TCLruCache(int maxSize) {
            super(maxSize);
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount() / 1024;

        }


    }

    private class SetImageTask extends AsyncTask<String, Void, Integer> {
        private ImageView imageview;
        private Bitmap bmp;
        private Bitmap bmpdisp;

        public SetImageTask(ImageView imageview) {
            this.imageview = imageview;
        }

        @Override
        protected Integer doInBackground(String... params) {
            String url = params[0];
            Uri uri = Uri.parse(url);
            InputStream inp = null;
            try {
                final int IMAGE_MAX_SIZE = 1200000; // 1.2MP
               // in = mcontent.openInputStream(uri);
                 inp = new java.net.URL(url).openStream();
                // Decode image size
                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inJustDecodeBounds = true;
                //BitmapFactory.decodeStream(in, null, o);
                BitmapFactory.decodeStream(inp, null, o);

                inp.close();



                int scale = 1;
                while ((o.outWidth * o.outHeight) * (1 / Math.pow(scale, 2)) >
                        IMAGE_MAX_SIZE) {
                    scale++;
                }
                inp = new java.net.URL(url).openStream();
                if (scale > 1) {
                    scale--;
                    // scale to max possible inSampleSize that still yields an image
                    // larger than target
                    o = new BitmapFactory.Options();
                    o.inSampleSize = scale;
                    bmp = BitmapFactory.decodeStream(inp, null, o);

                    // resize to desired dimensions
                    int height = bmp.getHeight();
                    int width = bmp.getWidth();

                    double y = Math.sqrt(IMAGE_MAX_SIZE
                            / (((double) width) / height));
                    double x = (y / height) * width;

                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmp, (int) x,
                            (int) y, true);
                    bmp.recycle();
                    bmp = scaledBitmap;

                    System.gc();
                } else {
                    bmp = BitmapFactory.decodeStream(inp);
                }
                inp.close();

                bmpdisp = getBitmapFromURL(url);
                if (bmp != null) {
                    cache.put(url, bmp);
                    Log.d("LRU",""+cache.size());
                }
                else {
                    return 0;
                }
            } catch (IOException e) {
                Log.e("Pearl", e.getMessage(),e);
                return 0;
            }

            return 1;

        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result == 1) {
                imageview.setImageBitmap(bmp);
            }else{
                //display error image
            }
            super.onPostExecute(result);
        }

        private Bitmap getBitmapFromURL(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection
                        = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    }

    @Override
    public void onLowMemory() {
    }

    @Override
    public void onTrimMemory(int level) {
        if (level >= TRIM_MEMORY_MODERATE) {
            cache.evictAll();
        }
        else if (level >= TRIM_MEMORY_BACKGROUND) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                cache.trimToSize(cache.size() / 2);
            }
        }
    }
    public void iterator(){
        int s = cache.size();
        Log.d("LRU","size of cache "+s);
        Map<String, Bitmap> snapshot = cache.snapshot();
        for (String id : snapshot.keySet()) {
            Bitmap myObject = cache.get(id);
            Log.d("LRU", "vamor");
        }
    }
}
