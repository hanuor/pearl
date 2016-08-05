package com.hanuor.pearl;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Shantanu Johri on 03-08-2016.
 */
public class PearlJSON {
    public static String convertToSerializableObject(JSONObject jsonObject){
        return new Gson().toJson(jsonObject);
    }
}
