package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mobilegreenfood.adapter.PopularFoodAdapter;
import com.example.mobilegreenfood.model.PopularFood;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView popularRecycler;
    PopularFoodAdapter popularFoodAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<PopularFood> popularFoodList = new ArrayList<>();
        popularFoodList.add(new PopularFood("Rau cu ha noi", "250k", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Rau xanh 4k", "450k", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Mi tom chua cay", "650k", R.drawable.popularfood3));
        popularFoodList.add(new PopularFood("Rau cu mua he", "220k", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Rau cai mua xuan", "480k", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Mi tom", "750k", R.drawable.popularfood3));
        setPopularRecycler(popularFoodList);
    }
    private void setPopularRecycler(List<PopularFood> popularFoodList){
        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);
    }
}