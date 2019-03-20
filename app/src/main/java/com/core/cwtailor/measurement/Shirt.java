package com.core.cwtailor.measurement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.core.cwtailor.User;
import com.core.cwtailor.stitching.Shopkeeper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Shirt extends AppCompatActivity {
    TextView email_id;
    String text;
    private EditText Neck, Chest, Waist, Hips, Sleeves, Sleeves_Length, Bicep, Cuff, Shirt_Length, Arm_Whole, Shoulder;
    private Button send;
    Spinner sSpinner;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt);

        sSpinner = (Spinner) findViewById(R.id.status_marketing);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(Shirt.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status));
        // Specify the layout to use when the list of choices appears
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sSpinner.setAdapter(statusAdapter);


        email_id = (TextView) findViewById(R.id.txt);
        Neck = (EditText) findViewById(R.id.mens_shirt_neck);
        Chest = (EditText) findViewById(R.id.mens_shirt_chest);
        Waist = (EditText) findViewById(R.id.mens_shirt_waist);
        Hips = (EditText) findViewById(R.id.mens_shirt_hips);
        Sleeves = (EditText) findViewById(R.id.mens_shirt_sleeves);
        Sleeves_Length = (EditText) findViewById(R.id.mens_shirt_sleeves_length);
        Bicep = (EditText) findViewById(R.id.mens_shirt_bicep);
        Cuff = (EditText) findViewById(R.id.mens_shirt_cuff);
        Shirt_Length = (EditText) findViewById(R.id.mens_shirt_length);
        Arm_Whole = (EditText) findViewById(R.id.mens_shirt_armwhole);
        Shoulder = (EditText) findViewById(R.id.mens_shirt_shoulder);
        Intent intent = getIntent();

        text = intent.getStringExtra(Select.EXTRA_TEXT);
        email_id.setText(text);

        send=(Button)findViewById(R.id.btn_mens_shirt);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item = sSpinner.getSelectedItem().toString();
                Toast.makeText(sSpinner.getContext(),item,Toast.LENGTH_SHORT).show();

                if(v==send)
                    sendData();
                v.setEnabled(false);
            }
        });{

        }

    }

    private void sendData(){
        //progressDialog.setMessage("Connecting to server ");
        //progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.MENS_SHIRT_MSM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String message=jsonObject.getString("message");

                            Toast.makeText(Shirt.this, message, Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Shirt.this, Marketing.class);
                            startActivity(intent);

                            //sendMessage();

                        } catch (Exception e) {
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
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("email_id",text);

                params.put("neck", Neck.getText().toString());
                params.put("chest", Chest.getText().toString());
                params.put("waist", Waist.getText().toString());
                params.put("hips", Hips.getText().toString());
                params.put("sleeves", Sleeves.getText().toString());
                params.put("sleeves_length", Sleeves_Length.getText().toString());
                params.put("bicep", Bicep.getText().toString());
                params.put("cuff", Cuff.getText().toString());
                params.put("shirt_length", Shirt_Length.getText().toString());
                params.put("armwhole", Arm_Whole.getText().toString());
                params.put("shoulder", Shoulder.getText().toString());
                params.put("status",sSpinner.getSelectedItem().toString());

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Marketing.class);
        startActivity(intent);
    }
}









