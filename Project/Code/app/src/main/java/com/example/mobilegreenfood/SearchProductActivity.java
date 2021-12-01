package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.adapter.ListProductAdapter;
import com.example.mobilegreenfood.model.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductActivity extends AppCompatActivity {
    RecyclerView searchFoodRecycler;
    ListProductAdapter listProductAdapter;
    EditText edSearchProductActivity;
    ImageView btnSearchProductActivity;
    public static Context context;
    String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        edSearchProductActivity = findViewById(R.id.edSearchProductActivity);
        btnSearchProductActivity = findViewById(R.id.btnSearchProductActivity);
        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        edSearchProductActivity.setText(query);
        getProductByQuery(query);
        btnSearchProductActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = edSearchProductActivity.getText().toString();
                getProductByQuery(query);
            }
        });
    }

    private void setProductRecycler(List<Food> foodList){
        searchFoodRecycler = findViewById(R.id.searchFoodRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        searchFoodRecycler.setLayoutManager(layoutManager);
        listProductAdapter = new ListProductAdapter(this, foodList);
        searchFoodRecycler.setAdapter(listProductAdapter);
    }

    private void getProductByQuery(String query){
        AppInterface.APP_INTERFACE.getProductByQuery(query).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                List<Food> foodList = response.body();
                setProductRecycler(foodList);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(SearchProductActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}