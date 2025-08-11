package com.example.sarahkhan.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {
    Button btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Initialize views
        btnLogin = (Button) findViewById(R.id.btn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        // Login button click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });

        // Sign Up button click
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity2.class));
            }
        });
    }
}