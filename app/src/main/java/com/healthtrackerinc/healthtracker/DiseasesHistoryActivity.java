package com.healthtrackerinc.healthtracker;

import android.app.DialogFragment;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.healthtrackerinc.healthtracker.R.id.checkBox;

public class DiseasesHistoryActivity extends AppCompatActivity {

    public int id;
    public Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_history);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(DiseasesHistoryActivity.this, fab);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(DiseasesHistoryActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });

    }

    public void onButtonClicked(View v){
        DialogFragment dialogFragment = new DatePickerFragment();

        //pass arguments using bundle
        id = v.getId();  //get button id
        bundle.putInt("btnId",id);
        dialogFragment.setArguments(bundle);

        dialogFragment.show(getFragmentManager(),"Date Picker");
    }



}
