package com.example.schoolin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ramotion.foldingcell.FoldingCell;

public class MainActivity_searchpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_searchpage);

        // get our folding cell
        final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);
        //Customize folding cell
        fc.initialize(1000, Color.rgb(219, 219, 219), 1);
        // attach click listener to folding cell
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });


        //Hide Actionbar
        getSupportActionBar().hide();

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
                        //navigate to user (school) page
                        //startActivity(new Intent(MainActivity.this, MainActivity_userpage.class));
                        break;
                }
                return true;
            }
        });
    }
}