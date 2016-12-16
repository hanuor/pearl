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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanuor.sapphire.temporarydb.SapphireImgDbHelper;
import com.hanuor.sapphire.temporarydb.SuggestionTempDBHandler;
import com.hanuor.sapphire.utils.ImagesUtil;
import com.hanuor.sapphire.utils.NativeSerializer;
import com.hanuor.sapphire.utils.intentation.IntentationPrime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SapphireIntentHandler {
    private Context context;
    private SuggestionTempDBHandler suggestionTempDBHandler;

    private IntentationPrime intentationPrime;


    public SapphireIntentHandler(Context context){
        this.context = context;
        suggestionTempDBHandler = new SuggestionTempDBHandler(context);
        intentationPrime = new IntentationPrime();
    }
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void setIntent(String keyTag, Intent intentObject) throws JsonProcessingException {
        String getJSON = intentationPrime.intentToJSON(context,intentObject);
        Log.d("Valhalla",getJSON);
        Log.d("VALHALLA",""+intentObject.getFlags());
        // suggestionTempDBHandler.insertData(keyTag, getJSON);
    }
    public void tast(String key, String value){
        suggestionTempDBHandler.insertData(key,value);
        Log.d("KAPAC","DODO  "+suggestionTempDBHandler.retrieveIntentData(key));
    }
    public void saveIntent(String keyTag, Intent intent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

            String jsonString  = mapper.writeValueAsString(intent);
            Log.d("SappHeya",""+jsonString);
        //Intent bj = mapper.readValue(jsonString, Intent.class);
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("a","aa");
        hashMap.put("b","lalala");
        ArrayList<String> m = new ArrayList<String>();
        m.add("frost");
        m.add("girl");
        m.add("Bumblebee");
        hashMap.put("c",m.toString());
        Log.d("SappTestTag", ""+ mapper.writeValueAsString(hashMap));

        //  String saveIntentthroughString = ToStringBuilder.reflectionToString(intent);
       // suggestionTempDBHandler.insertData(keyTag, saveIntentthroughString);
    }

    public void retrieveIntentData(String keyTag){

        Log.d("SapphireSuggestion","" + suggestionTempDBHandler.retrieveIntentData(keyTag));

    }
    public void check(SuggestionView suggestionView){
        Log.d("SapphireAccess","true0");
        SapphireImgDbHelper sapphireImgDbHelper = new SapphireImgDbHelper(context);
        ImagesUtil imagesUtil = new ImagesUtil();
        Log.d("SapphireAccess","true1");
        NativeSerializer nativeSerializer = new NativeSerializer();
        try {
           byte[] nt =  nativeSerializer.serialize(suggestionView);
            SuggestionView sV= (SuggestionView) nativeSerializer.deserialize(nt);
          //  sV.setUPSuggestion(context, imagesUtil.byteToBitmap(sapphireImgDbHelper.imgquery("girl")));
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Heteee","Ex"+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.d("Heteee","Ex"+e.getMessage());
        }



    }
}
