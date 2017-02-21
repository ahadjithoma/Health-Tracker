package com.healthtrackerinc.healthtracker;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.location.Address;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import au.com.bytecode.opencsv.CSVReader;

import static android.R.id.list;
import static java.lang.String.valueOf;

public class PharmachiesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public String location = "Όλα";
    public String workingHours = "Όλα";
    public List<String[]> list = new ArrayList<String[]>();
    public String jsonFileName = "pharmacies.json";
    private ListView listView;
    ArrayList<HashMap<String, String>> contactList;
    public JSONObject jsonObj;
    public JSONArray contacts;
    public ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmachies);

        listView = (ListView) findViewById(R.id.list_view);


        //JSON Data
        try {
            getJSONData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        /** Spinner Working Hours**/

        // Spinner element
        Spinner spinnerSp = (Spinner) findViewById(R.id.workingHours_spinner);

        // Spinner click listener
        spinnerSp.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categoriesSp = new ArrayList<String>();
        categoriesSp.add("Όλα");
        categoriesSp.add("Πρωί");
        categoriesSp.add("Μεσημέρι");
        categoriesSp.add("Απόγευμα");
        categoriesSp.add("Βράδυ");
        categoriesSp.add("Μεσάνυχτα");
        categoriesSp.add("24/7");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterSp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesSp);

        // Drop down layout style - list view with radio button
        dataAdapterSp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerSp.setAdapter(dataAdapterSp);


        /** Spinner Location **/

        // Spinner element
        Spinner spinnerLoc = (Spinner) findViewById(R.id.location_spinner);

        // Spinner click listener
        spinnerLoc.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categoriesLoc = new ArrayList<String>();
        categoriesLoc.add("Όλα");
        categoriesLoc.add("Αθήνα");
        categoriesLoc.add("Θεσσαλονίκη");
        categoriesLoc.add("Λάρισα");
        categoriesLoc.add("Πάτρα");
        categoriesLoc.add("Βέροια");
        categoriesLoc.add("Κύπρος");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterLoc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesLoc);

        // Drop down layout style - list view with radio button
        dataAdapterLoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerLoc.setAdapter(dataAdapterLoc);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, String> hashMap;
                hashMap = contactList.get(position);
                DialogFragment dialogFragment = new DisplayInfoDialogFragment();

                //pass arguments to Dialog using bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("hashMap", hashMap);
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getFragmentManager(), "info");
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

        if (adapterView.getId() == R.id.workingHours_spinner) {
            workingHours = adapterView.getItemAtPosition(pos).toString();
        }
        if (adapterView.getId() == R.id.location_spinner) {
            location = adapterView.getItemAtPosition(pos).toString();
        }

        try {
            display();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //displayResults();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // do something
    }

    public void  display() throws JSONException {

        contactList.clear();

        // looping through All Contacts
        for (int i = 0; i < contacts.length(); i++) {
            JSONObject c = contacts.getJSONObject(i);

            String name = c.getString("name");
            String surname = c.getString("surname");
            String workingHours = c.getString("working_hours");
            String loc = c.getString("address");
            String phone = c.getString("phone");

            // tmp hash map for single contact
            HashMap<String, String> contact = new HashMap<>();

            if (workingHours.contains(workingHours) && loc.contains(location)) {
                // adding each child node to HashMap key => value
                contact.put("name", name);
                contact.put("surname", surname);
                contact.put("working_hours", workingHours);
                contact.put("phone", phone);
                contact.put("address", loc);
                // adding contact to contact list
                contactList.add(contact);

            } else if (workingHours.equals("Όλα") && loc.contains(location)){
                // adding each child node to HashMap key => value
                contact.put("name", name + " " + surname);
                contact.put("working_hours", workingHours);
                contact.put("phone", phone);
                contact.put("address", loc);
                // adding contact to contact list
                contactList.add(contact);

            } else if ( workingHours.contains(workingHours) && location.equals("Όλα")){
                // adding each child node to HashMap key => value
                contact.put("name", name + " " + surname);
                contact.put("working_hours", workingHours);
                contact.put("phone", phone);
                contact.put("address", loc);
                // adding contact to contact list
                contactList.add(contact);

            } else if (workingHours.equals("Όλα") && location.equals("Όλα")){
                // adding each child node to HashMap key => value
                contact.put("name", name + " " + surname);
                contact.put("working_hours", workingHours);
                contact.put("phone", phone);
                contact.put("address", loc);
                // adding contact to contact list
                contactList.add(contact);
            }
        }

        adapter = new SimpleAdapter(
                PharmachiesActivity.this, contactList, R.layout.list_item,
                new String[]{"name", "working_hours", "address"}, new int[]{R.id.title, R.id.semi_title, R.id.bottom_title});

        listView.setAdapter(adapter);
    }


    /** Functions for JSON Handle **/

    public void getJSONData() throws IOException, JSONException {
        String jsonStr = loadJSONFromAssets();
        contactList = new ArrayList<>();

        if (jsonStr!=null) {
            jsonObj = new JSONObject(jsonStr);

            // Getting JSON Array node
            contacts = jsonObj.getJSONArray("pharmacies");
        }

    }

    public String loadJSONFromAssets() {
        String json = null;
        try {
            InputStream is = getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
