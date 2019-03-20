package com.core.cwtailor.measurement;

import androidx.appcompat.app.AppCompatActivity;
import fr.ganfra.materialspinner.MaterialSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.core.cwtailor.R;

import java.util.ArrayList;
import java.util.List;

public class MeasurementDetailActivity extends AppCompatActivity {

    MaterialSpinner spinner;
    Button btsaveadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Get Info");

        List<String> gender = new ArrayList<String>();
        gender.add("Male");
        gender.add("Female");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);

        btsaveadd = findViewById(R.id.btsaveadd);
        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        btsaveadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeasurementDetailActivity.this,TakeMeasurements.class));
            }
        });
    }
}
