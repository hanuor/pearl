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

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hanuor.utils.GetDayUtil;


public class PrivateDatabaseHelper extends SQLiteOpenHelper {
    private GetDayUtil getDayUtil = new GetDayUtil();
    private static final String PRIVATETREEDBNAME = "SapphirePrivateInit.db";
    private static final String GLOBALTABLENAME = "DataCollector";
    private static final String COLUMN_0 = "Sun";
    private static final String COLUMN_1 = "Mon";
    private static final String COLUMN_2 = "Tue";
    private static final String COLUMN_3 = "Wed";
    private static final String COLUMN_4 = "Thu";
    private static final String COLUMN_5 = "Fri";
    private static final String COLUMN_6 = "Sat";
    private static final int DB_Version = 1;

    public PrivateDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public PrivateDatabaseHelper(Context context) {
        super(context, PRIVATETREEDBNAME, null, DB_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLECREATE = "CREATE TABLE "+ GLOBALTABLENAME + "("+
                COLUMN_0 + " REAL," + COLUMN_1 + " REAL," +
                COLUMN_2 + " REAL," + COLUMN_3 + " REAL," +
                COLUMN_4 + " REAL," + COLUMN_5 + " REAL," +
                COLUMN_6 + " REAL" + ");";
        sqLiteDatabase.execSQL(TABLECREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public double retrieveNodeColumnValue(int columnSET){
        //Coumn set is the passed day here.
        String GETCOLUMNNAME = null;
        SQLiteDatabase db = this.getReadableDatabase();

        switch (columnSET){
            case 0:
                GETCOLUMNNAME = "Sun";
                break;
            case 1:
                GETCOLUMNNAME = "Mon";
                break;
            case 2:
                GETCOLUMNNAME = "Tue";
                break;
            case 3:
                GETCOLUMNNAME = "Wed";
                break;
            case 4:
                GETCOLUMNNAME = "Thu";
                break;
            case 5:
                GETCOLUMNNAME = "Fri";
                break;
            case 6:
                GETCOLUMNNAME = "Sat";
                break;
            default:
                GETCOLUMNNAME = "Sun";
                break;
        }
        Log.d("GetColumn", ""+ GETCOLUMNNAME);
        String query_params = "SELECT " + GETCOLUMNNAME + " FROM " + GLOBALTABLENAME;
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{

                Log.d("GetColumnreturnValue"," " + 1.00);
                return cSor.getDouble(cSor.getColumnIndex(GETCOLUMNNAME));
            }while(cSor.moveToNext());

        }else{
            Log.d("GetColumnreturnValue"," " + 0.0 + " DAY   " + getDayUtil.getDay());
            return 0.0;
        }
    }

    public void enterColumn0Node(double value){
        //the below couple of lines should be done in JAR file
        double prevValue = retrieveNodeColumnValue(0);
        double newValue = prevValue + prevValue;
        SQLiteDatabase db = this.getWritableDatabase();

    }
}
