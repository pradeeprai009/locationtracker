package com.xcellity.callerlocation;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xcellity.callerlocation.Location.GPSLocation;
import com.xcellity.callerlocation.localDB.SQLData;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    TextView txtv_signUp;
    double latitude;
    double longitude;
    GPSLocation gpsTracker;
    SQLData db;
    String addressLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new SQLData(this);

        btn_login = findViewById(R.id.btn_login);
        txtv_signUp = findViewById(R.id.txtv_signUp);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Location_full",addressLine);
                startActivity(intent);
            }
        });

        txtv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
                intent.putExtra("Location_full",addressLine);
                startActivity(intent);
                finish();

            }
        });

        getLocation();

        if(TextUtils.isEmpty(addressLine)){
            Toast.makeText(gpsTracker, "Location is required", Toast.LENGTH_SHORT).show();
            recreate();
        }

    }
    public void getLocation() {
        gpsTracker = new GPSLocation(this);
        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();

            getAddress(this, latitude, longitude);

        } else {
            gpsTracker.showSettingsAlert();
        }
    }

    public String getAddress(Context ctx, double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                addressLine = address.getAddressLine(0);

                db.insertData(addressLine);


            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }
}