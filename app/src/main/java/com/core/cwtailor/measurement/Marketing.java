package com.core.cwtailor.measurement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.core.cwtailor.R;
import com.core.cwtailor.adapters.MarketingAdapter;
import com.core.cwtailor.model.MarketingModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Marketing extends AppCompatActivity {

    //a list to store all the products
    List<MarketingModel> marketingList;

    //the recyclerview
    RecyclerView recyclerView;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing);

        setTitle("Quick Stitch (Marketing)");

        //getting the recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.marketing_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


 /*       RecyclerView myRecycler = (RecyclerView) findViewById(R.id.marketing_list);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        myRecycler.setAdapter(new SampleRecycler());*/

        //initializing the list
        marketingList= new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        marketing();

    }
    private void marketing(){
        progressDialog.setMessage("Loading Data");
        progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET,
                            Constants.GET_MARKETING,
                            new Response.Listener<String>() {
                                @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                JSONArray jsonArray = jsonObject.getJSONArray("marketing");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    MarketingModel model = new MarketingModel();
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    model.setUser_id(object.getString("user_id"));
                                    model.setEmail_id(object.getString("email_id"));
                                    model.setOrder_date(object.getString("order_date"));

                                    marketingList.add(model);
                                }
                                //creating recyclerview adapter
                                MarketingAdapter adapter = new MarketingAdapter(Marketing.this, marketingList);

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
                        progressDialog.hide();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);    }
}
