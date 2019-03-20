package com.core.cwtailor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.core.cwtailor.R;
import com.core.cwtailor.measurement.Select;
import com.core.cwtailor.model.MarketingModel;
import com.core.cwtailor.model.ShopkeeperModel;
import com.core.cwtailor.stitching.Shopkeeper;
import com.core.cwtailor.stitching.Shopkeeper_Detail;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopkeeperAdapter extends RecyclerView.Adapter<ShopkeeperAdapter.ShopkeeperViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ShopkeeperModel> shopkeeperlist;

    //getting the context and product list with constructor
    public ShopkeeperAdapter (Context mCtx, List<ShopkeeperModel>shopkeeperlist){
        this.mCtx=mCtx;
        this.shopkeeperlist=shopkeeperlist;
    }

    @NonNull
    @Override
    public ShopkeeperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view =inflater.inflate(R.layout.shopkeeper_view,null);
        return new ShopkeeperAdapter.ShopkeeperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopkeeperViewHolder holder, int position) {

        //getting the product of the specified position
        final ShopkeeperModel shopkeeper= shopkeeperlist.get(position);

        holder.srno.setText(shopkeeper.getUser_id());
        holder.email.setText(shopkeeper.getEmail_id());
        //holder.neck.setText(shopkeeper.getNeck());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, Shopkeeper_Detail.class);
                intent.putExtra("email",shopkeeper.getEmail_id());
                intent.putExtra("neck",shopkeeper.getNeck());
                intent.putExtra("chest",shopkeeper.getChest());
                intent.putExtra("waist",shopkeeper.getWaist());
                intent.putExtra("hips",shopkeeper.getHips());
                intent.putExtra("sleeves",shopkeeper.getSleeves());
                intent.putExtra("sleeves_length",shopkeeper.getSleeves_length());
                intent.putExtra("bicep",shopkeeper.getBicep());
                intent.putExtra("cuff",shopkeeper.getCuff());
                intent.putExtra("armwhole",shopkeeper.getArmwhole());
                intent.putExtra("shoulder",shopkeeper.getShoulder());
                intent.putExtra("shirt_length",shopkeeper.getShirt_length());

                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopkeeperlist.size();
    }

    //getting the context and product list with constructor
     class ShopkeeperViewHolder extends RecyclerView.ViewHolder{
        TextView srno, email;

        public ShopkeeperViewHolder(@NonNull View itemView) {
            super(itemView);
            srno = itemView.findViewById(R.id.shopkeeper_srno);
            email = itemView.findViewById(R.id.shopkeeper_email);
       //     neck = itemView.findViewById(R.id.shopkeeper_srno);
            //measurements
        }
    }

    }

