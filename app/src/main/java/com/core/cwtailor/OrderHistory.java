package com.core.cwtailor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.core.cwtailor.adapters.OrderHistoryAdapter;
import com.core.cwtailor.measurement.Select;
import com.core.cwtailor.model.OrderHistoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHistory extends AppCompatActivity {

    String text;
    List<OrderHistoryModel> orderHistoryModelList;

    RecyclerView recyclerView;

    private ProgressDialog progressDialog;
    private OrderHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        setTitle("Quick Stitch (Order History)");

        recyclerView = (RecyclerView) findViewById(R.id.rv_customer_order_history);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderHistoryModelList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        text = sharedPreferences.getString("uname", "");

         adapter = new OrderHistoryAdapter(OrderHistory.this, orderHistoryModelList);
        recyclerView.setAdapter(adapter);

        orderhistory();
    }

    private void orderhistory() {
        progressDialog.setMessage("Loading Date");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.ORDER_HISTORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.d(response, "");
                        try {
                            Log.e("JsonException", response);
                            JSONObject jsonObject = new JSONObject(response);

                            boolean success = jsonObject.getBoolean("success");

                            Log.d(response, "");
                            if (success) {
                                JSONArray jsonArray = jsonObject.getJSONArray("orderhistory");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    OrderHistoryModel historyModel = new OrderHistoryModel();
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    historyModel.setFullname(object.getString("fullname"));
                                    historyModel.setOrder_id(object.getString("order_id"));
                                    historyModel.setOrder_date(object.getString("order_date"));
                                    historyModel.setOrder_type(object.getString("order_type"));
                                    historyModel.setDelivery_date(object.getString("delivery_date"));
                                    historyModel.setEmail_id(object.getString("email_id"));
                                    orderHistoryModelList.add(historyModel);
                                }
                                adapter.refreshAdapter(orderHistoryModelList);
                              /*  adapter = new OrderHistoryAdapter(OrderHistory.this, orderHistoryModelList);
                                recyclerView.setAdapter(adapter);*/
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
                        Log.e("volley Error", error.toString());
                        Toast.makeText(getApplicationContext(), Constants.ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email_id", text);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
