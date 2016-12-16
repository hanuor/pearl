package com.hanuor.sapphire.hub;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.backendless.Backendless;
import com.hanuor.client.Client;
import com.hanuor.container.Initializer;
import com.hanuor.container.LibraryDatabase;
import com.hanuor.sapphire.temporarydb.KapacRecentDB;
import com.hanuor.sapphire.temporarydb.SapphireDbManager;
import com.hanuor.sapphire.temporarydb.SapphireImgDbHelper;
import com.hanuor.sapphire.temporarydb.SapphirePrivateDB;
import com.hanuor.sapphire.utils.BitmapUtility;
import com.hanuor.sapphire.utils.ImagesUtil;
import com.hanuor.utils.GetDayUtil;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.storage.Storage;
import com.shephertz.app42.paas.sdk.android.storage.StorageService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Internals {
    private Context ctx = null;
    private ImagesUtil imagesUtil = new ImagesUtil();
    private static SapphireDbManager sapphireDbManager;
    private static SapphirePrivateDB sapphirePrivateDB;
    private static SapphireImgDbHelper sapphireImgDbHelper;
    private static Initializer mInit = new Initializer();
    private static StorageService storageService;
    private  BitmapUtility bitmapUtility = new BitmapUtility();
    private GetDayUtil getDayUtil = new GetDayUtil();
    private KapacRecentDB kapacRecentDB;

    public Internals(Context ctx){

        Log.d("25 minutes", "Internals");
        this.ctx = ctx;
        sapphireDbManager = new SapphireDbManager(ctx);
        sapphireImgDbHelper = new SapphireImgDbHelper(ctx);
        sapphirePrivateDB = new SapphirePrivateDB(ctx);
        kapacRecentDB = new KapacRecentDB(ctx);
        App42API.initialize(ctx, mInit.Appkey(),mInit.AppSecret());
        Backendless.initApp( ctx, mInit.ImgHelperId(), mInit.ImgHelperSecret(),"v1");
        storageService = App42API.buildStorageService();

    }

    public  void saveDocIdInternally(String docID){
        //Don't forget to add wrtie permission in android
        new FilePrinterAsyncTaskSapphire().execute(docID);
    }
    public String readIdfromDevice(){
        BufferedReader input = null;
        File file = null;
        try {
            file = new File(ctx.getFilesDir(), LibraryDatabase.INTERNALFNAME);
            input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class FilePrinterAsyncTaskSapphire extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            String content = urls[0];

            FileOutputStream outputStream = null;
            try {
                outputStream = ctx.openFileOutput(LibraryDatabase.INTERNALFNAME, Context.MODE_PRIVATE);
                outputStream.write(content.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public void updateJsonFollowUp(final ArrayList<String> tags, String docID){
        storageService.findDocumentById(LibraryDatabase.DBNAME, LibraryDatabase.collectionName, docID, new App42CallBack() {
            public void onSuccess(Object response)
            {
                String jsonD = null;
                Storage storage  = (Storage )response;
                ArrayList<Storage.JSONDocument> jsonDocList = storage.getJsonDocList();
                for(int i=0;i<jsonDocList.size();i++)
                {
                    jsonD = jsonDocList.get(i).getJsonDoc();
                }
                try {
                    if(jsonD!=null){
                        JSONObject jObj = new JSONObject(jsonD);
                        String getDoc = Client.updateJsonDoc(Client.jsonDocAlgo(tags, jObj));
                        String queriedDoc = sapphireDbManager.query();
                        if(getDoc.equals(queriedDoc)){
                        }else{
                            sapphireDbManager.insertJDoc(getDoc);
                        }
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            public void onException(Exception ex)
            {
                System.out.println("Exception Message"+ex.getMessage());
            }
        });
    }
    public void hitTags(String _tag){
        //fetch from database
        String retrievedDoc = sapphireDbManager.query();
        String kapac_value = kapacRecentDB.queryKAPACVALUES();

        if(kapac_value == null){
            Log.d("KapacIntel",""+kapac_value);
        }

        try {
            Log.d("KapacIntel","getDoc " + retrievedDoc);
            String getDoc = Client.updateJsonDoc(Client.SapphireHitTag(retrievedDoc, _tag));
            kapacRecentDB.updateDOCwithKAPAC(Client.updateJsonDoc(Client.SapphireHitTag(kapac_value, _tag)));
            sapphireDbManager.insertJDoc(getDoc);
            privateHitTag(_tag);
            Log.d("Sapphire",""+sapphireDbManager.query());
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void privateHitTag(String hitTag){
        String jsonD = sapphirePrivateDB.fetchprivategetJsonStringfromDB();
        String nodePRdaydoc = sapphirePrivateDB.queryPRnodefor(getDayUtil.getDay());
        Log.d("SapGET",""+nodePRdaydoc);
        Log.d("SapGETPP",""+jsonD);

        try {
            if(jsonD!=null){
                JSONObject jObj = new JSONObject(jsonD);
                String getDoc = Client.updateJsonDoc(Client.SapphireHitTag(jsonD, hitTag));
                sapphirePrivateDB.storeTags(getDoc);

                //update to cloud as well
            }
            if(nodePRdaydoc!=null){
                //Update for days wise as well
                Log.d("Sapppp",nodePRdaydoc);
                JSONObject jObj = new JSONObject(nodePRdaydoc);
                String getPRNODEData = Client.updateJsonDoc(Client.SapphireHitTag(nodePRdaydoc, hitTag));
                sapphirePrivateDB.nodeupdatePRDAYsModulo2(getDayUtil.getDay(), getPRNODEData);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void storImgs(Context ctx, ArrayList<ImageView> imgviews){
        Log.d("allweknow","here");
        String destination = getAppName(ctx) +"*"+ readIdfromDevice();
        sapphireImgDbHelper.clearImgDB();
        for(int i = 0; i<imgviews.size(); i++){
            //add Tag check here, not adding for now as this is just for testing
            String tag = null;
            tag = (String) imgviews.get(i).getTag();

            sapphireImgDbHelper.insertImage(tag, imagesUtil.drawableToByteArray(imgviews.get(i).getDrawable()));
            Log.d("SapphireBelly",""+sapphireImgDbHelper.getCount());

            Bitmap bmp = null;
            try {
                bmp = ((BitmapDrawable)imgviews.get(i).getDrawable()).getBitmap();

                //Put an exp backoff here so that it uses min. usage of internet.
                //switch back and forth from imgstorebackendless class


               /* Backendless.Files.Android.upload(bitmapUtility.createResizedBitmap(bmp), Bitmap.CompressFormat.PNG,
                        100, tag, destination , new AsyncCallback<BackendlessFile>() {
                            @Override
                            public void handleResponse(BackendlessFile backendlessFile) {
                                Log.d("allweknowRes",""+backendlessFile.getFileURL());
                            }
                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {
                                Log.d("allweknowFault",""+backendlessFault.getMessage());
                            }
                        });*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.d("Sapppoo",""+ sapphireImgDbHelper.imgquery("girl") + "   "+ sapphireImgDbHelper.getCount());
  }
    private static String getAppName(Context context) {
        int stringId = context.getApplicationInfo().labelRes;
        if(context.getString(stringId)!= null){
            return context.getString(stringId);
        }else{
            return "null_app_name";
        }
    }


    public void recentTagViewHelper(String _tag, ArrayList<String> imageViewArrayList){
        ArrayList<String> tagslist = new ArrayList<String>();
        Log.d("SapphireList", ""+imageViewArrayList.size());
    }


}
