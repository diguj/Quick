package com.core.cwtailor.delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.core.cwtailor.Constants;
import com.core.cwtailor.LoginActivity;
import com.core.cwtailor.R;
import com.core.cwtailor.adapters.DeliveryAdapter;
import com.core.cwtailor.model.DeliveryModel;
import com.core.cwtailor.model.MarketingModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryActivity extends AppCompatActivity {

    //a list to store all the products
    List<DeliveryModel> DeliveryList;

    //the recyclerview
    RecyclerView rv;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        setTitle("Quick Stitch (Delivery)");

        //getting the recyclerview
        rv=(RecyclerView)findViewById(R.id.rv_delivery);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));


        //initializing the list
        DeliveryList= new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        delivery();
    }

    private void delivery(){
        progressDialog.setMessage("Loading Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.GET_DELIVERY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                JSONArray jsonArray = jsonObject.getJSONArray("shopkeeper");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    DeliveryModel model = new DeliveryModel();
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    model.setUser_id(object.getString("user_id"));
                                    model.setEmail_id(object.getString("email_id"));
                                    model.setOrder_id(object.getString("order_id"));
                                    model.setFullname(object.getString("fullname"));
                                    model.setMobno(object.getString("mobno"));
                                    model.setAddress(object.getString("address"));

                                    DeliveryList.add(model);
                                }
                                //creating recyclerview adapter
                                DeliveryAdapter adapter = new DeliveryAdapter(DeliveryActivity.this,DeliveryList);

                                //setting adapter to recyclerview
                                rv.setAdapter(adapter);

                            }

                        } catch (Exception e) {
                            Log.e("JsonException", e.toString());
                            Toast.makeText(getApplicationContext(), Constants.ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.hide();
                        Log.e("Volley Error",error.toString());
                        Toast.makeText(getApplicationContext(),Constants.ERROR_MESSAGE,Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);    }
}
