package com.hanuor.pearl.handler;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Shantanu Johri on 02-08-2016.
 */
public class BitmapResizer {

    final public int INT_HEIGHT=500;
    final public int INT_WIDTH=500;

    public Bitmap bitmapResizeforLRUcache(Bitmap bitmap){
        Bitmap output = Bitmap.createBitmap(INT_WIDTH,INT_HEIGHT, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) INT_WIDTH / bitmap.getWidth(), (float) INT_HEIGHT / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());

        return output;

    }
}
