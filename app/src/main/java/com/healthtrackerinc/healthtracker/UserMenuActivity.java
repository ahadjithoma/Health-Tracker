package com.healthtrackerinc.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        Button doctorsBtn = (Button)findViewById(R.id.doctorsBtn);

        // Register the onClick listener
        doctorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserMenuActivity.this, DoctorsActivity.class);
                startActivity(myIntent);
            }
        });

        Button hospitalsBtn = (Button)findViewById(R.id.hospitalsBtn);

        // Register the onClick listener
        hospitalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserMenuActivity.this, HospitalsActivity.class);
                startActivity(myIntent);
            }
        });

        Button pharmaciesBtn = (Button)findViewById(R.id.pharmaciesBtn);

        // Register the onClick listener
        doctorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserMenuActivity.this, PharmachiesActivity.class);
                startActivity(myIntent);
            }
        });

        Button diseasesBtn = (Button)findViewById(R.id.diseasesBtn);

        // Register the onClick listener
        doctorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserMenuActivity.this, DiseasesHistoryActivity.class);
                startActivity(myIntent);
            }
        });

        Button medicinesBtn = (Button)findViewById(R.id.medicinesBtn);

        // Register the onClick listener
        doctorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserMenuActivity.this, DrugHistory.class);
                startActivity(myIntent);
            }
        });

        Button mapsBtn = (Button)findViewById(R.id.mapsBtn);

        // Register the onClick listener
        doctorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserMenuActivity.this, MapsActivity.class);
                startActivity(myIntent);
            }
        });

        Button settingsBtn = (Button)findViewById(R.id.settingsBtn);

        // Register the onClick listener
        doctorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserMenuActivity.this, SettingsActivity.class);
                startActivity(myIntent);
            }
        });


    }

    /*public void onClickDoctors(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, DoctorsActivity.class);
        startActivity(myIntent);
    }

    public void onClickDiseasesHistory(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, DiseasesHistoryActivity.class);
        startActivity(myIntent);
    }

    public void onClickHospitals(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, HospitalsActivity.class);
        startActivity(myIntent);
    }

    public void onClickPharmachies(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, PharmachiesActivity.class);
        startActivity(myIntent);
    }

    public void onClickDrugHistory(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, DrugHistory.class);
        startActivity(myIntent);
    }

    public void onClickMaps(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, MapsActivity.class);
        startActivity(myIntent);
    }

    public void onClickSettings(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, SettingsActivity.class);
        startActivity(myIntent);
    }*/


}
