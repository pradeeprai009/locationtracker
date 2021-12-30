package com.xcellity.callerlocation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView txtv_locations;
    String Location_full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtv_locations = findViewById(R.id.txtv_locations);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Location_full = extras.getString("Location_full");
            txtv_locations.setText(Location_full);
        }

    }
}