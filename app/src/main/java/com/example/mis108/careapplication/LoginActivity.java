package com.example.mis108.careapplication;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;

public class LoginActivity extends AppCompatActivity  {
    private EditText Account,Password;
    private Button loginButton,LoginForgotpasswordButton,LoginRegisterButton;
    private static String URL_LOGIN = "http://140.117.71.74/graduation/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Account=  findViewById(R.id.accountEditText);
        Password= findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.loginButton);
        LoginForgotpasswordButton = findViewById(R.id.LoginForgotPasswordButton);
        LoginRegisterButton = findViewById(R.id.LoginRegisterButton);
        LoginRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterButtonIntent = new Intent(LoginActivity.this,registerActivity.class);
                startActivity(RegisterButtonIntent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = Account.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if(!account.isEmpty()||!password.isEmpty()){
                    Login(account,password);


                }
                else{
                    Account.setError("please insert account");
                    Password.setError("please insert password");
                }
            }
        });
    }
    private void Login(final String account, final String password) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success =jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            Log.d("asd", String.valueOf(jsonObject));
                            if (success.equals("1")){
                                for (int i = 0 ; i <jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    Toast.makeText(LoginActivity.this,
                                            "success Login. \n your Name :"
                                                    + name+"\nyour email : "
                                                    +email,Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this,"error"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"error"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("account",account);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
