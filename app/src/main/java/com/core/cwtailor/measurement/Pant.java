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
import com.core.cwtailor.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Pant extends AppCompatActivity {

    TextView email_id;
    String text;
    private EditText Waist, Abdomen, Hips, Thigh, Knee, Calf, Ankle, Comment;
    private Button send;
    Spinner sSpinner;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pant);

        sSpinner = (Spinner) findViewById(R.id.status_marketing);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(Pant.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status));
        // Specify the layout to use when the list of choices appears
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sSpinner.setAdapter(statusAdapter);

        email_id = (TextView) findViewById(R.id.txt);
        Waist = (EditText) findViewById(R.id.mens_pant_waist);
        Abdomen = (EditText) findViewById(R.id.mens_pant_abdomen);
        Hips = (EditText) findViewById(R.id.mens_pant_hips);
        Thigh = (EditText) findViewById(R.id.mens_pant_thigh);
        Knee = (EditText) findViewById(R.id.mens_pant_knee);
        Calf = (EditText) findViewById(R.id.mens_pant_calf);
        Ankle = (EditText) findViewById(R.id.mens_pant_ankle);
        Comment = (EditText) findViewById(R.id.mens_pant_comment);

        Intent intent = getIntent();
        text = intent.getStringExtra(Select.EXTRA_TEXT);
        email_id.setText(text);

        send=(Button)findViewById(R.id.btn_mens_pant);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = sSpinner.getSelectedItem().toString();
                Toast.makeText(sSpinner.getContext(),item,Toast.LENGTH_SHORT).show();

                if(v==send)
                    sendData();
                v.setEnabled(false);
            }
        });
    }

    private void sendData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.MENS_PANT_MSM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String message=jsonObject.getString("message");

                            Toast.makeText(Pant.this, message, Toast.LENGTH_LONG).show();

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
                params.put("waist", Waist.getText().toString());
                params.put("abdomen", Abdomen.getText().toString());
                params.put("hips", Hips.getText().toString());
                params.put("thigh", Thigh.getText().toString());
                params.put("knee", Knee.getText().toString());
                params.put("calf", Calf.getText().toString());
                params.put("ankle", Ankle.getText().toString());
                params.put("comment", Comment.getText().toString());

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