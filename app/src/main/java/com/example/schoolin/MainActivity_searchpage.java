package com.example.schoolin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.location.GnssAntennaInfo;
import android.location.GpsStatus;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity_searchpage extends AppCompatActivity implements Serializable {

    ListView lv_school;

    DataBaseHelper dataBaseHelper;
    EditText searchConsole;
    Button filterBt;
    View.OnClickListener listenerFilter;
    ScrollView scrollView;
    LinearLayout llCheckbox;
    CheckBox chbMINT, chbLanguage, chbSocialSciences, chbAesthetics, chbPE;

    boolean filterActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_searchpage);

        filterBt = (Button) findViewById(R.id.filter_bt);
        lv_school = (ListView) findViewById(R.id.schoollist);
        searchConsole = (EditText) findViewById(R.id.search);
        scrollView = (ScrollView) findViewById(R.id.scrollview1);

        llCheckbox = (LinearLayout) findViewById(R.id.linearlayout_checkbox);
        chbMINT = (CheckBox) findViewById(R.id.checkbox_mint);
        chbAesthetics = (CheckBox) findViewById(R.id.checkbox_aesthetic);
        chbSocialSciences = (CheckBox) findViewById(R.id.checkbox_gesellwissenschaften);
        chbPE = (CheckBox) findViewById(R.id.checkbox_sport);
        chbLanguage = (CheckBox) findViewById(R.id.checkbox_sprache);

        filterActive = false;

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
                if (searchConsole.getText().toString().equals("")) {
                    ListAdapter customAdapter = new ListAdapter(MainActivity_searchpage.this, R.layout.listview_1, dataBaseHelper.getEveryone());
                    lv_school.setAdapter(customAdapter);
                }
            }
        });

        lv_school.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                school clickedSchoolId = (school) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity_searchpage.this, showSchool.class);
                school clickedSchool = dataBaseHelper.showOne(clickedSchoolId);
                intent.putExtra("schoolID", clickedSchool.getId());
                intent.putExtra("schoolName", clickedSchool.getName());
                intent.putExtra("schoolLocation", clickedSchool.getLocation());
                intent.putExtra("schoolDescription", clickedSchool.getDescription());
                intent.putExtra("schoolWebsite", clickedSchool.getWebsite());
                ArrayList<String> ar = new ArrayList<String>();
                ar.add(clickedSchool.getEducation1());
                ar.add(clickedSchool.getEducation2());
                ar.add(clickedSchool.getEducation3());
                intent.putStringArrayListExtra("educationAr", ar);
                //intent.putExtra("schoolList", clickedSchool.getName());
                startActivity(intent);
                //dataBaseHelper.deleteOne(clickedCustomer);
                //ShowSchoolOnListView(dataBaseHelper);
                //Toast.makeText(MainActivity_searchpage.this, "Deleted " + clickedCustomer, Toast.LENGTH_SHORT).show();
            }
        });

        listenerFilter = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
                if (!filterActive) {
                    layoutParams.setMargins(0, 1100, 0, 56);
                    scrollView.setLayoutParams(layoutParams);
                    filterActive = true;
                    llCheckbox.setVisibility(View.VISIBLE);
                } else {
                    layoutParams.setMargins(0, 200, 0, 56);
                    scrollView.setLayoutParams(layoutParams);
                    ArrayList<Integer> arrayList = new ArrayList<Integer>();
                    int i = 0;
                    if (chbMINT.isChecked()) {
                        arrayList.add(R.array.mint);
                        i++;
                    }
                    if (chbLanguage.isChecked()) {
                        arrayList.add(R.array.languages);
                        i++;
                    }
                    if (chbSocialSciences.isChecked()) {
                        arrayList.add(R.array.socialSciences);
                        i++;
                    }
                    if (chbAesthetics.isChecked()) {
                        arrayList.add(R.array.aesthetics);
                        i++;
                    }
                    if (chbPE.isChecked()) {
                        arrayList.add(R.array.PE);
                        i++;
                    }
                    ListAdapter customAdapter;
                    if (i > 0) {
                        customAdapter = new ListAdapter(MainActivity_searchpage.this, R.layout.listview_1, dataBaseHelper.getFilterResult(getAllEducationArray(arrayList)));
                    } else {
                        customAdapter = new ListAdapter(MainActivity_searchpage.this, R.layout.listview_1, dataBaseHelper.getEveryone());
                    }
                    lv_school.setAdapter(customAdapter);
                    filterActive = false;
                    llCheckbox.setVisibility(View.GONE);
                }
            }
        };
        filterBt.setOnClickListener(listenerFilter);


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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public ArrayList<String> getAllEducationArray(ArrayList<Integer> educationPaths){
        ArrayList<String> ar = new ArrayList<String>();
        if(educationPaths.contains(R.array.aesthetics))
            ar.addAll(Arrays.asList(getResources().getStringArray(R.array.aesthetics)));
        if (educationPaths.contains(R.array.mint)){
            ar.addAll(Arrays.asList(getResources().getStringArray(R.array.mint)));
        }
        if (educationPaths.contains(R.array.PE)){
            ar.addAll(Arrays.asList(getResources().getStringArray(R.array.PE)));
        }
        if (educationPaths.contains(R.array.languages)){
            ar.addAll(Arrays.asList(getResources().getStringArray(R.array.languages)));
        }
        if (educationPaths.contains(R.array.socialSciences)){
            ar.addAll(Arrays.asList(getResources().getStringArray(R.array.socialSciences)));
        }
        return ar;
    }
}