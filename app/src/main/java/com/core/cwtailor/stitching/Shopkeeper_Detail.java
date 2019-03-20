package com.core.cwtailor.stitching;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.core.cwtailor.R;
import com.core.cwtailor.measurement.Shirt;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Shopkeeper_Detail extends AppCompatActivity {

    TextView user_email_shopkeeper, type, neck, chest, waist, sleeves, hip, bicep, cuff, shirtlength, armwhole, shoulder, sleeveslength;
    Spinner shopkeeper_status;
    Button btn_chng_status;
    String email;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper__detail);

        user_email_shopkeeper = (TextView)findViewById(R.id.user_email_shopkeer);
        type = (TextView)findViewById(R.id.type);
        neck = (TextView)findViewById(R.id.mens_shirt_neck_shopkeeper);
        chest = (TextView)findViewById(R.id.mens_shirt_chest__shopkeeper);
        waist = (TextView) findViewById(R.id.mens_shirt_waist_shopkeeper);
        sleeves = (TextView)findViewById(R.id.mens_shirt_sleeves_shopkeeper);
        hip = (TextView)findViewById(R.id.mens_shirt_hips_shopkeeper);
        bicep = (TextView)findViewById(R.id.mens_shirt_bicep_shopkeeper);
        cuff = (TextView)findViewById(R.id.mens_shirt_cuff_shopkeeper);
        shirtlength = (TextView)findViewById(R.id.mens_shirt_length_shopkeeper);
        armwhole = (TextView)findViewById(R.id.mens_shirt_armwhole_shopkeeper);
        shoulder = (TextView)findViewById(R.id.mens_shirt_shoulder_shopkeeper);
        sleeveslength = (TextView)findViewById(R.id.mens_shirt_sleeves_length_shopkeeper);

        //layouts to hide there visibility
        LinearLayout neck_layout = (LinearLayout) findViewById(R.id.layout_neck);
        LinearLayout chest_layout = (LinearLayout) findViewById(R.id.layout_chest);
        LinearLayout waist_layout = (LinearLayout) findViewById(R.id.layout_waist);
        LinearLayout sleeves_layout = (LinearLayout) findViewById(R.id.layout_sleeves);
        LinearLayout hip_layout = (LinearLayout) findViewById(R.id.layout_hip);
        LinearLayout bicep_layout = (LinearLayout) findViewById(R.id.layout_bicep);
        LinearLayout cuff_layout = (LinearLayout) findViewById(R.id.layout_cuff);
        LinearLayout shirtlength_layout = (LinearLayout) findViewById(R.id.layout_shirt_length);
        LinearLayout armwhole_layout = (LinearLayout) findViewById(R.id.layout_armwhole);
        LinearLayout shoulder_layout = (LinearLayout) findViewById(R.id.layout_shoulder);
        LinearLayout sleeveslength_layout = (LinearLayout) findViewById(R.id.layout_sleeves_length);

        shopkeeper_status = (Spinner) findViewById(R.id.status_shopkeeper);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(Shopkeeper_Detail.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status));
        // Specify the layout to use when the list of choices appears
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        shopkeeper_status.setAdapter(statusAdapter);

        btn_chng_status = (Button) findViewById(R.id.btn_change_status_shopkeeper);

        Intent intent  = getIntent();

        if(intent.hasExtra("email")){
            user_email_shopkeeper.setText(intent.getStringExtra("email"));
        }

        if(intent.getStringExtra("neck")!=null&&!intent.getStringExtra("neck").equals("0")){
             neck.setText(intent.getStringExtra("neck"));
        }
            else{
            neck_layout.setVisibility(View.GONE);
            }

        if(intent.getStringExtra("chest")!=null&&!intent.getStringExtra("chest").equals("0")){
            chest.setText(intent.getStringExtra("chest"));
        }
            else {
                chest_layout.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("waist")!=null&&!intent.getStringExtra("waist").equals("0")){
            waist.setText(intent.getStringExtra("waist"));
        }
        else{
            waist_layout.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("hips")!=null&&!intent.getStringExtra("hips").equals("0")){
            hip.setText(intent.getStringExtra("hips"));
        }
        else{
            hip_layout.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("sleeves")!=null&&!intent.getStringExtra("sleeves").equals("0")){
            sleeves.setText(intent.getStringExtra("sleeves"));
        }
        else{
            sleeves_layout.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("sleeves_length")!=null&&!intent.getStringExtra("sleeves_length").equals("0")){
            sleeveslength.setText(intent.getStringExtra("sleeves_length"));
        }
        else{
            sleeveslength_layout.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("bicep")!=null&&!intent.getStringExtra("bicep").equals("0")){
            bicep.setText(intent.getStringExtra("bicep"));
        }
        else{
            bicep_layout.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("cuff")!=null&&!intent.getStringExtra("cuff").equals("0")){
            cuff.setText(intent.getStringExtra("cuff"));
        }
        else{
            cuff_layout.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("armwhole")!=null&&!intent.getStringExtra("armwhole").equals("0")){
            armwhole.setText(intent.getStringExtra("armwhole"));
        }
        else{
            armwhole_layout.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("shoulder")!=null&&!intent.getStringExtra("shoulder").equals("0")){
            shoulder.setText(intent.getStringExtra("shoulder"));
        }
        else{
            shoulder_layout.setVisibility(View.GONE);
        }
        if(intent.getStringExtra("shirt_length")!=null&&!intent.getStringExtra("shirt_length").equals("0")){
            shirtlength.setText(intent.getStringExtra("shirt_length"));
        }
        else{
            shirtlength_layout.setVisibility(View.GONE);
        }


        progressDialog = new ProgressDialog(this);
        btn_chng_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus();
                v.setEnabled(false);
            }
        });
    }

    private void changeStatus(){
//        progressDialog.setMessage("Connecting to server ");
  //      progressDialog.show();

        StringRequest sringRequest = new StringRequest(Request.Method.POST,
                Constants.CHANGE_STATUS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String message = jsonObject.getString("message");

                            Toast.makeText(Shopkeeper_Detail.this, message, Toast.LENGTH_LONG).show();


                        } catch (JSONException e) {
                            Log.e("JsonException", e.toString());
                            Toast.makeText(getApplicationContext(), Constants.ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
  //              progressDialog.hide();
                Log.e("Volley Error",error.toString());
                Toast.makeText(getApplicationContext(),Constants.ERROR_MESSAGE,Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams()  {

                Map<String,String> params = new HashMap<>();

                params.put("status",shopkeeper_status.getSelectedItem().toString());
                params.put("email",user_email_shopkeeper.getText().toString());

                        return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sringRequest);
    }
}
