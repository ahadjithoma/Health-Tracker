package com.healthtrackerinc.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_phramacy_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phramacy_menu);

    }

    public void onClickDrugs(View view){
        Intent myIntent = new Intent(activity_phramacy_menu.this, DrugActivity.class);
        startActivity(myIntent);
    }

    public void onClickCustomers(View view){
        Intent myIntent = new Intent(activity_phramacy_menu.this, CustomersActivity.class);
        startActivity(myIntent);
    }
}
