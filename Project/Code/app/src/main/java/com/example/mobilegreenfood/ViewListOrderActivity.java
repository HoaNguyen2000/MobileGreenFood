package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.adapter.CartsAdapter;
import com.example.mobilegreenfood.adapter.OrdersAdapter;
import com.example.mobilegreenfood.model.Carts;
import com.example.mobilegreenfood.model.Order;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewListOrderActivity extends AppCompatActivity {
    RecyclerView rvListOrder;
    OrdersAdapter ordersAdapter;
    List<Order> orderArrayList;
    public static final String[] ORDER_STATUS = {
            "Đã tạo hoá đơn",
            "Đã chuẩn bị hàng",
            "Giao hàng thành công",
            "Đã huỷ đơn"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_order);
        rvListOrder = findViewById(R.id.rvListOrder);
        loadOrders();
    }
    private void setCartRecycler(List<Order> items) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvListOrder.setLayoutManager(layoutManager);
        ordersAdapter = new OrdersAdapter(this, items);
        rvListOrder.setAdapter(ordersAdapter);
    }
    private void loadOrders() {
        AppInterface.APP_INTERFACE.getListOrder(getToken()).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> orderList = response.body();
                setCartRecycler(orderList);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(ViewListOrderActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getToken(){
        SharedPreferences sharedPreferences= this.getSharedPreferences(LoginActivity.PREF, Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString(LoginActivity.keyToken, null);
    }
}