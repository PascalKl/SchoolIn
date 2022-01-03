package com.example.schoolin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class showSchool extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;

    TextView schoolName, location, description;
    Button websiteButton;
    ImageView cancel, delete, favorite;
    ListView educationView;
    int schoolID;
    boolean isFavorite;
    View.OnClickListener cancelListener, deleteListener, websiteListener, favoriteListener;
    String hrefWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_school);

        dataBaseHelper = new DataBaseHelper(showSchool.this);

        Intent i = getIntent();
        schoolName = (TextView) findViewById(R.id.schoolName_showView);
        schoolName.setText(i.getStringExtra("schoolName"));

        location = (TextView) findViewById(R.id.location_showView);
        location.setText(i.getStringExtra("schoolLocation"));

        description = (TextView) findViewById(R.id.description_showView);
        description.setText(i.getStringExtra("schoolDescription"));

        educationView = (ListView) findViewById(R.id.educationListView);
        websiteButton = (Button) findViewById(R.id.websiteBt);
        hrefWebsite = i.getStringExtra("schoolWebsite");

        cancel = (ImageView) findViewById(R.id.closeBt);
        delete = (ImageView) findViewById(R.id.deleteBt);
        String page = i.getStringExtra("page");
        Intent backIntent;
        if(page.equals("searchpage")){
            backIntent = new Intent(showSchool.this,MainActivity_searchpage.class);
        }
        else{
            backIntent = new Intent(showSchool.this,MainActivity.class);
        }
        favorite = (ImageView) findViewById(R.id.favoriteBt);
        isFavorite = i.getBooleanExtra("schoolFavorite", false);
        if (isFavorite){
            favorite.setColorFilter(getResources().getColor(R.color.gold));
        }
        else{
            favorite.setColorFilter(getResources().getColor(R.color.black));
        }
        schoolID =  Integer.valueOf(i.getStringExtra("schoolID"));

        cancelListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel.setColorFilter(getResources().getColor(R.color.red));
                startActivity(backIntent);
            }
        }; cancel.setOnClickListener(cancelListener);

        deleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(schoolID == -1){
                    Toast.makeText(showSchool.this, "Error deleting school", Toast.LENGTH_SHORT).show();
                }
                delete.setColorFilter(getResources().getColor(R.color.red));
                dataBaseHelper.deleteOne(schoolID);
                startActivity(backIntent);
            }
        }; delete.setOnClickListener(deleteListener);

        websiteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+hrefWebsite));
                startActivity(i);
            }
        };websiteButton.setOnClickListener(websiteListener);

        favoriteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean setFavorite;
                if (isFavorite){
                    favorite.setColorFilter(getResources().getColor(R.color.black));
                    setFavorite = false;
                    isFavorite = false;
                    Toast.makeText(showSchool.this, i.getStringExtra("schoolName") + " wurde als Favorit entfernt.", Toast.LENGTH_SHORT).show();
                }
                else{
                    favorite.setColorFilter(getResources().getColor(R.color.gold));
                    setFavorite = true;
                    isFavorite = true;
                    Toast.makeText(showSchool.this, i.getStringExtra("schoolName") + " wurde als Favorit hinzugefügt.", Toast.LENGTH_SHORT).show();
                }
                dataBaseHelper.favoriteSchool(schoolID, setFavorite, showSchool.this);
            }
        }; favorite.setOnClickListener(favoriteListener);


        ArrayList<String> educationAr = i.getStringArrayListExtra("educationAr");
        ArrayAdapter<String> customAdapter = new ArrayAdapter<String>(showSchool.this, android.R.layout.simple_list_item_1,educationAr);
        educationView.setAdapter(customAdapter);

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
                        startActivity(new Intent(showSchool.this, MainActivity.class));
                        break;
                    case R.id.page_search:
                        //navigate to search page
                        startActivity(new Intent(showSchool.this, MainActivity_searchpage.class));
                        break;
                    case R.id.page_user:
                        //navigate to user (School) page
                        startActivity(new Intent(showSchool.this, newSchoolActivity.class));
                        break;
                }
                return true;
            }
        });
    }
}