package com.core.cwtailor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.core.cwtailor.delivery.DeliveryActivity;
import com.core.cwtailor.delivery.DetailDelivery;
import com.core.cwtailor.measurement.Marketing;
import com.core.cwtailor.stitching.Shopkeeper;
import com.core.cwtailor.stitching.StitchingActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {



    public Button btn_login;
    public TextView new_user_registration;
    public EditText email,password;
    private ProgressDialog progressDialog;


    public void init(){

        new_user_registration =(TextView) findViewById(R.id.new_user_registration);
        new_user_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(LoginActivity.this, User.class);
                startActivity(i);
            }
        });


        email=(EditText)findViewById(R.id.txtEmail);
        password=(EditText)findViewById(R.id.txtPassword);

        btn_login=(Button)findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        checkLogin();
        getemail();
    }

    private void checkLogin(){
        SharedPreferences preferences=getSharedPreferences("user",Context.MODE_PRIVATE);

        if(preferences.contains("uname")){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }
    private String getemail(){
        SharedPreferences preferences=getSharedPreferences("user",Context.MODE_PRIVATE);
        return preferences.getString("uname",null);
    }

    private void login() {
        final String emailValue = email.getText().toString();
        final String passwordValue = password.getText().toString();


        progressDialog.setMessage("Logging In user...");
        progressDialog.show();
        if(!email.getText().toString().equalsIgnoreCase("") && email.getText().toString().equalsIgnoreCase("marketing") && password.getText().toString().equalsIgnoreCase("123")) {
            startActivity(new Intent(LoginActivity.this, Marketing.class));
        }else if(!email.getText().toString().equalsIgnoreCase("") && email.getText().toString().equalsIgnoreCase("shopkeeper") && password.getText().toString().equalsIgnoreCase("123")) {
            startActivity(new Intent(LoginActivity.this, Shopkeeper.class));
        }else if(!email.getText().toString().equalsIgnoreCase("") && email.getText().toString().equalsIgnoreCase("delivery") && password.getText().toString().equalsIgnoreCase("123")) {
            startActivity(new Intent(LoginActivity.this, DeliveryActivity.class));
        }else{

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                            if (success) {
                                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("uname", emailValue);
                                editor.commit();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            Log.e("JsonException", e.toString());
                            Toast.makeText(getApplicationContext(), Constants.ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Log.e("Volley Error", error.toString());
                        Toast.makeText(getApplicationContext(), Constants.ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", emailValue);
                params.put("password", passwordValue);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    }
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}


