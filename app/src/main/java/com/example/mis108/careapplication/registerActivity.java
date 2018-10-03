package com.example.mis108.careapplication;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.mis108.careapplication.LoginActivity;
import com.example.mis108.careapplication.R;


import org.json.JSONException;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registerActivity extends AppCompatActivity {
    private EditText Account ,Password, Name, Phone,Email;
    private Button registerButton;
    private static String URL_REGISTER ="http://140.117.71.74/graduation/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Account = findViewById(R.id.registerAccount);
        Password = findViewById(R.id.registerPassword);
        Name = findViewById(R.id.registerName);
        Phone = findViewById(R.id.registerPhone);
        Email = findViewById(R.id.registerEmail);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Register();


            }
        });

    }
    private  void Register(){
        final String Account =this.Account.getText().toString().trim();
        final String Password =this.Password.getText().toString().trim();
        final String Name =this.Name.getText().toString().trim();
        final String Phone =this.Phone.getText().toString().trim();
        final String Email =this.Email.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            Log.d("message", String.valueOf(jsonObject));

                            if (success.equals("1")) {
                                Toast.makeText(registerActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                Intent it =new Intent();
                                it.setClass(registerActivity.this,LoginActivity.class);
                                startActivity(it);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(registerActivity.this, "Register error!" + e.toString(), Toast.LENGTH_SHORT).show();


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(registerActivity.this, "Register error!"+ error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {

                Map<String,String> params =new HashMap<>();
                params.put("account",Account);
                params.put("password",Password);
                params.put("name",Name);
                params.put("phone",Phone);
                params.put("email",Email);
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

