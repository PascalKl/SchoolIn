package com.example.schoolin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class newSchoolActivity extends AppCompatActivity {

    private EditText editLocation, /*editEducation,*/ editSchoolName;
    private Spinner education1, education2, education3;
    private View.OnClickListener listener;
    private Button createNewSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_school);

        editSchoolName = (EditText) findViewById(R.id.schoolname_editText);
        editLocation = (EditText) findViewById(R.id.location_editText);
        //editEducation = (EditText) findViewById(R.id.school_education);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.education_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        education1 = (Spinner) findViewById(R.id.education1_spinner);
        education2 = (Spinner) findViewById(R.id.education2_spinner);
        education3 = (Spinner) findViewById(R.id.education3_spinner);

        education1.setAdapter(adapter);
        education2.setAdapter(adapter);
        education3.setAdapter(adapter);

        createNewSchool = (Button) findViewById(R.id.schoolCreate);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                school school;
                boolean cancel = false;

                try {
                    school = new school(-1, editSchoolName.getText().toString(), editLocation.getText().toString(),/* editEducation.getText().toString(),*/ getEducations());
                    //Toast.makeText(newSchoolActivity.this, school.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(newSchoolActivity.this, "Error creating school", Toast.LENGTH_SHORT).show();
                    //school = new school(-1, "error", "no location");
                    school = null;
                    cancel = true;
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(newSchoolActivity.this);

                if (cancel != true) {
                    boolean success = dataBaseHelper.addOne(school);

                    Toast.makeText(newSchoolActivity.this, "Schule wurde hinzugefügt. " + success, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(newSchoolActivity.this, MainActivity_searchpage.class));
                }
                else{
                    Toast.makeText(newSchoolActivity.this, "Schule wurde NICHT hinzugefügt. Fehler.", Toast.LENGTH_SHORT).show();
                }
                bottomNavigationView.setSelectedItemId(R.id.page_search);
            }
        };
        createNewSchool.setOnClickListener(listener);

        education1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0) {
                    education2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //Nothing happens.
            }
        });
        education2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0) {
                    education3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //Nothing happens.
            }
        });


        //Hide Actionbar
        getSupportActionBar().hide();

        //Bottom Navigation

        bottomNavigationView.setSelectedItemId(R.id.page_user);

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

    private String getEducations() {
        String educations;
        String Education1 = ", " + education1.getSelectedItem().toString();
        if (Education1.equals("Auswählen")){
            Education1 = "";
        }
        String Education2 = ", " + education2.getSelectedItem().toString();
        if (Education2.equals("Auswählen")){
            Education2 = "";
        }
        String Education3 = ", " + education3.getSelectedItem().toString();
        if (Education3.equals("Auswählen")){
            Education3 = "";
        }
        educations = Education1 + Education2 + Education3;

        return educations;
    }
}