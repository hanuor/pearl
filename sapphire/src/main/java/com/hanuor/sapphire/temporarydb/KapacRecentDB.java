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

import com.hanuor.sapphire.utils.enumeration.KapacRecent;
/*
Kapac displays the recent activity*/
public class KapacRecentDB extends SQLiteOpenHelper {
    private static final KapacRecent TBNM = KapacRecent.RECENT_KAPAC_TABLE;
    private static final KapacRecent DBNM = KapacRecent.KAPAC_RECENT_DATABASE;
    private static final KapacRecent CLNM = KapacRecent.COLUMN_VALUE;
    private static final String _tbnm = "KAPACTBNM";
    private static final String _dbnm = "KAPACDBNM.db";
    private static final String _clnm =  "KAPACCLNM";
    private static final String TABLE_PACKAGE_STORAGE = "TableContainsPackage";
    private static final String COLUMN_PACKAGE = "ColumnContaingPackage";

    private static final int KAPACRECENT = 3;
    public KapacRecentDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + _tbnm + "(" + _clnm + " STRING);");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PACKAGE_STORAGE + "(" + COLUMN_PACKAGE + " STRING" + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public KapacRecentDB(Context context) {
        super(context, _dbnm, null, KAPACRECENT);
    }
    public void insertKAPACDOC(String jsonDoc){
        String holder = queryKAPACVALUES();
        if(holder!=null){

        }else{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(_clnm, jsonDoc);
            db.insert(_tbnm, null, contentValues);
            db.close();
        }
    }
    public void updateDOCwithKAPAC(String _data){
        deleteKAPACDOc();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(_clnm, _data);
        db.insert(_tbnm, null, contentValues);
        db.close();
        Log.d("KapacIntel",queryKAPACVALUES());
    }
    //handles deletion automatically
    private boolean deleteKAPACDOc(){
        SQLiteDatabase db6 = this.getWritableDatabase();
        int status = db6.delete(_tbnm,1+"="+1 , null);
        db6.close();
        return true;
    }
    public String queryKAPACVALUES(){
        SQLiteDatabase db0 = this.getReadableDatabase();
        String query_params0 = "SELECT " + "*" + " FROM " + _tbnm;
        Cursor cSor0 = db0.rawQuery(query_params0, null);
        if(cSor0.moveToFirst()){
            do{
                return cSor0.getString(cSor0.getColumnIndexOrThrow(KapacRecentDB._clnm));
            }while(cSor0.moveToNext());
        }else{
            return null;
        }
    }
    public void clearPackageTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        int status = db.delete(TABLE_PACKAGE_STORAGE,1+"="+1 , null);
        db.close();
    }
    public void setTablePackageStorage(String packageS){
        clearPackageTable();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PACKAGE, packageS);
        db.insert(TABLE_PACKAGE_STORAGE, null, contentValues);
        db.close();
    }

    public String queryPackage(){
        SQLiteDatabase db0 = this.getReadableDatabase();
        String query_params0 = "SELECT " + "*" + " FROM " + TABLE_PACKAGE_STORAGE;
        Cursor cSor0 = db0.rawQuery(query_params0, null);
        if(cSor0.moveToFirst()){
            do{
                return cSor0.getString(cSor0.getColumnIndexOrThrow(KapacRecentDB.COLUMN_PACKAGE));
            }while(cSor0.moveToNext());
        }else{
            return null;
        }
    }
}
