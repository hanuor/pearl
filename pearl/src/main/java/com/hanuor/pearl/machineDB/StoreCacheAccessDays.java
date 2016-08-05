package com.hanuor.pearl.machineDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Shantanu Johri on 02-08-2016.
 */
public class StoreCacheAccessDays extends SQLiteOpenHelper{

    public static final String DB_NAME = "MindValley.db";
    private static final String TABLE_NAME = "daysCount";

    private static final int DB_VERSION = 1;

    private static final String DAYS = "days";
    public StoreCacheAccessDays(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public StoreCacheAccessDays(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_ID = "CREATE TABLE " + TABLE_NAME + "("+
                DAYS + " STRING"
                + ")";
        sqLiteDatabase.execSQL(TABLE_ID);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void insert(String day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DAYS, day);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }
    public void clear() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, 1 + "=" + 1, null);
        db.close();
    }
    public int getLength(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + DAYS + " FROM " + TABLE_NAME;
        Cursor cSor = db.rawQuery(query_params, null);
        Log.d("Mlearning", "" + cSor.getCount());
        return cSor.getCount();
    }
    public String query(){
        String daemon = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + DAYS + " FROM " + TABLE_NAME;
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                daemon =  cSor.getString(cSor.getColumnIndex(StoreCacheAccessDays.DAYS));

            }while(cSor.moveToNext());

        }
        return daemon;

    }
}
