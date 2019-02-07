/*
        File        : Forecast.java
        Program     : A1
        Programmer  : Lucas Roes
        Created     : February 2019
*/

package com.example.a_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.util.Log.d;

public class Forecast extends AppCompatActivity {

    String citydata;
    String temp = " Temperature Unknown";
    String condition = "Conditions Unknown";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        // get locations to put weather data
        final TextView Condition = findViewById(R.id.Condition);
        final TextView Temperature = findViewById(R.id.Temperature);
        final TextView City = findViewById(R.id.CityName);

        // find out what city was selected
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
        {
            citydata = extras.getString("CityName"); // retrieve the data using the keyName
        }

        // Use api to retrieve weather info
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // My API key, with the selected city appended to it.
        String url ="http://api.apixu.com/v1/current.json?key=628233ab0fcd472f9ed54636192401&q=" + citydata;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Log the first 500 characters of the response string.
                        d("debug", "Response is: " + response.substring(0,500));
                        String in = response;

                        //Parse the JSON data that I need
                        JSONObject reader = null;
                        try {
                            reader = new JSONObject(in);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JSONObject tmp  = null;
                        try {
                            tmp = reader.getJSONObject("current");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            temp = tmp.getString("temp_c");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JSONObject cond  = null;
                        try {
                            cond = reader.getJSONObject("current");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject txt = null;
                        try {
                            txt = cond.getJSONObject("condition");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            condition = txt.getString("text");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Put weather data onto the screen
                        Condition.setText(condition);
                        Temperature.setText(temp + " Celcius");
                        City.setText(citydata);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // log error
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    // method to navigate to the forecast activity
    public void GoToChooseCity(View view)
    {
        Intent myIntent = new Intent(this, ChooseCityActivity.class);
        startActivity(myIntent);
    }
}
