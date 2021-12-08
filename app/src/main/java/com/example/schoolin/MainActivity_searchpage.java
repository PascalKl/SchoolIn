package com.example.schoolin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity_searchpage extends AppCompatActivity{

    private static final String LOG_TAG = DbHelper.class.getSimpleName();

    private final String TABLE_NAME = "schools";
    private final String[] column = new String[]{"_id", "school", "location"};

    private final String sortString = column[1];

    private final String[] columnSelect = new String[] {column[0], column[1], column[2]};

    private String schoolName, location;

    private searchpageDataSource dataMethods;
    private School school;
    private Button schoolNewCreate;

    private EditText schoolEditText;
    private EditText locationEditText;

    private EditText schoolnameEdit;
    private EditText locationEdit;
    private Button schoolRefereshButton;
    private Button schoolDeleteButton;
    private Button schoolCancelButton;
    private View.OnClickListener listener;
    private View.OnClickListener listener1;

    private ListView schoolList;
    private Cursor schoolCursor;
    private ContentValues schoolData;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_searchpage);

        schoolList = (ListView) findViewById(R.id.schoollist);

        dataMethods = new searchpageDataSource(MainActivity_searchpage.this);

        //Database
        showListView();
        //

        //Foldingcell
        //get our folding cell
        //final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);
        //Customize folding cell
        //fc.initialize(1000, Color.rgb(219, 219, 219), 1);
        // attach click listener to folding cell
        //fc.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        fc.toggle(false);
        //    }
        //});


        //Hide Actionbar
        getSupportActionBar().hide();

        //Bottom Navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.page_search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_home:
                        //navigate to home page
                        startActivity(new Intent(MainActivity_searchpage.this, MainActivity.class));
                        break;
                    case R.id.page_search:
                        //navigate to search page (this activity)
                        //startActivity(new Intent(MainActivity_searchpage.this, MainActivity_searchpage.class));
                        break;
                    case R.id.page_user:
                        //navigate to user (School) page
                        startActivity(new Intent(MainActivity_searchpage.this, newSchoolActivity.class));
                        break;
                }
                return true;
            }
        });

    }
    //ListView
    public void showListView() {
        String [] listColumn = new String[]{column[0], column[1], column[2]};

        schoolCursor = dataMethods.showTable(TABLE_NAME , columnSelect, sortString);

        adapter = new SimpleCursorAdapter(MainActivity_searchpage.this, R.layout.foldingcell, schoolCursor, listColumn, new int[]{ R.id.schoolname_fc, R.id.location_fc}, CursorAdapter.NO_SELECTION);

        schoolList.setAdapter(adapter);
    }
    //
    //Database new entry

    public void schoolNewDialog(){
        final Dialog dialog = new Dialog(MainActivity_searchpage.this);
        dialog.setContentView(R.layout.activity_new_school);

        Log.d(LOG_TAG, "Created TEST1");

        dialog.setTitle("schools");

        schoolEditText = (EditText) dialog.findViewById(R.id.schoolname_editText);
        locationEditText = (EditText) dialog.findViewById(R.id.location_editText);

        schoolNewCreate = (Button) dialog.findViewById(R.id.schoolCreate);
                schoolName = schoolEditText.getText().toString();
                location = locationEditText.getText().toString();

                //saveAndShow();
              }
    //public void saveAndShow() {
        //school = new School(schoolName, location );
       // schoolData = schoolSentence(school);
        //dataMethods.pasteData(TABLE_NAME, schoolData);
        //schoolCursor = dataMethods.showTable(TABLE_NAME, columnSelect, sortString);
        //adapter.changeCursor(schoolCursor);
    //}

    public ContentValues schoolSentence(School school){
        ContentValues data = new ContentValues();
        data.put("school", school.getSchoolName());
        data.put("location", school.getLocation());

        Log.d(LOG_TAG, "DbHelper has created: " + school.getSchoolName() + " with location " + school.getLocation());

        return data;
    }

    public void showEditSaveDialog(long id) {
        final Dialog dialog = new Dialog(MainActivity_searchpage. this, R.style. textStandard);

        dialog.setContentView(R.layout.activity_edit_school);

        dialog.setTitle("School");
        schoolnameEdit = (EditText)dialog.findViewById(R.id.schoolname_edit_EditText);

        locationEdit = (EditText)dialog.findViewById(R.id.location_edit_EditText);

        schoolDeleteButton = (Button)dialog.findViewById(R.id.delSchool_Button);

        schoolRefereshButton = (Button)dialog.findViewById(R.id.changeSchoolInformation_Button);

        schoolCancelButton = (Button)dialog.findViewById(R.id.schoolEditCancel_Button);

        schoolCursor = dataMethods.showData(TABLE_NAME, column, id);
        schoolCursor.moveToFirst();

        //school = schoolObject(schoolCursor);

        schoolnameEdit.setText(school.getSchoolName());
        locationEdit.setText(school.getLocation());

        listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*if(v == schoolDeleteButton){
                    dataMethods.deleteData(TABLE_NAME, school.getEducation());
                    showListView();
                    dialog.dismiss();
                } else
                    if (v == schoolRefereshButton) {
                    school.setSchoolName(schoolnameEdit.getText().toString());
                    school.setLocation(locationEdit.getText().toString());

                    schoolData = schoolSentence(school);

                    dataMethods.changeData(TABLE_NAME, schoolData, school.getId());

                    showListView();
                    dialog.dismiss();
                } else
                    if (v == schoolCancelButton){
                        dialog.dismiss();
                    }*/
            }
        };
        schoolCancelButton.setOnClickListener(listener);
        schoolRefereshButton.setOnClickListener(listener);
        schoolDeleteButton.setOnClickListener(listener);

        dialog.show();
    }

    /*public School schoolObject(Cursor cursor){

        School school = new School();

        school.setEducation(cursor.getLong(0));
        school.setSchoolName(cursor.getString(1));
        school.setLocation(cursor.getString(2));

        cursor.close();

        return school;

    } */

    public void closeSchoolActivity() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("School", schoolName);
        finish();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(!schoolCursor.isClosed()){
            schoolCursor.close();
        }
        dataMethods.close();
    }
    //

}