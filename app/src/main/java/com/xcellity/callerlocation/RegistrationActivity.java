package com.xcellity.callerlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {
    TextView txtv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txtv_login=findViewById(R.id.txtv_login);

        txtv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}