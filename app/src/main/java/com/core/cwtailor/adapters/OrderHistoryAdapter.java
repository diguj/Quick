package com.core.cwtailor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.core.cwtailor.OrderHistory;
import com.core.cwtailor.R;
import com.core.cwtailor.model.OrderHistoryModel;
import com.core.cwtailor.stitching.Shopkeeper_Detail;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {
    private Context ctx;

    private List<OrderHistoryModel> orderHistoryModelList;

    public OrderHistoryAdapter(Context ctx, List<OrderHistoryModel> orderHistoryModelList) {

        this.ctx = ctx;
        this.orderHistoryModelList = orderHistoryModelList;
    }

    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.order_history_view, null);
        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        final OrderHistoryModel orderHistory = orderHistoryModelList.get(position);
        if (orderHistory != null && orderHistory.getEmail_id() != null && orderHistory.getFullname() != null && orderHistory.getOrder_id()!= null && orderHistory.getOrder_date() != null && orderHistory.getDelivery_date() != null && orderHistory.getOrder_type() != null) {
            holder.email_id.setText(orderHistory.getEmail_id());
            holder.fullname.setText(orderHistory.getFullname());
           // holder.order_id.setText(orderHistory.getOrder_id());
            holder.order_date.setText(orderHistory.getOrder_date());
            holder.order_type.setText(orderHistory.getOrder_type());
            holder.delivery_date.setText(orderHistory.getDelivery_date());

            Intent intent = new Intent(ctx, OrderHistory.class);
            intent.putExtra("email_id", orderHistory.getEmail_id());
            ctx.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return orderHistoryModelList.size();
    }

    public void refreshAdapter (List<OrderHistoryModel> orderHistoryModelList){

        this.orderHistoryModelList=orderHistoryModelList;
        notifyDataSetChanged();
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {

        TextView order_id, order_date, delivery_date, order_type, fullname, email_id, user_id;

        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            email_id = itemView.findViewById(R.id.email_address_delivery);
            fullname = itemView.findViewById(R.id.order_history_cust_name);
            order_id = itemView.findViewById(R.id.cust_order_id);
            order_date = itemView.findViewById(R.id.cust_order_date);
            order_type = itemView.findViewById(R.id.cust_order_type);
            delivery_date = itemView.findViewById(R.id.cust_order_delivery_date);


        }
    }
}
