package com.example.schoolin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide Actionbar
        getSupportActionBar().hide();

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
                        break;
                    case R.id.page_user:
                        //navigate to user (School) page
                        startActivity(new Intent(MainActivity.this, newSchoolActivity.class));
                        break;
                }
                return true;
            }
        });
    }
}


