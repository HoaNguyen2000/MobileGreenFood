package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.adapter.ListProductAdapter;
import com.example.mobilegreenfood.model.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductByCategoryActivity extends AppCompatActivity {
    RecyclerView productByCategoryRecycler;
    ListProductAdapter listProductAdapter;
    public static Context context;
    TextView BannerCategoryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_category);
        BannerCategoryName = findViewById(R.id.BannerCategoryName);
        Intent intent = getIntent();
        int category_id = intent.getIntExtra("category_id", 0);
        String category_name = intent.getStringExtra("category_name");
        BannerCategoryName.setText(category_name);
        getProductByCategoryId(category_id);
    }

    private int getCategoryIdIntent() {
        Intent intent = getIntent();
        return intent.getIntExtra("category_id", 0);
    }

    private void setProductByCategoryRecycler(List<Food> foodList){
        productByCategoryRecycler = findViewById(R.id.productByCategoryRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        productByCategoryRecycler.setLayoutManager(layoutManager);
        listProductAdapter = new ListProductAdapter(this, foodList);
        productByCategoryRecycler.setAdapter(listProductAdapter);
    }

    private void getProductByCategoryId(int category_id){
        AppInterface.APP_INTERFACE.getProductByCategory(category_id).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                Toast.makeText(ProductByCategoryActivity.this, "Success", Toast.LENGTH_LONG).show();
                List<Food> foodList = response.body();
                setProductByCategoryRecycler(foodList);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(ProductByCategoryActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}