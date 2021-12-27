package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.adapter.CartsAdapter;
import com.example.mobilegreenfood.adapter.CategoryAdapter;
import com.example.mobilegreenfood.model.Carts;
import com.example.mobilegreenfood.model.Category;
import com.example.mobilegreenfood.model.Food;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCartItems;
    CartsAdapter cartsAdapter;
    TextView tvCartTotalPrice, tvCartFinalPrice;
    ImageView btnOpenScanQr;
    public static Food food;
    public static boolean isScanSuccess = false;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
        loadItemCart();
        btnOpenScanQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanQrActivity.class));
            }
        });
        if(isScanSuccess == true){
            String message = "Food id:" + food.getProduct_id()+"\n"
                        +"Food name" + food.getProduct_name()+"\n"
                        +"Food price" + food.getProduct_price()+"\n";
            Log.e("Tag345", message);
            Toast.makeText(getApplicationContext(), "M1MM1", Toast.LENGTH_LONG).show();
        }

    }

    private void init(){
        rvCartItems = findViewById(R.id.rvCartItems);
        tvCartTotalPrice = findViewById(R.id.tvCartTotalPrice);
        tvCartFinalPrice = findViewById(R.id.tvCartFinalPrice);
        btnOpenScanQr = findViewById(R.id.btnOpenScanQr);
    }

    private void setCartRecycler(List<Carts> items) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCartItems.setLayoutManager(layoutManager);
        cartsAdapter = new CartsAdapter(this, items);
        rvCartItems.setAdapter(cartsAdapter);
    }
    private void loadItemCart(){
        AppInterface.APP_INTERFACE.getCarts(getToken()).enqueue(new Callback<List<Carts>>() {
            @Override
            public void onResponse(Call<List<Carts>> call, Response<List<Carts>> response) {
                List<Carts> cartsList = response.body();
                setCartRecycler(cartsList);
                caculatorPrice(cartsList);
            }

            @Override
            public void onFailure(Call<List<Carts>> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void caculatorPrice(List<Carts> carts){
        int total = 0;
        for (Carts cart : carts) {
            total += cart.getProduct_price() * cart.getQuantity();
        }
        tvCartTotalPrice.setText("$"+String.valueOf(total));
        tvCartFinalPrice.setText("$"+String.valueOf(total));
    }
    private String getToken(){
        SharedPreferences sharedPreferences= this.getSharedPreferences("PREFERENCE_DATA", Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString(LoginActivity.keyToken, "NULL");
    }
}