package com.hanuor.sapphire.temporarydb;/*
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
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hanuor.sapphire.hub.Internals;

import java.util.ArrayList;

public class SapphireImgDbHelper  extends SQLiteOpenHelper{
    private static final String DB_NAME = "SapphireInternalIMG.db";
    private static final String TABLE_IMAGE = "ImageHandler";
    private static final String ID_IMGKEY = "ImageKeyTag";
    private static final String IMG_COLUMN = "ImageColumn";
    private static final int DB_VERSION = 2;
    private Internals internals ;

    public SapphireImgDbHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);

    }


    public SapphireImgDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_IMG = "CREATE TABLE " + TABLE_IMAGE + "(" +
                ID_IMGKEY+ " STRING," + IMG_COLUMN + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(TABLE_IMG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertImage(String tag, byte[] arrayImg) throws SQLException {

                SQLiteDatabase database = this.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(ID_IMGKEY, tag);
                cv.put(IMG_COLUMN, arrayImg);
                database.insert(TABLE_IMAGE, null, cv);

    }
    public  int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + TABLE_IMAGE;
        Cursor cSor = db.rawQuery(query_params, null);
        return cSor.getCount();
    }
    public ArrayList<String> getAllTags(){
        ArrayList<String> getTags = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query_norms = "SELECT " + ID_IMGKEY + " FROM " + TABLE_IMAGE;
        Cursor cSor = db.rawQuery(query_norms, null);
        if(cSor.moveToFirst()){
            do{
                getTags.add(cSor.getString(cSor.getColumnIndexOrThrow(SapphireImgDbHelper.ID_IMGKEY)));
            }while(cSor.moveToNext());

        }else{
            return null;
        }
        return getTags;
    }

    public byte[] imgquery(String _key){
        String regenKey = _key ;
        String returnString = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String query_params = "SELECT " + IMG_COLUMN +", " + ID_IMGKEY
                + " FROM " + TABLE_IMAGE + " WHERE " + ID_IMGKEY + " = " +"'" +regenKey +"'"+ ";";
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                return  cSor.getBlob(cSor.getColumnIndex(SapphireImgDbHelper.IMG_COLUMN));

            }while(cSor.moveToNext());

        }else{
            return null;
        }

    }
    public void clearImgDB(){
        SQLiteDatabase dbnew = this.getWritableDatabase();
        dbnew.delete(TABLE_IMAGE, 1 + "=" + 1, null);
        dbnew.close();
    }

}
