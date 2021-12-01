package com.example.mobilegreenfood.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobilegreenfood.DetailsProductActivity;
import com.example.mobilegreenfood.R;
import com.example.mobilegreenfood.model.Food;

import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.SearchFoodViewHolder>{
    public static Context context;
    public static List<Food> foodList;

    public ListProductAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public SearchFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);
        return new SearchFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchFoodViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvItemFoodName.setText(foodList.get(position).getProduct_name());
        holder.tvItemFoodPrice.setText("$" + foodList.get(position).getProduct_price());
        Glide.with(context).load(foodList.get(position).getProduct_image()).into(holder.itemFoodImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsProductActivity.class);
                intent.putExtra("product_id", foodList.get(position).getProduct_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class SearchFoodViewHolder extends RecyclerView.ViewHolder{
        ImageView itemFoodImage;
        TextView tvItemFoodName, tvItemFoodPrice;

        public SearchFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            itemFoodImage = itemView.findViewById(R.id.itemFoodImage);
            tvItemFoodName = itemView.findViewById(R.id.tvItemFoodName);
            tvItemFoodPrice = itemView.findViewById(R.id.tvItemFoodPrice);
        }
    }
}
