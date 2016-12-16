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


//Db to store starttimestamps of app for machine learning.

public class StartTimeStoreDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "TimeStampStoreEandS.db";
    private static final int DB_VERSION = 15;
    private static final String TABLE_TIMESTAMP = "TimeStampTable";
    private static final String COLUMN_TIMESTAMP = "TimeStampColumn";
    private static final String COLUMN_ENDTIMESTAMP =  "EndTimeStampColumn";
    private static final String TABLE_ENDTIMESTAMP = "EndTimeStampTable";

    public StartTimeStoreDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("LoganS","OnCreate");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ENDTIMESTAMP  + "(" + COLUMN_ENDTIMESTAMP + " STRING" + ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TIMESTAMP + "(" + COLUMN_TIMESTAMP + " STRING" +");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public StartTimeStoreDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    public void addstartTimeStampToDB(long _timeStamp){
        String longCon  = Long.toString(_timeStamp);
        String querytoAppend = retrievestartStamp();
        String nss = querytoAppend + "\r\n" + longCon;
        clearTimestampTable();

        Log.d("nucleyaAAA",""+nss);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TIMESTAMP, nss);
        long ff = db.insert(TABLE_TIMESTAMP, null, contentValues);
        db.close();
    }
    public void addEndTimeStampToDB(long _timeStamp){
        String longCon  = Long.toString(_timeStamp);
        String querytoAppend = retrieveendStamp();
        String nss = querytoAppend + "\r\n" + longCon;
        clearendTimeStampTable();
        Log.d("nucleyaAAA",""+nss);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ENDTIMESTAMP, nss);
        long ff = db.insert(TABLE_ENDTIMESTAMP, null, contentValues);
        db.close();

    }

    public String retrievestartStamp(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + TABLE_TIMESTAMP;
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                return cSor.getString(cSor.getColumnIndexOrThrow(COLUMN_TIMESTAMP));
            }while(cSor.moveToNext());
        }else{
            return "";
        }
    }
    public String retrieveendStamp(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + TABLE_ENDTIMESTAMP;
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                return cSor.getString(cSor.getColumnIndexOrThrow(COLUMN_ENDTIMESTAMP));
            }while(cSor.moveToNext());
        }else{
            return "";
        }
    }
    public void clearTimestampTable(){
        SQLiteDatabase db0 = this.getWritableDatabase();
        db0.delete(TABLE_TIMESTAMP,1+"="+1 , null);
        db0.close();
    }
    public void clearendTimeStampTable(){
        SQLiteDatabase db0 = this.getWritableDatabase();
        db0.delete(TABLE_ENDTIMESTAMP,1+"="+1 , null);
        db0.close();

    }
}
