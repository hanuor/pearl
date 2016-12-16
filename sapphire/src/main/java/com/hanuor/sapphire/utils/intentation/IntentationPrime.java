package com.hanuor.sapphire.utils.intentation;
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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanuor.container.LibraryDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IntentationPrime {

    public String intentToJSON(Context con,Intent intent) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String getContextName = null;
        String getClassName = null;
        try {
            getClassName = intent.getComponent().getClassName();
            getContextName = con.getPackageName();
        } catch (Exception e) {
            e.printStackTrace();

        }
        HashMap<String, String> makeInsideJsonArray = new HashMap<String, String>();

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("className",getClassName);
        hashMap.put("context",getContextName);
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> it = keys.iterator();
            Log.d("SappsnoopDog",""+keys.size());
            while (it.hasNext()) {
                String key = it.next();

                Log.d("Sapptagdog","TYPE   " + bundle.get(key).toString());
                Log.d("NERVE",""+bundle.get(key).getClass().getAnnotations());


                String type = bundle.get(key).getClass().getSimpleName();
                Log.d("SappDogTAG",key + " OF TYPE " + type);
                switch (type) {
                    case "String":
                        makeInsideJsonArray.put(key,type+ LibraryDatabase.JSONSEPERATOR +bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "String[]":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString().replace(" ",""));
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Integer":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());

                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Double":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case  "double[]":

                        double[] newDouble = (double[]) bundle.get(key);
                        String fromDouble = Arrays.toString(newDouble);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromDouble.replace(" ",""));
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "int[]":
                        int[] newArray = (int[]) bundle.get(key);
                        String fromArray = Arrays.toString(newArray);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromArray.replace(" ",""));
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Boolean":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "boolean[]":

                        boolean[] newBool = (boolean[]) bundle.get(key);
                        String fromBool = Arrays.toString(newBool);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromBool.replace(" ",""));
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Char":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "char[]":

                        char[] newChar = (char[]) bundle.get(key);
                        String fromChar = Arrays.toString(newChar);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromChar.replace(" ",""));
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "CharSequence":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "charsequence[]":

                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString().replace(" ",""));
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Byte":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "byte[]":

                        byte[] newByte = (byte[]) bundle.get(key);
                        String fromByte = Arrays.toString(newByte);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromByte.replace(" ",""));
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Float":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "float[]":

                        float[] newFloat = (float[]) bundle.get(key);
                        String fromFloat = Arrays.toString(newFloat);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromFloat.replace(" ",""));
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Short":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "short[]":

                        short[] newShort = (short[]) bundle.get(key);
                        String fromShort = Arrays.toString(newShort);
                        fromShort = fromShort.replace(" ","");
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+fromShort.replace(" ",""));
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "Long":
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString());
                        Log.d("SappDogTAG","bool array");
                        break;
                    case "long[]":

                        long[] newLong = (long[]) bundle.get(key);
                        String fromLong = Arrays.toString(newLong);
                        makeInsideJsonArray.put(key,type+LibraryDatabase.JSONSEPERATOR+bundle.get(key).toString().replace(" ",""));
                        Log.d("SappDogTAG","bool array");
                        break;

                    case "ArrayList":
                        ArrayList<Object> obj = (ArrayList<Object>) bundle.get(key);
                        Object[] objArr = obj.toArray();
                        if(objArr[0] instanceof Integer){
                            ArrayList<Integer> newIntegerArray = bundle.getIntegerArrayList(key);
                            makeInsideJsonArray.put(key,type+"Integer"+LibraryDatabase.JSONSEPERATOR+newIntegerArray.toString().replace(" ",""));

                        }else if(objArr[0] instanceof String){
                            ArrayList<String> newStringArray = bundle.getStringArrayList(key);

                            makeInsideJsonArray.put(key,type+"String"+LibraryDatabase.JSONSEPERATOR+newStringArray.toString().replace(" ",""));

                        }
                        break;

                    default:
                        // whatever
                }

                hashMap.put(key, bundle.get(key).toString());
            }
        }
        String passArray = mapper.writeValueAsString(makeInsideJsonArray);
        hashMap.put("intentExtras",passArray);
        Log.d("GOGTAD",""+passArray);

        String intentString  = mapper.writeValueAsString(intent);
        Log.d("IntentString", "" + mapper.writeValueAsString(hashMap));
        StringBuilder a1S = new StringBuilder(mapper.writeValueAsString(hashMap));
        a1S.deleteCharAt(mapper.writeValueAsString(hashMap).length()-1);
        a1S.append(",");
        String s1t = a1S.toString();

        StringBuilder sb = new StringBuilder(intentString);
        sb.deleteCharAt(0);
        String retrString = sb.toString();
        StringBuilder newS = new StringBuilder();
        newS.append(s1t);
        newS.append(retrString);
        Log.d("Insnsns",newS.toString());
        return newS.toString();
    }

    public Intent jsonToINTENT(String JSONString) throws JSONException {

        JSONObject jsonObject = new JSONObject(JSONString.toString());
        String toArray = jsonObject.get("intentExtras").toString();
        String contextName = jsonObject.get("context").toString();
        String className = jsonObject.get("className").toString();
        Log.d("Insass",className.toString());

        Intent setIntent = new Intent();
        setIntent.setClassName(contextName, className);
        HashMap<String, String> extrasHash = new HashMap<String, String>();
        JSONObject issueObj = new JSONObject(toArray);
        for (int i = 0; i < issueObj.length(); i++) {
            extrasHash.put(issueObj.names().getString(i), issueObj.get(issueObj.names().getString(i)).toString());
        }
        Iterator it = extrasHash.entrySet().iterator();
        while (it.hasNext()) {
            //add conditions  and checks here

            Map.Entry pair = (Map.Entry) it.next();
            String currentKey = (String) pair.getKey();
            Log.d("HAHA",""+currentKey);
            String[] getValuethroughSplit = pair.getValue().toString().split(LibraryDatabase.JSONSEPERATOR);
            String dataType = getValuethroughSplit[0];
            String  value = (String) getValuethroughSplit[2];
            Log.d("Insamareen",getValuethroughSplit.length + " " +dataType+ " " +value.toString());
            switch (dataType) {
                case "String":
                    setIntent.putExtra(currentKey,(String) value);
                    break;
                case "String[]":
                    String comp1 = value.substring(1,value.length()-1);
                    String[] comp2 = comp1.split(",");
                    setIntent.putExtra(currentKey,comp2);
                    break;
                case "Integer":
                    setIntent.putExtra(currentKey,Integer.parseInt(value));
                    break;
                case "Double":

                    setIntent.putExtra(currentKey,Double.parseDouble(value));

                    break;
                case  "double[]":
                    String compDouble1 = value.substring(1,value.length()-1);
                    String[] compDoub2 = compDouble1.split(",");
                    double[] db = new double[compDoub2.length];
                    for(int i = 0; i<compDoub2.length; i++){
                        db[i] = Double.parseDouble(compDoub2[i]);
                    }
                    setIntent.putExtra(currentKey,db);
                    break;
                case "int[]":
                    String compInt1 = value.substring(1,value.length()-1);
                    String[] compInt2 = compInt1.split(",");
                    int[] intVal = new int[compInt2.length];
                    for(int i = 0; i<compInt2.length; i++){
                        intVal[i] = Integer.parseInt(compInt2[i]);
                    }
                    Log.d("Hankey",intVal.toString());
                    setIntent.putExtra(currentKey,intVal);

                    break;
                case "Boolean":
                    setIntent.putExtra(currentKey,Boolean.valueOf(value));

                    break;
                case "boolean[]":
                    String compB1 = value.substring(1,value.length()-1);
                    String[] compB2 = compB1.split(",");
                    boolean[] BVal = new boolean[compB2.length];
                    for(int i = 0; i<compB2.length; i++){
                        BVal[i] =Boolean.parseBoolean(compB2[i]);
                    }
                    setIntent.putExtra(currentKey, value);

                    break;
                case "Char":
                    setIntent.putExtra(currentKey,value);

                    break;
                case "char[]":

                    String ch1 = value.substring(1,value.length()-1);
                    String[] ch2 = ch1.split(",");
                    String newS = null;
                    for(int i = 0; i<ch2.length; i++){
                        newS = newS + ch2[i];
                    }
                    setIntent.putExtra(currentKey,newS.toCharArray());

                    break;
                case "CharSequence":
                    setIntent.putExtra(currentKey,(CharSequence) value);

                    break;
                case "Charsequence[]":
                    setIntent.putExtra(currentKey,value.toString());

                    break;
                case "Byte":
                    setIntent.putExtra(currentKey,Byte.valueOf(value));

                    break;
                case "byte[]":
                    String by = value.substring(1,value.length()-1);
                    String[] by2 = by.split(",");
                    byte[] by3 = new byte[by2.length];
                    for(int i = 0; i<by2.length; i++){
                        by3[i] =Byte.parseByte(by2[i]);
                    }
                    setIntent.putExtra(currentKey,by3);

                    break;
                case "Float":
                    setIntent.putExtra(currentKey,Float.valueOf(value));

                    break;
                case "float[]":
                    String fl = value.substring(1,value.length()-1);
                    String[] fl2 = fl.split(",");
                    float[] fl3 = new float[fl2.length];
                    for(int i = 0; i<fl2.length; i++){
                        fl3[i] =Float.parseFloat(fl2[i]);
                    }
                    setIntent.putExtra(currentKey,fl3);

                    break;
                case "Short":
                    setIntent.putExtra(currentKey,Short.valueOf(value));

                    break;
                case "short[]":
                    String sh = value.substring(1,value.length()-1);
                    String[] sh2 = sh.split(",");
                    short[] sh3 = new short[sh2.length];
                    for(int i = 0; i<sh2.length; i++){
                        sh3[i] =Short.parseShort(sh2[i]);
                    }
                    setIntent.putExtra(currentKey,sh3);

                    break;
                case "Long":
                    setIntent.putExtra(currentKey,Long.valueOf(value));

                    break;
                case "long[]":
                    String ll = value.substring(1,value.length()-1);
                    String[] ll2 = ll.split(",");
                    long[] ll3 = new long[ll2.length];
                    for(int i = 0; i<ll2.length; i++){
                        ll3[i] =Long.parseLong(ll2[i]);
                    }
                    setIntent.putExtra(currentKey,ll3);

                    break;

                case "ArrayListString":
                    Log.d("Hankey",currentKey+" ");
                    String arrL = value.substring(1,value.length()-1);
                    String[] arrl2 = arrL.split(",");
                    ArrayList<String> arrStr = new ArrayList<String>();
                    for (int i = 0; i < arrl2.length; i++) {
                        arrStr.add(arrl2[i]);
                    }
                    setIntent.putStringArrayListExtra(currentKey, arrStr);

                    break;
                case "ArrayListInteger":
                    String arL = value.substring(1,value.length()-1);
                    String[] arl2 = arL.split(",");
                    ArrayList<Integer> arrInt = new ArrayList<Integer>();
                    for(int i = 0; i<arl2.length; i++){
                        arrInt.add(Integer.parseInt(arl2[i]));
                    }

                    setIntent.putIntegerArrayListExtra(currentKey,arrInt);

                    break;
                default:
                    // whatever
            }
        }
        return setIntent;
    }

}
