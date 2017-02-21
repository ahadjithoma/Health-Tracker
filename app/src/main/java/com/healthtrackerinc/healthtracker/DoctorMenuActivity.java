package com.healthtrackerinc.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_menu);
    }

    public void onClickPatientHistory(View view){
        Intent myIntent = new Intent(DoctorMenuActivity.this, PatientHistoryActivity.class);
        startActivity(myIntent);
    }

    public void onClickDrugList(View view){
        Intent myIntent = new Intent(DoctorMenuActivity.this, DrugListActivity.class);
        startActivity(myIntent);
    }

    public void onClickActiveSubstances(View view){
        Intent myIntent = new Intent(DoctorMenuActivity.this, ActiveSubstanceActivity.class);
        startActivity(myIntent);
    }
    
}
