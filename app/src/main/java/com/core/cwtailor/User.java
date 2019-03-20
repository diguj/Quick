package com.core.cwtailor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class User extends AppCompatActivity implements View.OnClickListener{

    private EditText new_user_fullname, new_user_contact, new_user_email, new_user_password,new_user_address;
    private Button btn_submit_new_user;
    //private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        new_user_fullname = (EditText) findViewById(R.id.new_user_fullname);
        new_user_contact = (EditText) findViewById(R.id.new_user_contact);
        new_user_email = (EditText) findViewById(R.id.new_user_email);
        new_user_password = (EditText) findViewById(R.id.new_user_password);
        new_user_address= (EditText) findViewById(R.id.new_user_address);
        btn_submit_new_user = (Button) findViewById(R.id.btn_submit_new_user);
        //progressDialog = new ProgressDialog(this);
        btn_submit_new_user.setOnClickListener(this);

    }

    private  void registerUser(){

        final String fullname = new_user_fullname.getText().toString().trim();
        final String mobno = new_user_contact.getText().toString().trim();
        final String email = new_user_email.getText().toString().trim();
        final String password = new_user_password.getText().toString().trim();
        final String address = new_user_address.getText().toString().trim();

       // progressDialog.setMessage("Registering user...");
       // progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(User.this,LoginActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            Log.e("JsonException",e.toString());
                            Toast.makeText(getApplicationContext(),Constants.ERROR_MESSAGE,Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     //   progressDialog.hide();
                        Log.e("Volley Error",error.toString());
                        Toast.makeText(getApplicationContext(),Constants.ERROR_MESSAGE,Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("fullname", fullname);
                params.put("mobno", mobno);
                params.put("email", email);
                params.put("password", password);
                params.put("address", address);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private boolean validateUser(){

        final String fullname = new_user_fullname.getText().toString().trim();
        final String contact = new_user_contact.getText().toString().trim();
        final String email = new_user_email.getText().toString().trim();
        final String password = new_user_password.getText().toString().trim();
        final String address = new_user_address.getText().toString().trim();


        if(fullname.length()==0){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(contact.length()==0){
            Toast.makeText(this, "Please enter contact", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(email.length()==0){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length()==0){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(address.length()==0){
            Toast.makeText(this, "Please enter address", Toast.LENGTH_SHORT).show();
            return false;
        }

      //  progressDialog.setMessage("Validating user...");
      //  progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.CHECK_USER_EMAIL+"?email="+email,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            if(!jsonObject.getBoolean("success")){
                                registerUser();
                            }
                        } catch (JSONException e) {
                            Log.e("JsonException",e.toString());
                            Toast.makeText(getApplicationContext(),Constants.ERROR_MESSAGE,Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // progressDialog.hide();
                        Log.e("Volley Error",error.toString());
                        Toast.makeText(getApplicationContext(),Constants.ERROR_MESSAGE,Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        return false;
    }

    @Override
    public void onClick(View v) {


        if(v == btn_submit_new_user)
           validateUser();
           registerUser();
    }
}
