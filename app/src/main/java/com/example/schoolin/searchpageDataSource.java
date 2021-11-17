package com.example.schoolin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class searchpageDataSource {
    private static final String LOG_TAG = searchpageDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private searchpageDbHelper dbHelper;


    public searchpageDataSource(Context context) {
        Log.d(LOG_TAG, "DataSource erzeugt dbHelper.");
        dbHelper = new searchpageDbHelper(context);
    }
}