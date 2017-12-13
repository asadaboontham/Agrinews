package com.example.asadaboomtham.agrinewsw;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.function.Function;

public class MainActivity extends AppCompatActivity {
//        implements BaseExampleFragment.BaseExampleFragmentCallbacks, NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "MainActivity";

    //Declare layout
    private LinearLayout linearLayout_facebook;
    private LinearLayout linearLayout_AllNews;
    private LinearLayout linearLayout_Kaokaset;
    private LinearLayout linearLayout_Thaipbs;
    private LinearLayout linearLayout_Dailynews;


    //Start Declare Weatehr method
    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;

    Typeface weatherFont;
    //End Declare Weatehr method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout_facebook = (LinearLayout) findViewById(R.id.linearLayout_facebook);
        linearLayout_facebook.setOnClickListener(OnClickListener2);

        linearLayout_AllNews = (LinearLayout) findViewById(R.id.linearLayout_AllNews);
        linearLayout_AllNews.setOnClickListener(OnClickListener);

        linearLayout_Kaokaset = (LinearLayout) findViewById(R.id.linearLayout_Kaokaset);
        linearLayout_Kaokaset.setOnClickListener(OnClickListener3);

        linearLayout_Thaipbs = (LinearLayout) findViewById(R.id.linearLayout_Thaipbs);
        linearLayout_Thaipbs.setOnClickListener(OnClickListener4);

        linearLayout_Dailynews = (LinearLayout) findViewById(R.id.linearLayout_Dailynews);
        linearLayout_Dailynews.setOnClickListener(OnClickListener5);


        // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // navigationView.setNavigationItemSelectedListener(this);
        //Weather
        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView) findViewById(R.id.city_field);
        //updatedField = (TextView)findViewById(R.id.updated_field);
        detailsField = (TextView) findViewById(R.id.details_field);
        currentTemperatureField = (TextView) findViewById(R.id.current_temperature_field);
//        humidity_field = (TextView)findViewById(R.id.humidity_field);
//        pressure_field = (TextView)findViewById(R.id.pressure_field);
        weatherIcon = (TextView) findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);


//        Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
//            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {
//
//                cityField.setText(weather_city);
//                updatedField.setText(weather_updatedOn);
//                detailsField.setText(weather_description);
//                currentTemperatureField.setText(weather_temperature);
//                humidity_field.setText("Humidity: "+weather_humidity);
//                pressure_field.setText("Pressure: "+weather_pressure);
//                weatherIcon.setText(Html.fromHtml(weather_iconText));
//
//            }
//        });
//        asyncTask.execute("25.180000", "89.530000"); //  asyncTask.execute("Latitude", "Longitude")



    }
    private View.OnClickListener OnClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), facebookActivity.class);
            startActivity(i);
        }
    };

    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), AllNewsActivity.class);
            startActivity(i);
        }
    };

    private View.OnClickListener OnClickListener3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), KaokasetActivity.class);
            startActivity(i);
        }
    };

    private View.OnClickListener OnClickListener4 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), ThaiPbsActivity.class);
            startActivity(i);
        }
    };

    private View.OnClickListener OnClickListener5 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), DailyNewsActivity
                    .class);
            startActivity(i);
        }
    };
}
