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
        holder.tvCartQuantity.setText(String.valueOf(item.getQuantity()));
        holder.cartProductPrice.setText("$"+ String.valueOf(item.getProduct_price()));
        Glide.with(context).load(item.getProduct_image()).into(holder.imgCartItem);
        holder.btnDownCartQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQty = Integer.parseInt(holder.tvCartQuantity.getText().toString());
                if(currentQty <= 1){}
                else{
                    int result = currentQty - 1;
                    holder.tvCartQuantity.setText(String.valueOf(result));
                }
            }
        });
        holder.btnUpCartQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQty = Integer.parseInt(holder.tvCartQuantity.getText().toString());
                if(currentQty >= 20){}
                else{
                    int result = currentQty + 1;
                    holder.tvCartQuantity.setText(String.valueOf(result));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartsList.size();
    }

    public static class CartsViewHolder extends RecyclerView.ViewHolder{
        TextView cartProductName, tvCartQuantity, cartProductPrice;
        ImageView imgCartItem, btnUpCartQty, btnDownCartQty;

        public CartsViewHolder(@NonNull View itemView) {
            super(itemView);
            cartProductName = itemView.findViewById(R.id.cartProductName);
            imgCartItem = itemView.findViewById(R.id.imgCartItem);
            tvCartQuantity = itemView.findViewById(R.id.tvCartQuantity);
            btnUpCartQty = itemView.findViewById(R.id.btnUpCartQty);
            btnDownCartQty = itemView.findViewById(R.id.btnDownCartQty);
            cartProductPrice = itemView.findViewById(R.id.cartProductPrice);
        }
    }
}
