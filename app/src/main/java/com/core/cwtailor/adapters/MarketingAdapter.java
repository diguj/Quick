package com.core.cwtailor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.core.cwtailor.R;
import com.core.cwtailor.measurement.Select;
import com.core.cwtailor.measurement.Shirt;
import com.core.cwtailor.measurement.TakeMeasurements;
import com.core.cwtailor.model.MarketingModel;

import java.text.BreakIterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MarketingAdapter extends RecyclerView.Adapter<MarketingAdapter.MarketingViewHolder>{

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<MarketingModel> marketingList;

    //getting the context and product list with constructor
    public MarketingAdapter (Context mCtx, List<MarketingModel>marketingList){
        this.mCtx=mCtx;
        this.marketingList=marketingList;
    }
    @NonNull
    @Override
    public MarketingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view =inflater.inflate(R.layout.marketing_layout,null);
        return new MarketingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketingViewHolder holder, int position) {
        //getting the product of the specified position
        final MarketingModel marketing = marketingList.get(position);

        holder.srno.setText(marketing.getUser_id());
        holder.email.setText(marketing.getEmail_id());
        holder.date.setText(marketing.getOrder_date());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, Select.class);
                intent.putExtra("email",marketing.getEmail_id());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return marketingList.size();
    }

    public class MarketingViewHolder extends RecyclerView.ViewHolder{

        TextView srno, email, date;

        public MarketingViewHolder(@NonNull View itemView) {
            super(itemView);
            srno = itemView.findViewById(R.id.marketing_srno);
            email = itemView.findViewById(R.id.marketing_email);
            date =  itemView.findViewById(R.id.marketing_date);
        }
    }
}
