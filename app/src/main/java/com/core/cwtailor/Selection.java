package com.core.cwtailor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.core.cwtailor.adapters.SelectionAdapter;
import com.core.cwtailor.model.SelectionModel;

import java.util.ArrayList;
import java.util.List;

public class Selection extends AppCompatActivity {

    TextView email_id;
    private List<SelectionModel>optionlist;

    RecyclerView rv;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        email_id=(TextView)findViewById(R.id.txt_cust_email);
        Intent intent =getIntent();
        if(intent.hasExtra("email")){
            email_id.setText(intent.getStringExtra("email"));
        }
        //ARRAY LIST
        optionlist = new ArrayList<SelectionModel>();
        optionlist.add(new SelectionModel("Men's Shirt",R.drawable.ic_logo));
        optionlist.add(new SelectionModel("Men's Pant",R.drawable.ic_logo));
        optionlist.add(new SelectionModel("Men's Kurta",R.drawable.ic_logo));
        optionlist.add(new SelectionModel("Men's Suit",R.drawable.ic_logo));
        optionlist.add(new SelectionModel("Women's Kurti",R.drawable.ic_logo));
        optionlist.add(new SelectionModel(" Chudidar",R.drawable.ic_logo));
        optionlist.add(new SelectionModel("Blouse",R.drawable.ic_logo));
        optionlist.add(new SelectionModel("Lehenga",R.drawable.ic_logo));
        //getting the recyclerview from xml
        rv = (RecyclerView) findViewById(R.id.selection_rv);
//        main_recyclerView.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(new SelectionAdapter(this,optionlist));




    }
}
