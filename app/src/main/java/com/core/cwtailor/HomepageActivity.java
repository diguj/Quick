package com.core.cwtailor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.core.cwtailor.measurement.MeasurementDetailActivity;

public class HomepageActivity extends AppCompatActivity {

    CardView card1;
    Button btsaveadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        card1 = findViewById(R.id.card1);
        btsaveadd = findViewById(R.id.btsaveadd);
        btsaveadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageActivity.this,MeasurementDetailActivity.class));
            }
        });
    }
}
