package com.example.schoolin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity_searchpage extends AppCompatActivity{

    ListView lv_school;

    DataBaseHelper dataBaseHelper;
    EditText searchConsole;
    Button filterBt;
    View.OnClickListener listenerFilter;
    LinearLayout linearView;
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
        linearView = (LinearLayout) findViewById(R.id.linearlayout_1);

        llCheckbox = (LinearLayout) findViewById(R.id.linearlayout_checkbox);
        chbMINT = (CheckBox) findViewById(R.id.checkbox_mint);
        chbAesthetics = (CheckBox) findViewById(R.id.checkbox_aesthetic);
        chbSocialSciences = (CheckBox) findViewById(R.id.checkbox_gesellwissenschaften);
        chbPE = (CheckBox) findViewById(R.id.checkbox_sport);
        chbLanguage = (CheckBox) findViewById(R.id.checkbox_sprache);

        filterActive = false;

        dataBaseHelper = new DataBaseHelper(MainActivity_searchpage.this);
        ShowSchoolOnListView(dataBaseHelper, dataBaseHelper.getEveryone());

        searchConsole.addTextChangedListener(new TextWatcher() { //filter search requests during typing
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ShowSchoolOnListView(dataBaseHelper, dataBaseHelper.getSearchResult(searchConsole.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (searchConsole.getText().toString().equals("")) {
                    ShowSchoolOnListView(dataBaseHelper,dataBaseHelper.getEveryone());
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
                intent.putExtra("schoolFavorite",clickedSchool.isFavorite());
                intent.putExtra("page","searchpage");
                ArrayList<String> ar = new ArrayList<String>();
                ar.add(clickedSchool.getEducation1());
                ar.add(clickedSchool.getEducation2());
                ar.add(clickedSchool.getEducation3());
                intent.putStringArrayListExtra("educationAr", ar);
                startActivity(intent);
            }
        });

        listenerFilter = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearView.getLayoutParams();
                if (!filterActive) {
                    layoutParams.setMargins(0, 1100, 0, 56);
                    linearView.setLayoutParams(layoutParams);
                    filterActive = true;
                    llCheckbox.setVisibility(View.VISIBLE);
                    filterBt.setBackgroundColor(getResources().getColor(R.color.gray));
                } else {
                    filterBt.setBackgroundColor(getResources().getColor(R.color.lightblue));
                    layoutParams.setMargins(0, 200, 0, 56);
                    linearView.setLayoutParams(layoutParams);
                    ArrayList<String> arrayList = new ArrayList<String>();
                    int i = 0; //i describes the number of activated checkboxes
                    if (chbMINT.isChecked()) {
                        arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.mint)));
                        i++;
                    }
                    if (chbLanguage.isChecked()) {
                        arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.languages)));
                        i++;
                    }
                    if (chbSocialSciences.isChecked()) {
                        arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.socialSciences)));
                        i++;
                    }
                    if (chbAesthetics.isChecked()) {
                        arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.aesthetics)));
                        i++;
                    }
                    if (chbPE.isChecked()) {
                        arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.PE)));
                        i++;
                    }
                    ListAdapter customAdapter;
                    if (i > 0) { //if i is greater then 0, one checkbox is activated
                        ShowSchoolOnListView(dataBaseHelper,dataBaseHelper.getFilterResult(arrayList));
                    } else {//if i is not greater then 0, no checkbox is activated
                        ShowSchoolOnListView(dataBaseHelper, dataBaseHelper.getEveryone());
                    }
                    filterActive = false;
                    llCheckbox.setVisibility(View.GONE);
                }
            }
        };
        filterBt.setOnClickListener(listenerFilter);

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
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.page_search:
                        //navigate to search page (this activity)
                        //startActivity(new Intent(MainActivity_searchpage.this, MainActivity_searchpage.class)); //deactivated due to bugs
                        break;
                    case R.id.page_user:
                        //navigate to user (School) page
                        startActivity(new Intent(MainActivity_searchpage.this, newSchoolActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });
    }

    public void ShowSchoolOnListView(DataBaseHelper dataBaseHelper, List<school> schoolList) {
        ListAdapter customAdapter = new ListAdapter(MainActivity_searchpage.this, R.layout.listview_1, schoolList);
        lv_school.setAdapter(customAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}