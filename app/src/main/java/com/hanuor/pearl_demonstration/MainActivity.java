package com.hanuor.pearl_demonstration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hanuor.pearl.Pearl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView img, back;
    ArrayList<String> arr;
    Button btn;
    //a sample url. We couldn't find one so
    String urlString = "http://pastebin.com/raw/wgkJgazE";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);

        back = (ImageView) findViewById(R.id.background);

        btn = (Button) findViewById(R.id.button);
        arr = new ArrayList<String>();

        //Initialized with key "mind"
        JsonArrayRequest req = new JsonArrayRequest(urlString,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("MAIN", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            //jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);
                                Pearl.saveJsonObject(MainActivity.this, "serializable",""+i+"tag");

                                String name = person.getString("id");
                                String email = person.getString("height");
                                Log.d("MAINE"," "+name+" email " + email);
                                JSONObject mass = person.getJSONObject("user");
                                JSONObject spehe = mass.getJSONObject("profile_image");

                                arr.add(spehe.getString("large"));

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        Volley.newRequestQueue(MainActivity.this).add(req);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextAct = new Intent(MainActivity.this, com.hanuor.pearl_demonstration.DisplayActivity.class);
                nextAct.putStringArrayListExtra("list",arr);
                startActivity(nextAct);
            }
        });
    }
}
