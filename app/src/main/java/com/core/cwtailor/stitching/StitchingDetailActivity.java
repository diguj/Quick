package com.core.cwtailor.stitching;

import androidx.appcompat.app.AppCompatActivity;
import fr.ganfra.materialspinner.MaterialSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.core.cwtailor.R;

import java.util.ArrayList;
import java.util.List;

public class StitchingDetailActivity extends AppCompatActivity {

    MaterialSpinner spinner;
    Button btsaveadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stitching_deatil);

        setTitle("Pending");

        List<String> gender = new ArrayList<String>();
        gender.add("Working");
        gender.add("Cancel");
        gender.add("Completed");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);

        btsaveadd = findViewById(R.id.btsaveadd);
        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        btsaveadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StitchingDetailActivity.this,"Status Changed",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(StitchingDetailActivity.this,StitchingActivity.class));
            }
        });
    }
}
