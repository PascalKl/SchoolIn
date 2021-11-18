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
    public static final String TABLE_EDUCATION = "educational background";

    //Variables for table Schools
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SCHOOLNAME = "school";
    public static final String COLUMN_LOCATION = "location";

    //Variables for table Educational Background
    public static final String COLUMN_ID2 = "_id";
    public static final String COLUMN_EDUCATION_NAME = "education";

    //Table Schools
    public static final String SQL_CREATE_SCHOOLS =
            "CREATE TABLE " + TABLE_SCHOOL +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SCHOOLNAME + " TEXT NOT NULL, " +
                    COLUMN_LOCATION + " TEXT NOT NULL)";

    //Table Educational Background
    public static final String SQL_CREATE_EDUCATION =
            "CREATE TABLE " + TABLE_SCHOOL +
                    "(" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EDUCATION_NAME + " TEXT NOT NULL)";

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
        try {
            Log.d(LOG_TAG, "The Table is getting created: " + SQL_CREATE_SCHOOLS + " (via SQL-command).");
            db.execSQL(SQL_CREATE_SCHOOLS);
            Log.d(LOG_TAG, "The Table is getting created: " + SQL_CREATE_EDUCATION + " (via SQL-command).");
            db.execSQL(SQL_CREATE_EDUCATION);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Failed! An unexpected error occurred: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHOOL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EDUCATION);
        onCreate(db);
    }
}
