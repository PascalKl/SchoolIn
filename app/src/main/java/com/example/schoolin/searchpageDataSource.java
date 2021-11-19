package com.example.schoolin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class searchpageDataSource {
    private static final String LOG_TAG = searchpageDataSource.class.getSimpleName();

    private searchpageDbHelper dbHelper;
    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrite;
    private Cursor cursor;

    public searchpageDataSource(Context context){
        dbHelper = new searchpageDbHelper(context);
        dbRead = dbHelper.getReadableDatabase();
        dbWrite = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Test2!! ");
    }

    public Cursor showTable (String TABLE_NAME, String [] column, String sort) {

        Cursor cursor = dbRead.query(TABLE_NAME, column,
                null, null, null, null, sort);
        return cursor;
    }

    public long pasteData(String TABLE_NAME, ContentValues data) {

        long id = dbWrite.insert(TABLE_NAME, null, data);
        return id;
    }

    public Cursor showData(String TABLE_NAME, String [] column, long id){

        cursor = dbRead.query(TABLE_NAME, column, "_id = " + id, null, null, null, null);
        return cursor;
    }

    public void changeData(String TABLE_NAME, ContentValues data, long id) {
        dbWrite.update(TABLE_NAME, data, "_id = " + id, null);
    }

    public void deleteData(String TABLE_NAME, long id) {
        dbWrite.delete(TABLE_NAME, "_id = " + id, null);
    }
    public void close(){
        dbRead.close();
        dbWrite.close();
        dbHelper.close();
    }
}