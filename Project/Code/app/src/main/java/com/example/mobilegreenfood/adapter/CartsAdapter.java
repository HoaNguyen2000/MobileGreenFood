package com.example.mobilegreenfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobilegreenfood.R;
import com.example.mobilegreenfood.model.Carts;
import com.example.mobilegreenfood.model.Category;

import java.util.List;

public class CartsAdapter extends  RecyclerView.Adapter<CartsAdapter.CartsViewHolder>{
    public static Context context;
    private List<Carts> cartsList;

    public CartsAdapter(Context context, List<Carts> cartsList) {
        this.context = context;
        this.cartsList = cartsList;
    }

    @NonNull
    @Override
    public CartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartsAdapter.CartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartsViewHolder holder, int position) {
        Carts item = cartsList.get(position);
        holder.cartProductName.setText(item.getProduct_name());
        holder.tvCartQuantity.setText(item.getQuantity());
        Glide.with(context).load(item.getProduct_image()).into(holder.imgCartItem);
    }

    @Override
    public int getItemCount() {
        return cartsList.size();
    }

    public static class CartsViewHolder extends RecyclerView.ViewHolder{
        TextView cartProductName, tvCartQuantity;
        ImageView imgCartItem;

        public CartsViewHolder(@NonNull View itemView) {
            super(itemView);
            cartProductName = itemView.findViewById(R.id.cartProductName);
            imgCartItem = itemView.findViewById(R.id.imgCartItem);
            tvCartQuantity = itemView.findViewById(R.id.tvCartQuantity);
        }
    }
}
