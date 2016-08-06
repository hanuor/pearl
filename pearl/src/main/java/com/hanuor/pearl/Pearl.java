package com.hanuor.pearl;

import android.content.Context;
import android.widget.ImageView;

import com.hanuor.pearl.handler.VolleyHelper;
import com.hanuor.pearl.toolbox.ImageLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Shantanu Johri on 31-07-2016.
 */
public class Pearl{
    private static Context ctx;
    private static int defaultImg = 0;
    private static ImageLoader imageLoader;

    public static void saveJsonObject(Context context, String jsonObject,String tag) {
            FileOutputStream fos = null;
            try {
                fos = context.openFileOutput(tag, Context.MODE_PRIVATE);

                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(jsonObject);
                oos.flush();
                oos.close();

                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public static Object retrieveJsonObject(String tag){

            try {
                FileInputStream fis = ctx.openFileInput(tag);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object object = ois.readObject();
                fis.close();
                return object;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }

    }
    public static void imageLoader(Context context, String URL, ImageView target, int defaultImage) {
        ctx = context;
        defaultImg = defaultImage;
        VolleyHelper.init(context);
        imageLoader = VolleyHelper.getImageLoader();
            if(defaultImg != 0){
                  imageLoader.get(URL,ImageLoader.getImageListener(target, defaultImage, defaultImage));
            }
     }
    public static void cancelImageLoad(String urlofImage){
            imageLoader.cancelRequestfromQueue(urlofImage);
  }


}
