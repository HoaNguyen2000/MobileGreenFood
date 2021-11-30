package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.JsonFood;
import com.example.mobilegreenfood.adapter.CategoryAdapter;
import com.example.mobilegreenfood.adapter.PopularFoodAdapter;
import com.example.mobilegreenfood.model.Category;
import com.example.mobilegreenfood.model.PopularFood;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;
    TextView tvtesst;
    JsonFood jsonFood;
    ImageView btnSearchProduct;
    ArrayList<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnSearchProduct = findViewById(R.id.btnSearchProduct);
        getCategory();
        btnSearchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCategory();
            }
        });
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
        JsonFood.jsonFood.getCategory().enqueue(new Callback<List<Category>>() {
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