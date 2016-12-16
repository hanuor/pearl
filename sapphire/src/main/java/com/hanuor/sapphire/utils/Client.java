package com.hanuor.sapphire.utils;
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
import android.util.Log;

import com.hanuor.client.NodeMonitor;
import com.hanuor.container.Initializer;
import com.hanuor.container.LibraryDatabase;
import com.hanuor.sapphire.hub.Internals;
import com.hanuor.sapphire.temporarydb.KapacRecentDB;
import com.hanuor.sapphire.temporarydb.SapphireDbManager;
import com.hanuor.sapphire.temporarydb.SapphirePrivateDB;
import com.hanuor.utils.ConverterUtils;
import com.hanuor.utils.GetDayUtil;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.storage.Storage;
import com.shephertz.app42.paas.sdk.android.storage.StorageService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Client {
    private Context ctx;
    public static Initializer mInit = new Initializer();
    public static NodeMonitor mNodeMonitor = new NodeMonitor();
    private static StorageService storageService = App42API.buildStorageService();
    private static SapphirePrivateDB sapphirePrivateDB;
    private static GetDayUtil getDayUtil;
    private KapacRecentDB kapacRecentDB;
    private SapphireDbManager sapphireDbManager;
    public Client(Context ctx){
      App42API.initialize(ctx, mInit.Appkey(),mInit.AppSecret());
       this.ctx = ctx;
        getDayUtil = new GetDayUtil();
        sapphirePrivateDB = new SapphirePrivateDB(ctx);
        sapphireDbManager  = new SapphireDbManager(ctx);
        kapacRecentDB = new KapacRecentDB(ctx);
    }

    public void writeJson(final String jsonDocument){
		/*
		Write json document to the database. This method is directly accessed from the android library.
		*/
        //use a internet check here
        //use a separate DB here for handling the JSON  data
        //This is for private tree learning

        if(sapphireDbManager.query() == null){
            Log.d("Weate","HEEEHE");
            sapphireDbManager.insertJDoc(jsonDocument);
        }else{
            Log.d("Weate","HEEEHE  " + sapphireDbManager.query());
        }
        if(sapphirePrivateDB.queryPRnodefor(getDayUtil.getDay())==null){
            sapphirePrivateDB.storeTags(jsonDocument);
        }
        //sapphirePrivateDB.nodeupdatePRDAYsModulo2(getDayUtil.getDay(), jsonDocument);
        //end of pvt tree learning

        //For public learning. That is maintaining the recent visited profile tags we are shifting to KAPAC Intelligence.
        kapacRecentDB.insertKAPACDOC(jsonDocument);


        ServiceHandler.storageService.insertJSONDocument(LibraryDatabase.DBNAME, LibraryDatabase.collectionName, jsonDocument, new App42CallBack() {
            @Override
            public void onSuccess(Object o) {
                Log.d("SapphireUploadSuccess","true");
                Internals internals = new Internals(ctx);
                Storage storage  = (Storage )o;
                ArrayList<Storage.JSONDocument> jsonDocList = storage.getJsonDocList();
                String docId = jsonDocList.get(0).getDocId();
                internals.saveDocIdInternally(docId);

            }
            @Override
            public void onException(Exception e) {
                Log.d("SapphireUploadSuccess","false");
                e.printStackTrace();
            }
        });
    }
    private void getJson(String docId) {
        // TODO Auto-generated method stub

		/*Get the json doccument from the database using a docId as a formal parameter.
		*/

        storageService.findDocumentById(LibraryDatabase.DBNAME, LibraryDatabase.collectionName, docId, new App42CallBack() {
            public void onSuccess(Object response)
            {
                Storage  storage  = (Storage )response;
                System.out.println("dbName is " + storage.getDbName());
                System.out.println("collection Name is " + storage.getCollectionName());
                ArrayList<Storage.JSONDocument> jsonDocList = storage.getJsonDocList();
                for(int i=0;i<jsonDocList.size();i++)
                {
                    System.out.println("objectId is " + jsonDocList.get(i).getDocId());
                    System.out.println("CreatedAt is " + jsonDocList.get(i).getCreatedAt());
                    System.out.println("UpdatedAtis " + jsonDocList.get(i).getUpdatedAt());
                    System.out.println("Jsondoc is " + jsonDocList.get(i).getJsonDoc());
                }
            }
            public void onException(Exception ex)
            {
                System.out.println("Exception Message"+ex.getMessage());
            }
        });
    }

    public  void makeJsonString(ArrayList<String> tags){
        ConverterUtils cutils = new ConverterUtils(tags);
        String jsonDoc =  cutils.getJsonString();

        //call the write Json script
        //writeJSON makes a json object string and saves it with default valure 0.1

        writeJson(jsonDoc);
    }
    public static void updateJson(final ArrayList<String> tags, String docID){

        storageService.findDocumentById(LibraryDatabase.DBNAME, LibraryDatabase.collectionName, docID, new App42CallBack() {
            public void onSuccess(Object response)
            {
                String jsonD = null;
                HashMap<String,String> hMap=new HashMap<String,String>();
                ArrayList<String> arrayList = new ArrayList<String>();
                Storage  storage  = (Storage )response;
                System.out.println("dbName is " + storage.getDbName());
                System.out.println("collection Name is " + storage.getCollectionName());
                ArrayList<Storage.JSONDocument> jsonDocList = storage.getJsonDocList();
                for(int i=0;i<jsonDocList.size();i++)
                {
                    jsonD = jsonDocList.get(i).getJsonDoc();
                }
                try {
                    if(jsonD!=null){
                        JSONObject jObj = new JSONObject(jsonD);
                        for(int i = 0; i<tags.size();i++){
                            String entityValue = null;
                            entityValue = jObj.getString(tags.get(i));
                            if(entityValue.length()!=0){
                                hMap.put(tags.get(i), entityValue);
                            }else{
                                arrayList.add(tags.get(i));
                            }
                            //add to hashmap here
                            for(int j = 0; j< arrayList.size();j++){
                                hMap.put(arrayList.get(j), "0.1");
                            }
                        }
                        updateJsonDoc(hMap);
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
    protected static void updateJsonDoc(HashMap<String, String> hMap) {
        //Imported from Eclipse
        // TODO Auto-generated method stub
        ConverterUtils cUtils = new ConverterUtils();
        try {
            String output = cUtils.getUpdatedJsonString(hMap);

            //return the string here if you want to

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }

}
