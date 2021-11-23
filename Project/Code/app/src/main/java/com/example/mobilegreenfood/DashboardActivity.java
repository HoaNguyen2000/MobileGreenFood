package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mobilegreenfood.adapter.CategoryAdapter;
import com.example.mobilegreenfood.adapter.PopularFoodAdapter;
import com.example.mobilegreenfood.model.Category;
import com.example.mobilegreenfood.model.PopularFood;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ArrayList<Category> items = new ArrayList<>();
        items.add(new Category(1, "Bánh bao", "test1"));
        items.add(new Category(2, "Bánh mì", "test1"));
        items.add(new Category(3, "Bánh xôi", "test1"));
        items.add(new Category(4, "Bánh gõ", "test1"));
        items.add(new Category(5, "Bánh lop", "test1"));
        setCategoryRecycler(items);
    }
    private void setCategoryRecycler(ArrayList<Category> items){
        categoryRecycler = findViewById(R.id.categoryRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, items);
        categoryRecycler.setAdapter(categoryAdapter);
    }
}