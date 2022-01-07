package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobilegreenfood.model.Order;

public class ViewOrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_detail);
        Order order = (Order) getIntent().getSerializableExtra("order");

    }
}