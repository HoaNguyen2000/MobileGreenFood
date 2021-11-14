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

import com.example.mobilegreenfood.DetailsProductActivity;
import com.example.mobilegreenfood.R;
import com.example.mobilegreenfood.model.SearchFood;

import java.util.List;

public class SearchFoodAdapter extends RecyclerView.Adapter<SearchFoodAdapter.SearchFoodViewHolder>{
    public static Context context;
    public static List<SearchFood> searchFoodList;

    public SearchFoodAdapter(Context context, List<SearchFood> searchFoodList) {
        this.context = context;
        this.searchFoodList = searchFoodList;
    }

    @NonNull
    @Override
    public SearchFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_food_item, parent, false);
        return new SearchFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchFoodViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.searchFoodImage.setImageResource(R.drawable.asiafood1);
        holder.tvSearchFoodName.setText(searchFoodList.get(position).getFoodName());
        holder.tvSearchFoodPrice.setText("$" + searchFoodList.get(position).getFoodPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsProductActivity.class);
                i.putExtra("foodId", searchFoodList.get(position).getFoodId());
                i.putExtra("foodName", searchFoodList.get(position).getFoodName());
                i.putExtra("foodPrice", searchFoodList.get(position).getFoodPrice());
                i.putExtra("foodImageUrl", searchFoodList.get(position).getFoodImageUrl());
                i.putExtra("foodRating", searchFoodList.get(position).getFoodRating());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchFoodList.size();
    }

    public static class SearchFoodViewHolder extends RecyclerView.ViewHolder{
        ImageView searchFoodImage;
        TextView tvSearchFoodName, tvSearchFoodPrice;

        public SearchFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            searchFoodImage = itemView.findViewById(R.id.searchFoodImage);
            tvSearchFoodName = itemView.findViewById(R.id.tvSearchFoodName);
            tvSearchFoodPrice = itemView.findViewById(R.id.tvSearchFoodPrice);
        }
    }
}
