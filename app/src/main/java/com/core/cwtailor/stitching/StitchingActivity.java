package com.core.cwtailor.stitching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.core.cwtailor.R;

public class StitchingActivity extends AppCompatActivity {

    LinearLayout llDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stitching);

        setTitle("Quick Stitch (Shopkeeper)");

        llDetails = findViewById(R.id.llDetails);

        llDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StitchingActivity.this,StitchingDetailActivity.class));
            }
        });

    }
}
