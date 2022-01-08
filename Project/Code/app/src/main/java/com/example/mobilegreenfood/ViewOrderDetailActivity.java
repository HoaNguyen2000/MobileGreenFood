package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.model.Order;
import com.example.mobilegreenfood.model.OrderItems;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrderDetailActivity extends AppCompatActivity {
    TextView tvOrderDetailId, tvOrderDetailDiscount, tvOrderDetailTotal, btnChangeOrderStatus;
    Spinner spOrderStatus;
    ListView lvOrderItems;
    ArrayAdapter<String> orderItemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_detail);
        init();
        Order order = (Order) getIntent().getSerializableExtra("order");
        setValueIntent(order);
        btnChangeOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOrderStatus(order.getOrder_id(), spOrderStatus.getSelectedItemPosition() + 2);
            }
        });
    }
    
    private void setValueIntent(Order order){
        tvOrderDetailId.setText("Mã đơn hàng: "+ order.getOrder_id());
        tvOrderDetailTotal.setText("Thành tiền: $" + order.getAmount());
        if(order.getCoupon_id() != 0){
            tvOrderDetailDiscount.setText("Giảm giá: " + order.getCoupon_percent_discount() + "%");
        }
        setItemOnSpinner();
        getOrderItems(order.getOrder_id());
        spOrderStatus.setSelection(order.getOrder_status() -2, true);
    }
    private void getOrderItems(int order_id) {
        orderItemArrayAdapter = new ArrayAdapter<>(ViewOrderDetailActivity.this, android.R.layout.simple_list_item_1);
        lvOrderItems.setAdapter(orderItemArrayAdapter);
        AppInterface.APP_INTERFACE.getOrderItem(getToken(), order_id).enqueue(new Callback<List<OrderItems>>() {
            @Override
            public void onResponse(Call<List<OrderItems>> call, Response<List<OrderItems>> response) {
                if(response.code() == 200){
                    List<OrderItems> orderItems = response.body();
                    for (OrderItems orderItem:
                            orderItems) {
                        orderItemArrayAdapter.add(orderItem.getOrder_item_name() + "\n" +
                                "Giá: $" + orderItem.getOrder_item_price()+ "              " +
                                "SL:" + orderItem.getOrder_item_quantity());
                        orderItemArrayAdapter.notifyDataSetChanged();
                    }
                }else {
                    Toast.makeText(ViewOrderDetailActivity.this, "Có lỗi xảy ra 404", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OrderItems>> call, Throwable t) {
                Toast.makeText(ViewOrderDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getToken(){
        SharedPreferences sharedPreferences= this.getSharedPreferences(LoginActivity.PREF, Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString(LoginActivity.keyToken, null);
    }
    private void setItemOnSpinner(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Đã tạo hoá đơn");//2
        arrayList.add("Đã chuẩn bị hàng");//3
        arrayList.add("Giao hàng thành công");//4
        arrayList.add("Đã huỷ đơn");//5
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrderStatus.setAdapter(arrayAdapter);
    }
    private void updateOrderStatus(int order_id, int order_status){
        AppInterface.APP_INTERFACE.updateOrderStatus(getToken(), order_id, order_status).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.code() == 200){
                    finish();
                    Toast.makeText(ViewOrderDetailActivity.this, "Đổi trạng thái thành công!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ViewOrderDetailActivity.this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(ViewOrderDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void init(){
        tvOrderDetailId = findViewById(R.id.tvOrderDetailId);
        tvOrderDetailDiscount = findViewById(R.id.tvOrderDetailDiscount);
        tvOrderDetailTotal = findViewById(R.id.tvOrderDetailTotal);
        btnChangeOrderStatus = findViewById(R.id.btnChangeOrderStatus);
        spOrderStatus = findViewById(R.id.spOrderStatus);
        lvOrderItems = findViewById(R.id.lvOrderItems);
    }
}