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

public class SapphirePrivateDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "SapphireInternalPrivitized.db";
    private static final int DBVERSION = 3;

    private static final String TABLE_PR = "privatemoduletwo";
    private static final String COL_LISTTAGS =  "privateListTags";

    private static final String COLUMN_PR_DAYS = "privateModulodataDays";
    private static final String TABLE_PRDAYS_MON = "privatedatadaysTABLE_MON";
    private static final String TABLE_PRDAYS_TUE = "privatedatadaysTABLE_TUE";
    private static final String TABLE_PRDAYS_WED = "privatedatadaysTABLE_WED";
    private static final String TABLE_PRDAYS_THU = "privatedatadaysTABLE_THU";
    private static final String TABLE_PRDAYS_FRI = "privatedatadaysTABLE_FRI";
    private static final String TABLE_PRDAYS_SAT = "privatedatadaysTABLE_SAT";
    private static final String TABLE_PRDAYS_SUN = "privatedatadaysTABLE_SUN";

    public SapphirePrivateDB(Context context) {
        super(context, DB_NAME, null, DBVERSION);
    }


    public SapphirePrivateDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_PRIVATETAGS = "CREATE TABLE " + TABLE_PR + "(" +
                COL_LISTTAGS + " STRING" + ");";
        String TABLEPRMON = "CREATE TABLE " + TABLE_PRDAYS_MON + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRTUE = "CREATE TABLE " + TABLE_PRDAYS_TUE + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRWED = "CREATE TABLE " + TABLE_PRDAYS_WED + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRTHU = "CREATE TABLE " + TABLE_PRDAYS_THU + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRFRI = "CREATE TABLE " + TABLE_PRDAYS_FRI + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRSAT = "CREATE TABLE " + TABLE_PRDAYS_SAT + "("+
                COLUMN_PR_DAYS + " STRING);";
        String TABLEPRSUN = "CREATE TABLE " + TABLE_PRDAYS_SUN + "("+
                COLUMN_PR_DAYS + " STRING);";

        sqLiteDatabase.execSQL(TABLE_PRIVATETAGS);
        sqLiteDatabase.execSQL(TABLEPRMON);
        sqLiteDatabase.execSQL(TABLEPRTUE);
        sqLiteDatabase.execSQL(TABLEPRWED);
        sqLiteDatabase.execSQL(TABLEPRTHU);
        sqLiteDatabase.execSQL(TABLEPRFRI);
        sqLiteDatabase.execSQL(TABLEPRSAT);
        sqLiteDatabase.execSQL(TABLEPRSUN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void storeTags(String jDocument){
        deleteAll();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_LISTTAGS, jDocument);
        db.insert(TABLE_PR, null, contentValues);
        db.close();
        Log.d("SapphireBlof",""+fetchprivategetJsonStringfromDB());
    }
    private void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PR, 1 + "=" + 1, null);
        db.close();
    }
    public String fetchprivategetJsonStringfromDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + TABLE_PR;
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                return cSor.getString(cSor.getColumnIndexOrThrow(SapphirePrivateDB.COL_LISTTAGS));
            }while(cSor.moveToNext());
        }else{
            return null;
        }
    }
    public void nodeupdatePRDAYsModulo2(int day, String data){
        Log.d("Saaaa","Yes");
        switch(day){
            case 0:
                    deleteNodePRdatafor(0);

                SQLiteDatabase db0 = this.getWritableDatabase();
                ContentValues contentValues0 = new ContentValues();
                    contentValues0.put(COLUMN_PR_DAYS, data);
                    db0.insert(TABLE_PRDAYS_SUN, null, contentValues0);
                    db0.close();
                break;
            case 1:
                deleteNodePRdatafor(1);

                SQLiteDatabase db1 = this.getWritableDatabase();
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put(COLUMN_PR_DAYS, data);
                db1.insert(TABLE_PRDAYS_MON, null, contentValues1);
                db1.close();
                break;
            case 2:
                deleteNodePRdatafor(2);

                SQLiteDatabase db2 = this.getWritableDatabase();
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(COLUMN_PR_DAYS, data);
                db2.insert(TABLE_PRDAYS_TUE, null, contentValues2);
                db2.close();
                break;
            case 3:
                deleteNodePRdatafor(3);

                SQLiteDatabase db3 = this.getWritableDatabase();
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put(COLUMN_PR_DAYS, data);
                db3.insert(TABLE_PRDAYS_WED, null, contentValues3);
                db3.close();
                break;
            case 4:
                deleteNodePRdatafor(4);

                SQLiteDatabase db4 = this.getWritableDatabase();
                ContentValues contentValues4 = new ContentValues();
                contentValues4.put(COLUMN_PR_DAYS, data);
                db4.insert(TABLE_PRDAYS_THU, null, contentValues4);
                db4.close();
                break;
            case 5:
                deleteNodePRdatafor(5);

                SQLiteDatabase db5 = this.getWritableDatabase();
                ContentValues contentValues5 = new ContentValues();
                contentValues5.put(COLUMN_PR_DAYS, data);
                db5.insert(TABLE_PRDAYS_FRI, null, contentValues5);
                db5.close();
                break;
            case 6:
                deleteNodePRdatafor(6);

                SQLiteDatabase db6 = this.getWritableDatabase();
                ContentValues contentValues6 = new ContentValues();
                contentValues6.put(COLUMN_PR_DAYS, data);
                db6.insert(TABLE_PRDAYS_SAT, null, contentValues6);
                db6.close();
                break;
            default:
                break;
        }
        Log.d("SapphireMagicModule1", ""+ queryPRnodefor(day));
    }
    private void deleteNodePRdatafor(int day){

        try {

            switch(day){
                case 0:
                    SQLiteDatabase db0 = this.getWritableDatabase();
                    db0.delete(TABLE_PRDAYS_SUN,1+"="+1 , null);
                    db0.close();
                    break;
                case 1:
                    SQLiteDatabase db1 = this.getWritableDatabase();
                    db1.delete(TABLE_PRDAYS_MON,1+"="+1 , null);
                    db1.close();
                    break;
                case 2:
                    SQLiteDatabase db2 = this.getWritableDatabase();
                    db2.delete(TABLE_PRDAYS_TUE,1+"="+1 , null);
                    db2.close();
                    break;
                case 3:
                    SQLiteDatabase db3 = this.getWritableDatabase();
                    db3.delete(TABLE_PRDAYS_WED,1+"="+1 , null);
                    db3.close();
                    break;
                case 4:
                    SQLiteDatabase db4 = this.getWritableDatabase();
                    db4.delete(TABLE_PRDAYS_THU,1+"="+1 , null);
                    db4.close();
                    break;

                case 5:
                    SQLiteDatabase db5 = this.getWritableDatabase();
                    db5.delete(TABLE_PRDAYS_FRI,1+"="+1 , null);
                    db5.close();
                    break;
                case 6:
                    SQLiteDatabase db6 = this.getWritableDatabase();
                    db6.delete(TABLE_PRDAYS_SAT,1+"="+1 , null);
                    db6.close();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String queryPRnodefor(int day){

        switch(day){
            case 0:
                SQLiteDatabase db0 = this.getReadableDatabase();
                String query_params0 = "SELECT " + "*" + " FROM " + TABLE_PRDAYS_SUN;
                Cursor cSor0 = db0.rawQuery(query_params0, null);
                if(cSor0.moveToFirst()){
                    do{
                        return cSor0.getString(cSor0.getColumnIndexOrThrow(SapphirePrivateDB.COLUMN_PR_DAYS));
                    }while(cSor0.moveToNext());
                }else{
                    return null;
                }

            case 1:
                SQLiteDatabase db1 = this.getReadableDatabase();
                String query_params1 = "SELECT " + "*" + " FROM " + TABLE_PRDAYS_MON;
                Cursor cSor1 = db1.rawQuery(query_params1, null);
                if(cSor1.moveToFirst()){
                    do{
                        return cSor1.getString(cSor1.getColumnIndexOrThrow(SapphirePrivateDB.COLUMN_PR_DAYS));
                    }while(cSor1.moveToNext());
                }else{
                    return null;
                }

            case 2:
                SQLiteDatabase db2 = this.getReadableDatabase();
                String query_params2 = "SELECT " + "*" + " FROM " + TABLE_PRDAYS_TUE;
                Cursor cSor2 = db2.rawQuery(query_params2, null);
                if(cSor2.moveToFirst()){
                    do{
                        return cSor2.getString(cSor2.getColumnIndexOrThrow(SapphirePrivateDB.COLUMN_PR_DAYS));
                    }while(cSor2.moveToNext());
                }else{
                    return null;
                }

            case 3:
                SQLiteDatabase db3 = this.getReadableDatabase();
                String query_params3 = "SELECT " + "*" + " FROM " + TABLE_PRDAYS_WED;

                Cursor cSor3 = db3.rawQuery(query_params3, null);
                if(cSor3.moveToFirst()){
                    do{
                        return cSor3.getString(cSor3.getColumnIndexOrThrow(SapphirePrivateDB.COLUMN_PR_DAYS));
                    }while(cSor3.moveToNext());
                }else{
                    return null;
                }

            case 4:
                SQLiteDatabase db4 = this.getReadableDatabase();
                String query_params4 = "SELECT " + "*" + " FROM " + TABLE_PRDAYS_THU;
                Cursor cSor4 = db4.rawQuery(query_params4, null);
                if(cSor4.moveToFirst()){
                    do{
                        return cSor4.getString(cSor4.getColumnIndexOrThrow(SapphirePrivateDB.COLUMN_PR_DAYS));
                    }while(cSor4.moveToNext());
                }else{
                    return null;
                }

            case 5:
                SQLiteDatabase db5 = this.getReadableDatabase();
                String query_params5 = "SELECT " + "*" + " FROM " + TABLE_PRDAYS_FRI;
                Cursor cSor5 = db5.rawQuery(query_params5, null);
                if(cSor5.moveToFirst()){
                    do{
                        return cSor5.getString(cSor5.getColumnIndexOrThrow(SapphirePrivateDB.COLUMN_PR_DAYS));
                    }while(cSor5.moveToNext());
                }else{
                    return null;
                }

            case 6:
                SQLiteDatabase db6 = this.getReadableDatabase();
                String query_params6 = "SELECT " + "*" + " FROM " + TABLE_PRDAYS_SAT;
                Cursor cSor6 = db6.rawQuery(query_params6, null);
                if(cSor6.moveToFirst()){
                    do{
                        return cSor6.getString(cSor6.getColumnIndexOrThrow(SapphirePrivateDB.COLUMN_PR_DAYS));
                    }while(cSor6.moveToNext());
                }else{
                    return null;
                }

            default:
                return null;

        }

    }

}
