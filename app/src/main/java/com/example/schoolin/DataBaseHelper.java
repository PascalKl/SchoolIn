package com.example.schoolin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String SCHOOL_TABLE = "SCHOOL_TABLE";
    public static final String COLUMN_SCHOOL_NAME = "SCHOOL_NAME";
    public static final String COLUMN_SCHOOL_LOCATION = "SCHOOL_LOCATION";
    public static final String COLUMN_SCHOOL_EDUCATION = "SCHOOL_EDUCATION";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "school.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + SCHOOL_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SCHOOL_NAME + " TEXT, " + COLUMN_SCHOOL_LOCATION + " TEXT, " + COLUMN_SCHOOL_EDUCATION + " TEXT )";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //
    public boolean addOne(school school){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SCHOOL_NAME, school.getName());
        cv.put(COLUMN_SCHOOL_LOCATION, school.getLocation());
        cv.put(COLUMN_SCHOOL_EDUCATION, school.getEducation());

        long insert = db.insert(SCHOOL_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean deleteOne(school school){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + SCHOOL_TABLE + " WHERE " + COLUMN_ID + " = " + school.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        if ( cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }
    public List<school> getEveryone() {

        List<school> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + SCHOOL_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int customerID = cursor.getInt(0);
                String schoolName = cursor.getString(1);
                String schoolLocation = cursor.getString(2);
                String schoolEducation = cursor.getString(3);

                school newSchool = new school(customerID, schoolName, schoolLocation, schoolEducation);
                returnList.add(newSchool);

            }
            while(cursor.moveToNext());
        }
        else{
            //failure. Nothing to show.
        }
        cursor.close();
        db.close();
        return returnList;

    }

    public List<school> getSearchResult(String SearchRequest) {
        List<school> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + SCHOOL_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                if(
                        cursor.getString(1).contains(SearchRequest)
                        || cursor.getString(2).contains(SearchRequest)
                        || cursor.getString(3).contains(SearchRequest)
                ){
                    int customerID = cursor.getInt(0);
                    String schoolName = cursor.getString(1);
                    String schoolLocation = cursor.getString(2);
                    String schoolEducation = cursor.getString(3);

                    school newSchool = new school(customerID, schoolName, schoolLocation, schoolEducation);
                    returnList.add(newSchool);
                }
            }
            while(cursor.moveToNext());
        }
        else{
            //failure. Nothing to show.
        }

        cursor.close();
        db.close();
        return returnList;
    }
}
