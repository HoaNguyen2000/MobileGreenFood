package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {
    TextView tvCheckoutTotalPrice, btnCheckoutOrder;
    EditText edOrderNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        init();
        getIntentCart();
        btnCheckoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double price = Double.valueOf(tvCheckoutTotalPrice.getText().toString().replace("$", ""));
                String order_note = edOrderNote.getText().toString();
                checkoutOrder(price, order_note);
            }
        });
    }
    private void init(){
        tvCheckoutTotalPrice = findViewById(R.id.tvCheckoutTotalPrice);
        edOrderNote = findViewById(R.id.edOrderNote);
        btnCheckoutOrder = findViewById(R.id.btnCheckoutOrder);
    }
    private void getIntentCart(){
        Intent intent = getIntent();
        tvCheckoutTotalPrice.setText(intent.getStringExtra("money"));
    }
    private void checkoutOrder(double price, String order_note){
        AppInterface.APP_INTERFACE.checkoutOrder(getToken(), price, order_note).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.code() == 203){
                    Toast.makeText(getApplicationContext(), "Đã tạo đơn hàng thành công", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CheckoutActivity.this, DashboardActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private String getToken(){
        SharedPreferences sharedPreferences= this.getSharedPreferences(LoginActivity.PREF, Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString(LoginActivity.keyToken, null);
    }
}