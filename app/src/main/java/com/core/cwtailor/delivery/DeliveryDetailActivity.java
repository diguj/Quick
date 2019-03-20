package com.core.cwtailor.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.core.cwtailor.R;

public class DeliveryDetailActivity extends AppCompatActivity {

    Button btsaveadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_detail);

        btsaveadd = findViewById(R.id.btsaveadd);
        btsaveadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DeliveryDetailActivity.this,"Product Delivered Successfully",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(DeliveryDetailActivity.this,DeliveryActivity.class));
            }
        });
    }
}
