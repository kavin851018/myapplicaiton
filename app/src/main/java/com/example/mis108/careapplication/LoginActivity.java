package com.example.mis108.careapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button registerLink = (Button) findViewById(R.id.LoginRegisterButton);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,registerActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        final Button forgetPasswordLink = (Button) findViewById(R.id.LoginForgotPasswordButton);
        forgetPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgetPasswordIntent = new Intent(LoginActivity.this,forgetPasswordActivity.class);
                LoginActivity.this.startActivity(forgetPasswordIntent);
            }
        });


    }
}
