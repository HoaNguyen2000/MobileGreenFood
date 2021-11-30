package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.CategoryInterface;
import com.example.mobilegreenfood.adapter.CategoryAdapter;
import com.example.mobilegreenfood.adapter.SlideAdapter;
import com.example.mobilegreenfood.model.Category;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;
    ImageView btnSearchProduct;
    SliderView sliderView;
    int[] listImage = {
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4,
            R.drawable.banner5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnSearchProduct = findViewById(R.id.btnSearchProduct);
        sliderView = findViewById(R.id.imageSlide);
        setImageSlide(listImage);
        getCategory();
        btnSearchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setImageSlide(int[] listImage) {
        SlideAdapter slideAdapter = new SlideAdapter(listImage);
        sliderView.setSliderAdapter(slideAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }

    private void setCategoryRecycler(ArrayList<Category> items) {
        categoryRecycler = findViewById(R.id.categoryRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, items);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    //
    private void getCategory() {
        CategoryInterface.categoryInterface.getListCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Toast.makeText(DashboardActivity.this, "Success", Toast.LENGTH_LONG).show();
                List<Category> categories = (List<Category>) response.body();
                setCategoryRecycler((ArrayList<Category>) categories);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}