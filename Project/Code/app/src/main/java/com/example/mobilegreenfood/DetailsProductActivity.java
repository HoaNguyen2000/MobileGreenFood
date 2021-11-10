package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsProductActivity extends AppCompatActivity {
    ImageView btnUpCount, btnDownCount, ivProductImage;
    TextView tvCountProduct, tvProductName, tvPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        btnDownCount = (ImageView)findViewById(R.id.btnDownCount);
        btnUpCount = (ImageView) findViewById(R.id.btnUpCount);
        ivProductImage = (ImageView) findViewById(R.id.ivProductImage);
        tvProductName = (TextView) findViewById(R.id.tvProductName);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvCountProduct = (TextView) findViewById(R.id.tvCountProduct);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        Integer foodImage = extras.getInt("foodImage");
        //Toast.makeText(DetailsProductActivity.this, foodImage, Toast.LENGTH_LONG).show();
        String name = extras.getString("name");
        String price = extras.getString("price");
        tvProductName.setText(name);
        tvPrice.setText(price);
        ivProductImage.setImageResource(foodImage);
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
}