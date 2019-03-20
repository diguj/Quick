package com.core.cwtailor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.core.cwtailor.R;
import com.core.cwtailor.delivery.DeliveryDetailActivity;
import com.core.cwtailor.delivery.DetailDelivery;
import com.core.cwtailor.model.DeliveryModel;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>{

     private Context mCtx;
    //we are storing all the products in a list
    private List<DeliveryModel> deliveryModelList;

     public DeliveryAdapter(Context mCtx, List<DeliveryModel>deliveryModelList ){
         this.mCtx=mCtx;
         this.deliveryModelList=deliveryModelList;

    }

    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view =inflater.inflate(R.layout.delivery_view,null);
        return new DeliveryAdapter.DeliveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder holder, int position) {

        //getting the product of the specified position
        final DeliveryModel delivery =deliveryModelList.get(position);

        holder.srno.setText(delivery.getUser_id());
        holder.email.setText(delivery.getEmail_id());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, DetailDelivery.class);

                intent.putExtra("order_id",delivery.getOrder_id());

                intent.putExtra("name",delivery.getFullname());
                intent.putExtra("email",delivery.getEmail_id());
                intent.putExtra("address",delivery.getAddress());
                intent.putExtra("mobile",delivery.getMobno());

                mCtx.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveryModelList.size();
    }

    public class DeliveryViewHolder extends RecyclerView.ViewHolder{
            TextView srno,email;
         public DeliveryViewHolder(@NonNull View itemView) {
             super(itemView);
             srno = itemView.findViewById(R.id.delivery_cust_srno);
             email = itemView.findViewById(R.id.delivery_cust_email);
         }
     }
}
