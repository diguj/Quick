package com.core.cwtailor.stitching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.core.cwtailor.adapters.MarketingAdapter;
import com.core.cwtailor.adapters.ShopkeeperAdapter;
import com.core.cwtailor.measurement.Marketing;
import com.core.cwtailor.model.MarketingModel;
import com.core.cwtailor.model.ShopkeeperModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shopkeeper extends AppCompatActivity {

    List<ShopkeeperModel> shopkeeperList;

    RecyclerView recyclerView;

  private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper);

        setTitle("Quick Stitch (Shopkeeper)");

        //getting the recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.shopkeeper_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the list
        shopkeeperList= new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        shopkeeper();
    }

    private void shopkeeper(){
        progressDialog.setMessage("Loading Data");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.GET_SHOPKEEPER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                JSONArray jsonArray = jsonObject.getJSONArray("shopkeeper");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    ShopkeeperModel model = new ShopkeeperModel();
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    model.setUser_id(object.getString("user_id"));
                                    model.setEmail_id(object.getString("email_id"));
                                    model.setNeck(object.getString("neck"));
                                    model.setChest(object.getString("chest"));
                                    model.setWaist(object.getString("waist"));
                                    model.setHips(object.getString("hips"));
                                    model.setSleeves(object.getString("sleeves"));
                                    model.setSleeves_length(object.getString("sleeves_length"));
                                    model.setBicep(object.getString("bicep"));
                                    model.setCuff(object.getString("cuff"));
                                    model.setArmwhole(object.getString("armwhole"));
                                    model.setShoulder(object.getString("shoulder"));
                                    model.setShirt_length(object.getString("shirt_length"));


                                    shopkeeperList.add(model);
                                }
                                //creating recyclerview adapter
                                ShopkeeperAdapter adapter = new ShopkeeperAdapter(Shopkeeper.this,shopkeeperList);

                                //setting adapter to recyclerview
                                recyclerView.setAdapter(adapter);

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
