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

public class ActiveSubstanceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    public List<String[]> list = new ArrayList<String[]>();
    public int rowsNum, colNum;
    public String jsonFileName = "substance.json";
    private ListView listView;
    ArrayList<HashMap<String, String>> contactList;
    public JSONObject jsonObj;
    public JSONArray contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_substance);


        //JSON Data
        try {
            getJSONData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

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
            contacts = jsonObj.getJSONArray("substance");
        }

    }


    public void  display() throws JSONException {

        contactList.clear();

        // looping through All Contacts
        for (int i = 0; i < contacts.length(); i++) {
            JSONObject c = contacts.getJSONObject(i);

            String name = c.getString("activeTitle");
            String loc = c.getString("drugTitle");


            // tmp hash map for single contact
            HashMap<String, String> contact = new HashMap<>();

            contact.put("title", name);
            contact.put("address", loc);

            contactList.add(contact);


        }

        ListAdapter adapter = new SimpleAdapter(
                ActiveSubstanceActivity.this, contactList, R.layout.list_item,
                new String[]{"activeTitle", "drugTitle"}, new int[]{R.id.title, R.id.semi_title});

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
