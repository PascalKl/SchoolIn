package com.example.schoolin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView view;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (RecyclerView) findViewById(R.id.lv_favorite);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerViewAdapter customAdapter = new RecyclerViewAdapter(dataBaseHelper.getEveryoneIsFavorite(), new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(school clickedSchoolId) {
                Intent intent = new Intent(MainActivity.this, showSchool.class);

                school clickedSchool = dataBaseHelper.showOne(clickedSchoolId);

                intent.putExtra("schoolID", clickedSchool.getId());
                intent.putExtra("schoolName", clickedSchool.getName());
                intent.putExtra("schoolLocation", clickedSchool.getLocation());
                intent.putExtra("schoolDescription", clickedSchool.getDescription());
                intent.putExtra("schoolWebsite", clickedSchool.getWebsite());
                intent.putExtra("schoolFavorite",clickedSchool.isFavorite());
                intent.putExtra("page","home");
                ArrayList<String> ar = new ArrayList<String>();
                ar.add(clickedSchool.getEducation1());
                ar.add(clickedSchool.getEducation2());
                ar.add(clickedSchool.getEducation3());
                intent.putStringArrayListExtra("educationAr", ar);
                startActivity(intent);
            }
        });
        view.setLayoutManager(horizontalLayoutManager);
        view.setAdapter(customAdapter);

        //Hide Actionbar
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //deactivate Night Theme / Dark Theme (not implemented yet)

        //Bottom Navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_home:
                        //navigate to home page (this activity)
                        //startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.page_search:
                        //navigate to search page
                        startActivity(new Intent(MainActivity.this, MainActivity_searchpage.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.page_user:
                        //navigate to user (School) page
                        startActivity(new Intent(MainActivity.this, newSchoolActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });
    }
}