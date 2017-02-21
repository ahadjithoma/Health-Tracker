package com.healthtrackerinc.healthtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    public void onClickOk(){

        AddHospital.getInstance().setAdd(1);
        AddHospital.getInstance().setAddress(address.toString());

    }

    public void onClickCancel(){

    }
}
