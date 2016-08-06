package com.hanuor.pearl;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.hanuor.pearl.handler.VolleyHelper;
import com.hanuor.pearl.toolbox.ImageLoader;
import com.hanuor.pearl.verifiers.TemporaryDB;
import com.hanuor.pearl.verifiers.Utils;

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
    private static   TemporaryDB temporaryDB = new TemporaryDB();

    private static ImageLoader imageLoader;
    public static void initialize(Context context, String key){
        ctx = context;
        VolleyHelper.init(context);
         imageLoader = VolleyHelper.getImageLoader();

        if(context== null || key == null) {
            Utils.throwExceptionIfNullOrBlank(context, "context");
            Utils.throwExceptionIfNullOrBlank(key, "libraryKey");
        }
        else{
            temporaryDB.setKeygen(key);
        }
    }
    public static void saveJsonObject(Context context, String jsonObject,String tag) {
        if(temporaryDB.getKeygen().equalsIgnoreCase("pearl")) {

            FileOutputStream fos = null;
            try {
                fos = context.openFileOutput(tag, Context.MODE_PRIVATE);

                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(jsonObject);
                oos.flush();
                oos.close();

                fos.close();

                Log.d("CheckJson", "yes");
            } catch (FileNotFoundException e) {
                e.printStackTrace();

                Log.d("CheckJson", "no");
            } catch (IOException e) {
                e.printStackTrace();

                Log.d("CheckJson", "no");
            }
        }else{
            Utils.throwExceptionIfKeyDNM(temporaryDB.getKeygen().toString(), "libraykey");
        }
    }
    public static Object retrieveJsonObject(String tag){
        if(temporaryDB.getKeygen().equalsIgnoreCase("pearl")) {


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
        }else{
            Utils.throwExceptionIfKeyDNM(temporaryDB.getKeygen().toString(), "libraykey");
            return null;
        }
    }
    public static void imageLoader(Context context, String URL, ImageView target) {
        ctx = context;
        Log.e("LRU","loader");
        if(temporaryDB.getKeygen().equalsIgnoreCase("pearl")){

            imageLoader.get(URL,ImageLoader.getImageListener(target, com.hanuor.pearl.R.drawable.more, com.hanuor.pearl.R.drawable.more));


        }else{
            Utils.throwExceptionIfKeyDNM(temporaryDB.getKeygen().toString(), "libraykey");
        }
     }
    public static void cancelImageLoad(String urlofImage){
        if(temporaryDB.getKeygen().equalsIgnoreCase("pearl")) {
            imageLoader.cancelRequestfromQueue(urlofImage);
        }else{
            Utils.throwExceptionIfKeyDNM(temporaryDB.getKeygen().toString(), "libraykey");
        }
    }


}
