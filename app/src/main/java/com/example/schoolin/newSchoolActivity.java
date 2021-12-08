package com.example.schoolin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class newSchoolActivity extends AppCompatActivity {

    DbHelper db;
    //Datentypen
    private EditText editSchoolName;
    private EditText editLocation;
    private EditText editEducation;
    private View.OnClickListener listener;
    private Button createNewSchool;


    public newSchoolActivity (){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_school);

        editSchoolName = (EditText) findViewById(R.id.schoolname_editText);
        editLocation = (EditText) findViewById(R.id.location_editText) ;
        editEducation = (EditText) findViewById(R.id.school_education);
        createNewSchool = (Button) findViewById(R.id.schoolCreate);

        db= new DbHelper(this);

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainActivity_searchpage().schoolNewDialog();
            }
        };
        School s1 = new School(editSchoolName.toString(), editLocation.toString(), editEducation.toString());
        db.addSchool(s1);
        createNewSchool.setOnClickListener(listener);

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
                        startActivity(new Intent(newSchoolActivity.this, MainActivity.class));
                        break;
                    case R.id.page_search:
                        //navigate to search page (this activity)
                        startActivity(new Intent(newSchoolActivity.this, MainActivity_searchpage.class));
                        break;
                    case R.id.page_user:
                        //navigate to user (School) page
                        //startActivity(new Intent(newSchool.this, newSchool.class));
                        break;
                }
                return true;
            }
        });
    }
}