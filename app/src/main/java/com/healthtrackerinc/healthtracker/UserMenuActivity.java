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

    }

    public void onClickDoctors(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, DoctorsActivity.class);
        startActivity(myIntent);
    }

    public void onClickDiseasesHistory(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, DiseasesHistoryActivity.class);
        startActivity(myIntent);
    }


}
