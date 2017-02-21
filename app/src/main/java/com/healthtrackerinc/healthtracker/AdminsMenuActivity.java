package com.healthtrackerinc.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminsMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins_menu);
    }


    public void onClickDoctors(View view){
        Intent myIntent = new Intent(AdminsMenuActivity.this, DoctorsActivity.class);
        startActivity(myIntent);
    }

    public void onClickHospitals(View view){
        Intent myIntent = new Intent(AdminsMenuActivity.this, HospitalsActivity.class);
        startActivity(myIntent);
    }
}
