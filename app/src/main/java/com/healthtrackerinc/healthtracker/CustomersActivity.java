package com.healthtrackerinc.healthtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CustomersActivity extends AppCompatActivity {

    public List<String[]> list = new ArrayList<String[]>();
    public String jsonFileName = "customers.json";
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
        setContentView(R.layout.activity_customers);

        listView = (ListView) findViewById(R.id.list_view);


        //JSON Data
        try {
            getJSONData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }




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
        String select = item.getTitle().toString();
        if (select.equals("Add")){
            addFunction();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addFunction() {
        Toast.makeText(getApplicationContext(), "Add", Toast.LENGTH_SHORT).show();
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


        }

        adapter = new SimpleAdapter(
                CustomersActivity.this, contactList, R.layout.list_item,
                new String[]{"name", "surname", "IID"}, new int[]{R.id.title, R.id.semi_title, R.id.bottom_title});

        listView.setAdapter(adapter);
    }


    /** Functions for JSON Handle **/

    public void getJSONData() throws IOException, JSONException {
        String jsonStr = loadJSONFromAssets();
        contactList = new ArrayList<>();

        if (jsonStr!=null) {
            jsonObj = new JSONObject(jsonStr);

            // Getting JSON Array node
            contacts = jsonObj.getJSONArray("customers");
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
