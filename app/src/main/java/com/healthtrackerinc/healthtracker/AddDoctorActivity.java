package com.healthtrackerinc.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddDoctorActivity extends AppCompatActivity {

    EditText editText;
    Button okBtn, cancelBtn;
    public List<String[]> list = new ArrayList<String[]>();
    public String jsonFileName = "doctors.json";
    private ListView listView;
    ArrayList<HashMap<String, String>> contactList;
    public JSONObject jsonObj;
    public JSONArray contacts;
    public ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        editText = (EditText) findViewById(R.id.name);
        okBtn = (Button) findViewById(R.id.ok);


        //JSON Data
        try {
            getJSONData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        writeToJSON();
    }

    public void onClickOk(View view){



    }
    /** Functions for JSON Handling **/

    public void getJSONData() throws IOException, JSONException {
        String jsonStr = loadJSONFromAssets();
        contactList = new ArrayList<>();

        if (jsonStr!=null) {
            jsonObj = new JSONObject(jsonStr);

            // Getting JSON Array node
            contacts = jsonObj.getJSONArray("doctors");
        }
    }


    public void writeToJSON(){
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("username", "3");
            jsonObj.put("password", "NAME OF STUDENT");
            jsonObj.put("name", "aaaaaa");
            jsonObj.put("surname", "Arts");
            jsonObj.put("specialty", "5/5/1993");
            jsonObj.put("phone", "99999");
            jsonObj.put("location", "Cyprus");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        contacts.put(jsonObj);
    }

    public void deleteFromJSON() {
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
