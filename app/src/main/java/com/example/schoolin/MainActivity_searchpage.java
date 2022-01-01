package com.example.schoolin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity_searchpage extends AppCompatActivity{

    ListView lv_school;

    DataBaseHelper dataBaseHelper;
    EditText searchConsole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_searchpage);

        lv_school = (ListView) findViewById(R.id.schoollist);
        searchConsole = (EditText) findViewById(R.id.search);

        dataBaseHelper = new DataBaseHelper(MainActivity_searchpage.this);
        ShowSchoolOnListView(dataBaseHelper);

        searchConsole.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ListAdapter customAdapter = new ListAdapter(MainActivity_searchpage.this, R.layout.listview_1, dataBaseHelper.getSearchResult(searchConsole.getText().toString()));
                lv_school.setAdapter(customAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(searchConsole.getText().toString().equals("")){
                    ListAdapter customAdapter = new ListAdapter(MainActivity_searchpage.this, R.layout.listview_1, dataBaseHelper.getEveryone());
                    lv_school.setAdapter(customAdapter);
                }
            }
        });

        lv_school.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                school clickedCustomer = (school) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedCustomer);
                ShowSchoolOnListView(dataBaseHelper);
                Toast.makeText(MainActivity_searchpage.this, "Deleted " + clickedCustomer, Toast.LENGTH_SHORT).show();
            }
        });

        //
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


    public void ShowSchoolOnListView(DataBaseHelper dataBaseHelper) {
        ListAdapter customAdapter = new ListAdapter(MainActivity_searchpage.this, R.layout.listview_1, dataBaseHelper.getEveryone());
        lv_school.setAdapter(customAdapter);
    }
}