/*
        File        : ChooseCityActivity.java
        Program     : A1
        Programmer  : Lucas Roes
        Created     : February 2019
*/

package com.example.a_1;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class ChooseCityActivity extends AppCompatActivity {

    String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
    }

    // method to navigate to the forecast activity
    public void GoToWeather(View v) {
        // get the id of the button pressed, store it in a string
        Button button = findViewById(v.getId());
        city = button.getText().toString();
        // go to the forecast activity
        Intent intent = new Intent(this, Forecast.class);
        intent.putExtra("CityName", city);
        startActivity(intent);
    }

    public void GoToMain(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
