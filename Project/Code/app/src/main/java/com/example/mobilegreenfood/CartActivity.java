package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.adapter.CartsAdapter;
import com.example.mobilegreenfood.model.Carts;
import com.example.mobilegreenfood.model.Coupon;
import com.example.mobilegreenfood.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCartItems;
    CartsAdapter cartsAdapter;
    public static TextView tvCartTotalPrice, tvCartFinalPrice, tvDiscountPercent;
    EditText edCoupon;
    ImageView btnOpenScanQr, btnDeleteCoupon, btnAddCoupon;
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
        btnDeleteCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvDiscountPercent.getText().toString().equals("0%")){
                    Toast.makeText(CartActivity.this, "Bạn chưa áp mã nào", Toast.LENGTH_LONG);
                }else {
                    alertRemoveCoupon();
                }
            }
        });
        btnAddCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coupon_code = edCoupon.getText().toString();
                checkCoupon(coupon_code);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    private void init(){
        rvCartItems = findViewById(R.id.rvCartItems);
        tvCartTotalPrice = findViewById(R.id.tvCartTotalPrice);
        tvCartFinalPrice = findViewById(R.id.tvCartFinalPrice);
        tvDiscountPercent = findViewById(R.id.tvDiscountPercent);
        btnDeleteCoupon = findViewById(R.id.btnDeleteCoupon);
        btnAddCoupon = findViewById(R.id.btnAddCoupon);
        btnOpenScanQr = findViewById(R.id.btnOpenScanQr);
        edCoupon = findViewById(R.id.edCoupon);
    }
    public void caculatorPrice(List<Carts> carts){
        int total = 0;
        for (Carts cart : carts) {
            total += cart.getProduct_price() * cart.getQuantity();
        }
        tvCartTotalPrice.setText("$"+String.valueOf(total));
        if(tvDiscountPercent.getText().toString().equals("0%")){
            tvCartFinalPrice.setText("$"+String.valueOf(total));
        }
        Log.e("finalPriceTotal", String.valueOf(total));
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
                getOrder();
            }

            @Override
            public void onFailure(Call<List<Carts>> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getOrder(){
        AppInterface.APP_INTERFACE.getOrder(getToken()).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.code() == 200){
                    double total = 0.0, discount_value = 0.0, percent = 0.0, finalPrice = 0.0;
                    Order order = response.body();
                    edCoupon.setText(order.getCoupon_code());
                    total = Double.parseDouble(tvCartTotalPrice.getText().toString().replace("$", ""));
                    discount_value = Double.valueOf(order.getCoupon_percent_discount());
                    percent = (total * (discount_value / 100));
                    finalPrice = total - percent;
                    tvDiscountPercent.setText(String.valueOf(order.getCoupon_percent_discount())+"%");
                    tvCartFinalPrice.setText("$"+String.valueOf(finalPrice));
                    Log.e("finalPriceIfIf", String.valueOf(finalPrice));
                }
//                else {
//                    String price = tvCartTotalPrice.getText().toString();
//                    tvCartFinalPrice.setText(price);
//                    Log.e("finalPriceGetOrderElse", price);
//                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void checkCoupon(String coupon_code){
        AppInterface.APP_INTERFACE.checkCoupon(coupon_code).enqueue(new Callback<Coupon>() {
            @Override
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {
                if(response.code() == 200){
                    Coupon coupon = response.body();
                    addCoupon(coupon.getCoupon_id());
                }else if(response.code() == 404){
                    Toast.makeText(CartActivity.this, "Mã giảm giá không tồn tại hoặc đã hết lượt", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Coupon> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void addCoupon(int coupon_id){
        AppInterface.APP_INTERFACE.insertCouponOrder(getToken(), coupon_id).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.code() == 203){
                    Toast.makeText(CartActivity.this, "Đã thêm thành công", Toast.LENGTH_LONG).show();
                    getOrder();
                }else {
                    Toast.makeText(CartActivity.this, "Fail100000", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void removeCoupon(){
        AppInterface.APP_INTERFACE.removeCoupon(getToken()).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.code() == 203){
                    getOrder();
                    Toast.makeText(CartActivity.this, "Đã xoá coupon này", Toast.LENGTH_LONG);
                    edCoupon.setText("");
                    tvDiscountPercent.setText("0%");
                    tvCartFinalPrice.setText(tvCartTotalPrice.getText().toString());
                    Log.e("finalPriceRemove", tvCartTotalPrice.getText().toString());
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void alertRemoveCoupon(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
        builder.setTitle("Xoá mã giảm").setMessage("Bạn có chắc muốn xoá mã giảm giá này không!");
        builder.setNegativeButton("Huỷ", null);
        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeCoupon();
            }
        });
        builder.show();
    }

    private String getToken(){
        SharedPreferences sharedPreferences= this.getSharedPreferences("PREFERENCE_DATA", Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString(LoginActivity.keyToken, null);
    }



}