package com.healthtrackerinc.healthtracker;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class DoctorsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public String location = "Όλα";
    public String specialty = "Όλα";
    public List<String[]> list = new ArrayList<String[]>();
    public String jsonFileName = "doctors.json";
    private ListView listView;
    ArrayList<HashMap<String, String>> contactList;
    public JSONObject jsonObj;
    public JSONArray contacts;
    public ListAdapter adapter;

    // gamietai o nhras
    // andrea se agapo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        listView = (ListView) findViewById(R.id.list_view);


        //JSON Data
        try {
            getJSONData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        /** Spinner Specialty **/

        // Spinner element
        Spinner spinnerSp = (Spinner) findViewById(R.id.specialty_spinner);

        // Spinner click listener
        spinnerSp.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categoriesSp = new ArrayList<String>();
        categoriesSp.add("Όλα");
        categoriesSp.add("Παθολόγος");
        categoriesSp.add("Νευρολόγος");
        categoriesSp.add("Παιδίατρος");
        categoriesSp.add("Δερματολόγος");
        categoriesSp.add("Χειρούργος");
        categoriesSp.add("Οφθαλμίατρος");

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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                HashMap<String, String> hashMap;
                hashMap = contactList.get(position);
                DialogFragment dialogFragment = new DisplayInfoDialogFragment();

                //pass arguments to Dialog using bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("hashMap", hashMap);
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getFragmentManager(),"info");
            }
        });
    }



    // Generate the Action Bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (Account.getInstance().getAdminValue()==1)
            getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String s = item.getTitle().toString();
        Toast.makeText(getApplicationContext(), "pressed "+s, Toast.LENGTH_SHORT ).show();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

        if (adapterView.getId()==R.id.specialty_spinner){
            specialty = adapterView.getItemAtPosition(pos).toString();
        }
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









    public void  display() throws JSONException {

        contactList.clear();

        // looping through All Contacts
        for (int i = 0; i < contacts.length(); i++) {
            JSONObject c = contacts.getJSONObject(i);

            String name = c.getString("name");
            String surname = c.getString("surname");
            String spec = c.getString("specialty");
            String loc = c.getString("location");
            String phone = c.getString("phone");

            // tmp hash map for single contact
            HashMap<String, String> contact = new HashMap<>();

            if (spec.contains(specialty) && loc.contains(location)) {
                // adding each child node to HashMap key => value
                contact.put("name", name);
                contact.put("surname", surname);
                contact.put("specialty", spec);
                contact.put("phone", phone);
                contact.put("location", loc);
                // adding contact to contact list
                contactList.add(contact);

            } else if (specialty.equals("Όλα") && loc.contains(location)){
                // adding each child node to HashMap key => value
                contact.put("name", name + " " + surname);
                contact.put("specialty", spec);
                contact.put("phone", phone);
                contact.put("location", loc);
                // adding contact to contact list
                contactList.add(contact);

            } else if ( spec.contains(specialty) && location.equals("Όλα")){
                // adding each child node to HashMap key => value
                contact.put("name", name + " " + surname);
                contact.put("specialty", spec);
                contact.put("phone", phone);
                contact.put("location", loc);
                // adding contact to contact list
                contactList.add(contact);

            } else if (specialty.equals("Όλα") && location.equals("Όλα")){
                // adding each child node to HashMap key => value
                contact.put("name", name + " " + surname);
                contact.put("specialty", spec);
                contact.put("phone", phone);
                contact.put("location", loc);
                // adding contact to contact list
                contactList.add(contact);
            }
        }

        adapter = new SimpleAdapter(
                DoctorsActivity.this, contactList, R.layout.list_item,
                new String[]{"name", "specialty", "location"}, new int[]{R.id.title, R.id.semi_title, R.id.bottom_title});

        listView.setAdapter(adapter);
    }


    /** Functions for JSON Handle **/

    public void getJSONData() throws IOException, JSONException {
        String jsonStr = loadJSONFromAssets();
        contactList = new ArrayList<>();

        if (jsonStr!=null) {
            jsonObj = new JSONObject(jsonStr);

            // Getting JSON Array node
            contacts = jsonObj.getJSONArray("doctors");
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


    /*
        // CSV Data read // Anothetr method instead of using JSON Files
        String next[] = {};
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("doctors.csv"),"UTF-8"));
            for(;;) {
                next = reader.readNext();
                if(next != null) {
                    list.add(next);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        rowsNum = list.size();
        colNum = list.get(0).length;
        //Toast.makeText(getApplicationContext(), list.get(6)[0], Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), ""+rowsNum+" "+colNum, Toast.LENGTH_SHORT).show();


            /*
    //csv method
    public void displayResults(){
        listView = (ListView) findViewById(R.id.list_view);

        // tmp hash map for single contact
        HashMap<String, String> contact = new HashMap<>();
        contactList = new ArrayList<>();

        // adding each child node to HashMap key => value
        for (int i=0; i<rowsNum; i++){
            if (specialty.equals(list.get(i)[4].toString())){
                contact.put("name", list.get(i)[2] + " " + list.get(i)[3]);
            }
        }
        // adding contact to contact list
        contactList.add(contact);

        ListAdapter adapter = new SimpleAdapter(
                DoctorsActivity.this, contactList,
                R.layout.list_item, new String[]{"name"}, new int[]{R.id.name});

        listView.setAdapter(adapter);
    }


*/


}
