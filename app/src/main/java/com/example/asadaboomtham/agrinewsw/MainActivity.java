package com.example.asadaboomtham.agrinewsw;
/**
 * Created by SHAJIB on 7/4/2017.
 */

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asadaboomtham.agrinewsw.Adapter.Function;
import com.example.asadaboomtham.agrinewsw.AllNewsActivity;
import com.example.asadaboomtham.agrinewsw.DailyNewsActivity;
import com.example.asadaboomtham.agrinewsw.R;
import com.example.asadaboomtham.agrinewsw.ThaiPbsActivity;
import com.example.asadaboomtham.agrinewsw.facebookActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private CircleImageView photoImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    private ProfileTracker profileTracker;

    //Declare layout
    private LinearLayout linearLayout_facebook;
    private LinearLayout linearLayout_AllNews;
    private LinearLayout linearLayout_Kaokaset;
    private LinearLayout linearLayout_Thaipbs;
    private LinearLayout linearLayout_Dailynews;
    private LinearLayout facebook_connect_layout;


    TextView customFontTextView;
    TextView customFontTextView2;
    TextView customFontTextView3;
    Typeface typeface;

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;

    Typeface weatherFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        photoImageView = (CircleImageView) findViewById(R.id.photoImageView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
//        emailTextView = (TextView) findViewById(R.id.emailTextView);
//            idTextView = (TextView) findViewById(R.id.idTextView);


























        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    displayProfileInfo(currentProfile);
                }
            }
        };

        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        } else {
            requestEmail(AccessToken.getCurrentAccessToken());

            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                displayProfileInfo(profile);
            } else {
                Profile.fetchProfileForCurrentAccessToken();
            }
        }


        typeface = Typeface.createFromAsset(getAssets(), "fonts/TH Baijam.ttf");

        customFontTextView = (TextView) findViewById(R.id.customFontTextView);

        customFontTextView.setTypeface(typeface);

//        customFontTextView2 = (TextView) findViewById(R.id.customFontTextView2);
//
//        customFontTextView2.setTypeface(typeface);

        customFontTextView3 = (TextView) findViewById(R.id.customFontTextView3);

        customFontTextView3.setTypeface(typeface);

        linearLayout_facebook = (LinearLayout) findViewById(R.id.linearLayout_facebook);
        linearLayout_facebook.setOnClickListener(OnClickListener2);
//
        facebook_connect_layout = (LinearLayout) findViewById(R.id.facebook_connect_layout);
        facebook_connect_layout.setOnClickListener(OnClickListener6);

        linearLayout_AllNews = (LinearLayout) findViewById(R.id.linearLayout_AllNews);
        linearLayout_AllNews.setOnClickListener(OnClickListener);

        linearLayout_Kaokaset = (LinearLayout) findViewById(R.id.linearLayout_Kaokaset);
        linearLayout_Kaokaset.setOnClickListener(OnClickListener3);

        linearLayout_Thaipbs = (LinearLayout) findViewById(R.id.linearLayout_Thaipbs);
        linearLayout_Thaipbs.setOnClickListener(OnClickListener4);

        linearLayout_Dailynews = (LinearLayout) findViewById(R.id.linearLayout_Dailynews);
        linearLayout_Dailynews.setOnClickListener(OnClickListener5);


        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView) findViewById(R.id.city_field);
        //updatedField = (TextView)findViewById(R.id.updated_field);
        //detailsField = (TextView) findViewById(R.id.details_field);
        currentTemperatureField = (TextView) findViewById(R.id.current_temperature_field);
        //humidity_field = (TextView)findViewById(R.id.humidity_field);
        //pressure_field = (TextView)findViewById(R.id.pressure_field);
        weatherIcon = (TextView) findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);


        Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                cityField.setText(weather_city);
                // updatedField.setText(weather_updatedOn);
                //detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                // humidity_field.setText("Humidity: " + weather_humidity);
                // pressure_field.setText("Pressure: " + weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });
        asyncTask.execute("13.2811658", "100.9262629"); //  asyncTask.execute("Latitude", "Longitude")
        getSupportActionBar().hide();


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
    private View.OnClickListener OnClickListener6 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), LogoutActivity
                    .class);
            startActivity(i);
        }
    };

    private void requestEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String email = object.getString("email");
                    setEmail(email);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }


    private void setEmail(String email) {
        emailTextView.setText(email);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    private void displayProfileInfo(Profile profile) {
        String id = profile.getId();
        String name = profile.getName();
        String photoUrl = profile.getProfilePictureUri(600, 480).toString();

        nameTextView.setText(name);
//            idTextView.setText(id);

        Glide.with(getApplicationContext())
                .load(photoUrl)
                .into(photoImageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        profileTracker.stopTracking();
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }
}


