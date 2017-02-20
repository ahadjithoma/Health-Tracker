package com.healthtrackerinc.healthtracker;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    int btnId;
    Button btn;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //get the arguments from bundle
        Bundle bundle = getArguments();
        btnId = bundle.getInt("btnId");
        btn = (Button) getActivity().findViewById(btnId);

        /*
            - Does not work correctly with those Theme
            THEME_DEVICE_DEFAULT_LIGHT
            THEME_DEVICE_DEFAULT_DARK

            -Works fine with those Theme
            THEME_HOLO_LIGHT
            THEME_HOLO_DARK
            THEME_TRADITIONAL
         */
        //return new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK,this, year, month, day);


        //DatePickerDialog dpd = new DatePickerDialog(getActivity(),AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,this,year, month, day);
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),AlertDialog.THEME_DEVICE_DEFAULT_DARK,this,year, month, day);
        //DatePickerDialog dpd = new DatePickerDialog(getActivity(),AlertDialog.THEME_HOLO_LIGHT,this,year, month, day);
        //DatePickerDialog dpd = new DatePickerDialog(getActivity(),AlertDialog.THEME_HOLO_DARK,this,year, month, day);
        //DatePickerDialog dpd = new DatePickerDialog(getActivity(),AlertDialog.THEME_TRADITIONAL,this,year, month, day);

        //Get the DatePicker instance from DatePickerDialog
        DatePicker dp = dpd.getDatePicker();

        //Set the CLEAR Button
        dpd.setButton(DialogInterface.BUTTON_NEUTRAL, "clear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (btn.getId() == R.id.startDayBtn )
                    btn.setText("Επιλογή Αρχικής Ημερομηνίας");
                else if (btn.getId() == R.id.endDayBtn )
                btn.setText("Επιλογή Τελικής Ημερομηνίας");
            }
        });

        //Set the DatePicker maximum date selection to current date
        dp.setMaxDate(c.getTimeInMillis());//get the current day

        //Set a title for DatePickerDialog
        dpd.setTitle("");
        //dpd.setTitle("Select Date"); //In DEVICE_DEFAULT Themes it does not work properly

        return dpd; //Return the DatePickerDialog in app window*/
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //Do something with the date chosen by the user
/*
        //get the arguments from bundle
        Bundle bundle = getArguments();
        int btnId = bundle.getInt("btnId");*/
        Button btn = (Button) getActivity().findViewById(btnId);

        //set selected date to the button
        month++; //because it starts from 0 to 11
        btn.setText(day+"/"+month+"/"+year);
        btn.setTextColor(Color.parseColor("#313F68"));
        btn.setTypeface(btn.getTypeface(), Typeface.BOLD);

    }

}