package com.example.schoolin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class searchpageDbHelper extends SQLiteOpenHelper{

    private static final String LOG_TAG = searchpageDbHelper.class.getSimpleName();

    //Database- and Table-constant are getting created
    public static final String DB_NAME = "schoollist.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_SCHOOL = "schools";

    //Variables for table Schools
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SCHOOLNAME = "school";
    public static final String COLUMN_LOCATION = "location";

    //Table Schools
    public static final String SQL_CREATE_SCHOOLS =
            "CREATE TABLE " + TABLE_SCHOOL +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SCHOOLNAME + " TEXT NOT NULL, " +
                    COLUMN_LOCATION + " TEXT NOT NULL)";

    //End of Database creation

    //Database is getting created
    public searchpageDbHelper(Context context) {
        //super(context, "PLACEHOLDER_DATABASENAME", null, 1);
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper has created: " + getDatabaseName() + "(database)");
    }
    //

    //Table at database is getting created via SQL command
    @Override
    public void onCreate(SQLiteDatabase db){

            Log.d(LOG_TAG, "The Table is getting created: " + SQL_CREATE_SCHOOLS + " (via SQL-command).");
            db.execSQL(SQL_CREATE_SCHOOLS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHOOL);
        onCreate(db);
    }
}
