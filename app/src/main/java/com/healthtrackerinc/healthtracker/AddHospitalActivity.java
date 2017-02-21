package com.healthtrackerinc.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddHospitalActivity extends AppCompatActivity {

    EditText name, address, phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospital);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.tel);
    }


    public void onClickOk(View view){

        AddHospital.getInstance().setAdd(1);
        AddHospital.getInstance().setAddress(address.toString());

    }

    public void onClickCancel(View view){
        Intent myIntent = new Intent(AddHospitalActivity.this, HospitalsActivity.class);
        startActivity(myIntent);
    }
}
