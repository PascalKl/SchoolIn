package com.example.schoolin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity.*;
import androidx.fragment.app.Fragment;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String SCHOOL_TABLE = "SCHOOL_TABLE";
    public static final String COLUMN_SCHOOL_NAME = "SCHOOL_NAME";
    public static final String COLUMN_SCHOOL_LOCATION = "SCHOOL_LOCATION";
    public static final String COLUMN_SCHOOL_DESCRIPTION = "SCHOOL_DESCRIPTION";
    public static final String COLUMN_SCHOOL_WEBSITE= "SCHOOL_WEBSITE";
    public static final String COLUMN_SCHOOL_EDUCATION_1 = "SCHOOL_EDUCATION_1";
    public static final String COLUMN_SCHOOL_EDUCATION_2 = "SCHOOL_EDUCATION_2";
    public static final String COLUMN_SCHOOL_EDUCATION_3 = "SCHOOL_EDUCATION_3";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "school.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE "
                + SCHOOL_TABLE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SCHOOL_NAME + " TEXT, "
                + COLUMN_SCHOOL_LOCATION + " TEXT, "
                + COLUMN_SCHOOL_DESCRIPTION+ " TEXT, "
                + COLUMN_SCHOOL_WEBSITE + " TEXT, "
                + COLUMN_SCHOOL_EDUCATION_1 + " TEXT, "
                + COLUMN_SCHOOL_EDUCATION_2 + " TEXT, "
                + COLUMN_SCHOOL_EDUCATION_3 + " TEXT )";

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
        cv.put(COLUMN_SCHOOL_DESCRIPTION, school.getDescription());
        cv.put(COLUMN_SCHOOL_WEBSITE, school.getWebsite());
        cv.put(COLUMN_SCHOOL_EDUCATION_1, school.getEducation1());
        cv.put(COLUMN_SCHOOL_EDUCATION_2, school.getEducation2());
        cv.put(COLUMN_SCHOOL_EDUCATION_3, school.getEducation3());


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
    public void deleteOne(int schoolID){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + SCHOOL_TABLE + " WHERE " + COLUMN_ID + " = " + schoolID;

        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
    }
    public school showOne(school school){

        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + SCHOOL_TABLE + " WHERE " + COLUMN_ID + " = " + school.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        int customerID = cursor.getInt(0);
        String schoolName = cursor.getString(1);
        String schoolLocation = cursor.getString(2);
        String schoolDescription = cursor.getString(3);
        String schoolWebsite = cursor.getString(4);
        String schoolEducation1 = cursor.getString(5);
        String schoolEducation2 = cursor.getString(6);
        String schoolEducation3 = cursor.getString(7);
        cursor.close();
        db.close();
        return new school(customerID, schoolName, schoolLocation, schoolDescription, schoolWebsite, schoolEducation1, schoolEducation2, schoolEducation3);
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
                String schoolDescription = cursor.getString(3);
                String schoolWebsite = cursor.getString(4);
                String schoolEducation1 = cursor.getString(5);
                String schoolEducation2 = cursor.getString(6);
                String schoolEducation3 = cursor.getString(7);

                school newSchool = new school(customerID, schoolName, schoolLocation, schoolDescription, schoolWebsite, schoolEducation1, schoolEducation2, schoolEducation3);
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
                    String schoolDescription = cursor.getString(3);
                    String schoolWebsite = cursor.getString(4);
                    String schoolEducation1 = cursor.getString(5);
                    String schoolEducation2 = cursor.getString(6);
                    String schoolEducation3 = cursor.getString(7);

                    school newSchool = new school(customerID, schoolName, schoolLocation, schoolDescription, schoolWebsite, schoolEducation1, schoolEducation2, schoolEducation3);
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
    public List<school> getFilterResult(ArrayList<String> FilterRequest) {
        List<school> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + SCHOOL_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);


        if (cursor.moveToFirst()){
            do {

                int customerID = cursor.getInt(0);
                String schoolName = cursor.getString(1);
                String schoolLocation = cursor.getString(2);
                String schoolDescription = cursor.getString(3);
                String schoolWebsite = cursor.getString(4);
                String schoolEducation1 = cursor.getString(5);
                String schoolEducation2 = cursor.getString(6);
                String schoolEducation3 = cursor.getString(7);

                if(
                        FilterRequest.contains(schoolEducation1)
                        || FilterRequest.contains(schoolEducation2)
                        || FilterRequest.contains(schoolEducation3)
                ){
                    school newSchool = new school(customerID, schoolName, schoolLocation, schoolDescription, schoolWebsite, schoolEducation1, schoolEducation2, schoolEducation3);
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
