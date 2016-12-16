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

public class TrainingTracker extends SQLiteOpenHelper {
    private static final String DB_NAME_VALUE = "TrainindData.db";
    private static final String TRAINING_TABLE = "TrainingTable";
    private static final String MON_COL = "MondayColumn";
    private static final String TUE_COL = "TuesdayColumn";
    private static final String WED_COL = "WednesdayColumn";
    private static final String THU_COL = "ThursdayColumn";
    private static final String FRI_COL = "FridayColumn";
    private static final String SAT_COL = "SaturdayColumn";
    private static final String SUN_COL = "SundayColumn";
    private static  String COLUMN;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ = "CREATE TABLE "+ TRAINING_TABLE + "("+
                MON_COL + " TEXT," + TUE_COL + " TEXT," +
                WED_COL + " TEXT," + THU_COL + " TEXT," +
                FRI_COL + " TEXT," + SAT_COL + " TEXT," +
                SUN_COL + " TEXT" + ");";
        sqLiteDatabase.execSQL(CREATE_);
    }
    private static int Db_VERSION_VALUE = 13;
    public TrainingTracker(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public TrainingTracker(Context context){
        super(context, DB_NAME_VALUE, null, Db_VERSION_VALUE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void updateValue(int day){
        String GETCOLUMNNAME = null;
        switch (day){
            case 0:
                GETCOLUMNNAME = SUN_COL;
                break;
            case 1:
                GETCOLUMNNAME = MON_COL;
                break;
            case 2:
                GETCOLUMNNAME = TUE_COL;
                break;
            case 3:
                GETCOLUMNNAME = WED_COL;
                break;
            case 4:
                GETCOLUMNNAME = THU_COL;
                break;
            case 5:
                GETCOLUMNNAME = FRI_COL;
                break;
            case 6:
                GETCOLUMNNAME = SAT_COL;
                break;
            default:
                GETCOLUMNNAME = SUN_COL;
                break;
        }

        //query here then store

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GETCOLUMNNAME, "stored");

        long ff = db.insert(TRAINING_TABLE, null, contentValues);
        Log.d("TrainingTracker",GETCOLUMNNAME+"  "+ff);
        db.close();
    }

    public void clearTable(){
        SQLiteDatabase db0 = this.getWritableDatabase();
        db0.delete(TRAINING_TABLE,1+"="+1 , null);
        db0.close();
    }
    public String queryTracker(int day, boolean forAll){

        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = null;
        COLUMN = null;
        switch (day){
            case 0:
                query_params = "SELECT " + SUN_COL + " FROM " + TRAINING_TABLE;
                COLUMN = SUN_COL;
                break;
            case 1:
                query_params = "SELECT " + MON_COL + " FROM " + TRAINING_TABLE;
                COLUMN = MON_COL;
                break;
            case 2:
                query_params = "SELECT " + TUE_COL + " FROM " + TRAINING_TABLE;
                COLUMN = TUE_COL;
                break;
            case 3:
                query_params = "SELECT " + WED_COL + " FROM " + TRAINING_TABLE;
                COLUMN = WED_COL;
                break;
            case 4:
                query_params = "SELECT " + THU_COL + " FROM " + TRAINING_TABLE;
                COLUMN = THU_COL;
                break;
            case 5:
                query_params = "SELECT " + FRI_COL + " FROM " + TRAINING_TABLE;
                COLUMN = FRI_COL;
                break;
            case 6:
                COLUMN = SAT_COL;
                query_params = "SELECT " + SAT_COL + " FROM " + TRAINING_TABLE;
                break;
            default:
                COLUMN = SUN_COL;
                query_params = "SELECT " + SUN_COL + " FROM " + TRAINING_TABLE;
                break;
        }
        if(!forAll){
            Cursor cSor = db.rawQuery(query_params, null);
            if(cSor.moveToFirst()){
                do{
                    return cSor.getString(cSor.getColumnIndexOrThrow(TrainingTracker.COLUMN));
                }while(cSor.moveToNext());
            }else{
                return null;
            }

        }else{

            String query_params0 = "SELECT " + SUN_COL + " FROM " + TRAINING_TABLE;
            String query_params1 = "SELECT " + MON_COL + " FROM " + TRAINING_TABLE;
            String query_params2 = "SELECT " + TUE_COL + " FROM " + TRAINING_TABLE;
            String query_params3 = "SELECT " + WED_COL + " FROM " + TRAINING_TABLE;
            String query_params4 = "SELECT " + THU_COL + " FROM " + TRAINING_TABLE;
            String query_params5 = "SELECT " + FRI_COL + " FROM " + TRAINING_TABLE;
            String query_params6 = "SELECT " + SAT_COL + " FROM " + TRAINING_TABLE;
            String queryArr[] = {query_params0, query_params1, query_params2,query_params3, query_params4, query_params5,query_params6};
            String corresP_D[] = {SUN_COL,MON_COL, TUE_COL, WED_COL, THU_COL, FRI_COL, SAT_COL};
            boolean validatorHelper[]  =new boolean[7];
            boolean validator = false;
            for(int i = 0; i<7; i++){
                Cursor cSor = db.rawQuery(queryArr[i], null);

                if(cSor.moveToFirst()){
                    do{

                        try {
                            String  Rvalue  =  cSor.getString(cSor.getColumnIndexOrThrow(corresP_D[i]));
                            Log.d("TrainingT",""+Rvalue + " for " + corresP_D[i]);
                            if(Rvalue.length()==0){
                                validator = false;
                            }else{
                                validator = true;
                            }
                            validatorHelper[i]  = validator;
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } finally {
                            continue;
                        }
                    }while(cSor.moveToNext());
                }else{
                    return null;
                }
            }

            if(areAllTrue(validatorHelper)){
                return "stored";
            }else{
                return null;
            }

        }
    }
    public static boolean areAllTrue(boolean[] array)
    {
        for(boolean b : array) if(!b) return false;
        return true;
    }
}
