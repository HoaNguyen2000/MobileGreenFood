package com.example.mobilegreenfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilegreenfood.R;
import com.example.mobilegreenfood.ViewListOrderActivity;
import com.example.mobilegreenfood.ViewOrderDetailActivity;
import com.example.mobilegreenfood.model.Order;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>{
    public Context context;
    private List<Order> orders;

    public OrdersAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new OrdersAdapter.OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.tvOrderId.setText(String.valueOf(order.getOrder_id()));
        holder.tvOrderAmount.setText("$"+ order.getAmount());
        String status = ViewListOrderActivity.ORDER_STATUS[order.getOrder_status() -2];
        holder.tvOrderStatus.setText(status);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewOrderDetailActivity.class);
                intent.putExtra("order", order);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder{
        TextView tvOrderId, tvOrderAmount, tvOrderStatus;
        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderAmount = itemView.findViewById(R.id.tvOrderAmount);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
        }
    }
}
