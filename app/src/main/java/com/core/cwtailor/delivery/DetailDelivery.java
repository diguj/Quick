package com.core.cwtailor.delivery;

import androidx.appcompat.app.AppCompatActivity;
import fr.ganfra.materialspinner.MaterialSpinner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.core.cwtailor.stitching.Shopkeeper_Detail;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailDelivery extends AppCompatActivity {

    TextView email_delivery, type,name, mobile, address, order_id, srno;
    Spinner delivery_status;
    Button btn_chng_status_delivery;
    String email;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_delivery);

        email_delivery=(TextView)findViewById(R.id.user_email_delivery);
       // type=(TextView)findViewById(R.id.type_delivery);
        name=(TextView)findViewById(R.id.delivery_fullname);
        mobile=(TextView)findViewById(R.id.delivery_mobile);
        address=(TextView)findViewById(R.id.delivery_address);
        order_id=(TextView)findViewById(R.id.delivery_order_id);
        srno=(TextView)findViewById(R.id.delivery_cust_srno);

        LinearLayout main_layout  = (LinearLayout) findViewById(R.id.Layout_type_delivery);
        LinearLayout fullname  = (LinearLayout) findViewById(R.id.layout_fullname_delivery);
        LinearLayout mobno = (LinearLayout) findViewById(R.id.layout_mobile_delivery);
        LinearLayout addressL = (LinearLayout) findViewById(R.id.layout_address_delivery);
        LinearLayout orderid  = (LinearLayout) findViewById(R.id.layout_order_id_delivery);
        LinearLayout email = (LinearLayout) findViewById(R.id.layout_user_email_delivery);

        delivery_status = (Spinner) findViewById(R.id.status_delivery);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(DetailDelivery.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status));
        // Specify the layout to use when the list of choices appears
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        delivery_status.setAdapter(statusAdapter);

        btn_chng_status_delivery = (Button) findViewById(R.id.btn_change_status_delivery);

        Intent intent  = getIntent();

        if(intent.hasExtra("email")){
            email_delivery.setText(intent.getStringExtra("email"));
        }

        if(intent.getStringExtra("email")!=null&&!intent.getStringExtra("email").equals("0")){
            email_delivery.setText(intent.getStringExtra("email"));
        }
        else{
            email.setVisibility(View.GONE);
        }
        if(intent.getStringExtra("name")!=null&&!intent.getStringExtra("name").equals("0")){
            name.setText(intent.getStringExtra("name"));
        }
        else{
            fullname.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("mobile")!=null&&!intent.getStringExtra("mobile").equals("0")){
            mobile.setText(intent.getStringExtra("mobile"));
        }
        else {
            mobno.setVisibility(View.GONE);
        }

        if(intent.getStringExtra("address")!=null&&!intent.getStringExtra("address").equals("0")){
            address.setText(intent.getStringExtra("address"));
        }
        else {
            addressL.setVisibility(View.GONE);
        }
        if(intent.getStringExtra("order_id")!=null&&!intent.getStringExtra("order_id").equals("0")){
            order_id.setText(intent.getStringExtra("order_id"));
        }
        else {
            orderid.setVisibility(View.GONE);
        }

        progressDialog = new ProgressDialog(this);
        btn_chng_status_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus();
                v.setEnabled(false);
            }
        });


    }private void changeStatus(){
//        progressDialog.setMessage("Connecting to server ");
        //      progressDialog.show();

        StringRequest sringRequest = new StringRequest(Request.Method.POST,
                Constants.CHANGE_STATUS_DELIVERY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String message = jsonObject.getString("message");

                            Toast.makeText(DetailDelivery.this, message, Toast.LENGTH_LONG).show();


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

                params.put("status",delivery_status.getSelectedItem().toString());
                params.put("email",email_delivery.getText().toString());
                params.put("order_id",order_id.getText().toString());

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sringRequest);
    }
}

