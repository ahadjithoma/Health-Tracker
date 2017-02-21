package com.healthtrackerinc.healthtracker;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.location.Address;
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

public class HospitalsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public String location = "Όλα";
    public String specialty = "Όλα";
    public List<String[]> list = new ArrayList<String[]>();
    public int rowsNum, colNum;
    public String jsonFileName = "hospitals.json";
    private ListView listView;
    ArrayList<HashMap<String, String>> contactList;
    public JSONObject jsonObj;
    public JSONArray contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);


        //JSON Data
        try {
            getJSONData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



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



    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

        if (adapterView.getId()==R.id.location_spinner){
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


    public void getJSONData() throws IOException, JSONException {
        String jsonStr = loadJSONFromAssets();
        contactList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_view);

        if (jsonStr!=null) {
            jsonObj = new JSONObject(jsonStr);

            // Getting JSON Array node
            contacts = jsonObj.getJSONArray("hospitals");
        }

    }


    public void  display() throws JSONException {

        contactList.clear();

        // looping through All Contacts
        for (int i = 0; i < contacts.length(); i++) {
            JSONObject c = contacts.getJSONObject(i);

            String name = c.getString("name");
            String surname = c.getString("surname");
            String spec = c.getString("specialty");
            String phone = c.getString("phone");

            // tmp hash map for single contact
            HashMap<String, String> contact = new HashMap<>();

            if (spec.contains(specialty)) {
                // adding each child node to HashMap key => value
                contact.put("name", name + " " + surname);
                contact.put("specialty", spec);
                contact.put("phone", phone);

                // adding contact to contact list
                contactList.add(contact);
            } else if (specialty.equals("Όλα")){
                // adding each child node to HashMap key => value
                contact.put("name", name + " " + surname);
                contact.put("specialty", spec);
                contact.put("phone", phone);

                // adding contact to contact list
                contactList.add(contact);
            }
        }

        ListAdapter adapter = new SimpleAdapter(
                HospitalsActivity.this, contactList, R.layout.list_item,
                new String[]{"name", "specialty", "phone"}, new int[]{R.id.title, R.id.semi_title, R.id.bottom_title});

        listView.setAdapter(adapter);
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
