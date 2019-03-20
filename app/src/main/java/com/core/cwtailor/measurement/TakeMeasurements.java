package com.core.cwtailor.measurement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.core.cwtailor.Constants;
import com.core.cwtailor.LoginActivity;
import com.core.cwtailor.MainActivity;
import com.core.cwtailor.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TakeMeasurements extends AppCompatActivity{

    TextView email_id;
    Button change_status;
    String  email, status;
    Spinner sSpinner;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_take__measurements);

        sSpinner = (Spinner) findViewById(R.id.status_marketing);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(TakeMeasurements.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status));
        // Specify the layout to use when the list of choices appears
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sSpinner.setAdapter(statusAdapter);

        email_id=(TextView)findViewById(R.id.txt_email_id);

        Intent intent =getIntent();

        if(intent.hasExtra("email")){
            email_id.setText(intent.getStringExtra("email"));
        }


        change_status = (Button) findViewById(R.id.btn_change_status);

        change_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = sSpinner.getSelectedItem().toString();
                Toast.makeText(sSpinner.getContext(),item,Toast.LENGTH_SHORT).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.GET_MEASUREMENTS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                progressDialog.dismiss();

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(TakeMeasurements.this,Marketing.class);
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
//                                progressDialog.hide();
                                Log.e("Volley Error",error.toString());
                                Toast.makeText(getApplicationContext(),Constants.ERROR_MESSAGE,Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("status",sSpinner.getSelectedItem().toString());


                        return params;
                    }
                };
                RequestQueue requestQueue = (RequestQueue) Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });

    }
}
