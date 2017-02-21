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

        Button mapsBtn = (Button)findViewById(R.id.doctorsBtn);

        // Register the onClick listener
        mapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserMenuActivity.this, MapsActivity.class);
                startActivity(myIntent);
            }
        });
    }

    public void onClickDoctors(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, DoctorsActivity.class);
        startActivity(myIntent);
    }

    public void onClickDiseasesHistory(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, DiseasesHistoryActivity.class);
        startActivity(myIntent);
    }

    public void onClickMaps(View view){
        Intent myIntent = new Intent(UserMenuActivity.this, MapsActivity.class);
        startActivity(myIntent);
    }


}
