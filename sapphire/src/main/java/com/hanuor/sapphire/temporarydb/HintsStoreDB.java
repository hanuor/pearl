package com.hanuor.sapphire.temporarydb;
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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hanuor.sapphire.utils.Utility;

import java.util.ArrayList;

public class HintsStoreDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "HintsStore.db";
    private static final int DBVERSION = 7;
    private static final String TABLE_NAME = "HINTSTABLE";
    private static final String COLUMN_NAME = "HINTSCOLUMN";

    public HintsStoreDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public HintsStoreDB(Context context) {
        super(context, DB_NAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String Table_create = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_NAME + " STRING" + ");";
        sqLiteDatabase.execSQL(Table_create);
    }

    public void storeDetails(ArrayList<String> details){
        clearDatabase();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(String  element: details){
            contentValues.put(COLUMN_NAME, element);
            long val = db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
    }
    public void storeDetails(ArrayList<String> details, String delimiter){
        clearDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            if(delimiter.equals("")){
                Utility.throwException(delimiter,"Cannot be null");
            }
            for(String  element: details){
                String[] partition = element.split(delimiter);
                stringBuffer.setLength(0);
                int newLength = partition.length;
                for(int i = 0; i < newLength ; i++){
                    Log.d("VVVV",""+ partition[i]);

                    stringBuffer.append(partition[i]);
                    if(i == (newLength-1)){

                    }else{
                        stringBuffer.append("\r\n");
                    }

                }
                contentValues.put(COLUMN_NAME, stringBuffer.toString());
                long val = db.insert(TABLE_NAME, null, contentValues);
            }


        db.close();
    }
    public void clearDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,1+"="+1 , null);
        db.close();
    }
    public ArrayList<String> query(){
        ArrayList<String> retString  = new ArrayList<String>();
        SQLiteDatabase db0 = this.getReadableDatabase();
        String query_params0 = "SELECT " + "*" + " FROM " + TABLE_NAME;
        Cursor cSor0 = db0.rawQuery(query_params0, null);
        if(cSor0.moveToFirst()){
            do{
                retString.add(cSor0.getString(cSor0.getColumnIndexOrThrow(HintsStoreDB.COLUMN_NAME))) ;
            }while(cSor0.moveToNext());
            return retString;
        }else{
            return null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
