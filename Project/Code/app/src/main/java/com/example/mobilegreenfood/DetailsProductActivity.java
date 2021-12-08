package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.model.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsProductActivity extends AppCompatActivity {
    ImageView btnUpCount, btnDownCount, ivProductImage;
    TextView tvCountProduct, tvProductName, tvPrice, tvProductDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        init();
        getDetailsProduct(getProductIdIntent());
        btnDownCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = Integer.parseInt((String) tvCountProduct.getText()) -1;
                tvCountProduct.setText(String.valueOf(current));
            }
        });
        btnUpCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = Integer.parseInt((String) tvCountProduct.getText()) +1;
                tvCountProduct.setText(String.valueOf(current));
            }
        });
    }
    private void init(){
        btnDownCount = (ImageView)findViewById(R.id.btnDownCount);
        btnUpCount = (ImageView) findViewById(R.id.btnUpCount);
        ivProductImage = (ImageView) findViewById(R.id.ivProductImage);
        tvProductName = (TextView) findViewById(R.id.tvProductName);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvCountProduct = (TextView) findViewById(R.id.tvCountProduct);
        tvProductDescription = (TextView) findViewById(R.id.tvProductDescription);
    }

    private int getProductIdIntent(){
        Intent intent = getIntent();
        return intent.getIntExtra("product_id", 0);
    }

    private void setItem(String name, String price, String productDescription, String productImage){
        tvProductName.setText(name);
        tvPrice.setText(price);
        tvProductDescription.setText(productDescription);
        Glide.with(this).load(productImage).into(ivProductImage);
    }
    private void getDetailsProduct(int product_id){
        AppInterface.APP_INTERFACE.getDetailsProduct(product_id).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                List<Food> foodList = response.body();
                String name = foodList.get(0).getProduct_name();
                String price = foodList.get(0).getProduct_price();
                String description = foodList.get(0).getProduct_description();
                String image = foodList.get(0).getProduct_image();
                setItem(name,"$" + price, description, image);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(DetailsProductActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}