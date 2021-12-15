package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.adapter.CartsAdapter;
import com.example.mobilegreenfood.adapter.CategoryAdapter;
import com.example.mobilegreenfood.model.Carts;
import com.example.mobilegreenfood.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCartItems;
    CartsAdapter cartsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
        loadItemCart();
    }

    private void init(){
        rvCartItems = findViewById(R.id.rvCartItems);
    }

    private void setCartRecycler(List<Carts> items) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCartItems.setLayoutManager(layoutManager);
        cartsAdapter = new CartsAdapter(this, items);
        rvCartItems.setAdapter(cartsAdapter);
    }
    private void loadItemCart(){
        AppInterface.APP_INTERFACE.getCarts(DashboardActivity.token).enqueue(new Callback<List<Carts>>() {
            @Override
            public void onResponse(Call<List<Carts>> call, Response<List<Carts>> response) {
                List<Carts> cartsList = response.body();
                setCartRecycler(cartsList);
            }

            @Override
            public void onFailure(Call<List<Carts>> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}