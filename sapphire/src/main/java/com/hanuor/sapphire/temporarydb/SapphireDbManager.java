package com.hanuor.sapphire.temporarydb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 15-08-2016.
 */

/*
Store tags and their values in a json format*/
public class SapphireDbManager extends SQLiteOpenHelper {


    private static final String DB_NAME = "SapphireInternal.db";
    private static final String TABLE_NAME = "clientManager";
    private static final String TABLE_JSONDOC= "jsonDocManager";
    private static final String ID_DOCS = "idoc";

    private static final String TABLE_TAGSDOC= "TagsDocManager";
    private static final String TAGS_SOTRAGE = "Tagsstorage";
    private static final String IMAGE_KEYTAG = "imagekeytag";

    private static final String TABLE_PR = "privatemoduletwo";
    private static final String COL_LISTTAGS =  "privateListTags";

    private static String LIST_SEPARATOR = "__,__";
    private static final int DB_VERSION = 1;

    public SapphireDbManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public SapphireDbManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_JDOCS = "CREATE TABLE "+ TABLE_JSONDOC + "("+
                ID_DOCS + " STRING" + ");";
        String TABLE_OFTAGS = "CREATE TABLE " + TABLE_TAGSDOC + "(" +
                TAGS_SOTRAGE + " STRING);";
        String TABLE_PRIVATETAGS = "CREATE TABLE " + TABLE_PR + "(" +
                COL_LISTTAGS + " STRING" + ");";
        sqLiteDatabase.execSQL(TABLE_PRIVATETAGS);
        sqLiteDatabase.execSQL(TABLE_OFTAGS);
        sqLiteDatabase.execSQL(TABLE_JDOCS);
}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void storeTags(ArrayList<String> tagsList){
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : tagsList) {
            stringBuffer.append(str).append(LIST_SEPARATOR);
        }
        int lastIndex = stringBuffer.lastIndexOf(LIST_SEPARATOR);
        stringBuffer.delete(lastIndex, lastIndex + LIST_SEPARATOR.length() + 1);
        clearJDocTable(1);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TAGS_SOTRAGE, stringBuffer.toString());
        db.insert(TABLE_TAGSDOC, null, contentValues);
        db.close();

    }
    public void insertJDoc(String Doc){
        clearJDocTable(0);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_DOCS, Doc);
        db.insert(TABLE_JSONDOC, null, contentValues);
        db.close();
        Log.d("Weate",""+query());
    }
    public void clearJDocTable(int switcher){
        switch(switcher){
            case 0:
                SQLiteDatabase db = this.getWritableDatabase();
                db.delete(TABLE_JSONDOC, 1 + "=" + 1, null);
                db.close();
                break;
            case 1:
                SQLiteDatabase dbnew = this.getWritableDatabase();
                dbnew.delete(TABLE_TAGSDOC, 1 + "=" + 1, null);
                dbnew.close();
                break;
        }
    }

    public String query(){
        String returnString = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + ID_DOCS + " FROM " + TABLE_JSONDOC;
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
                returnString =  cSor.getString(cSor.getColumnIndex(SapphireDbManager.ID_DOCS));
            }while(cSor.moveToNext());
        }
        return returnString;

    }

}
