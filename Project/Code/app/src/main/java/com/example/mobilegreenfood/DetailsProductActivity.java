package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsProductActivity extends AppCompatActivity {
    ImageView btnUpCount, btnDownCount;
    TextView tvCountProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        btnDownCount = (ImageView)findViewById(R.id.btnDownCount);
        btnUpCount = (ImageView) findViewById(R.id.btnUpCount);
        tvCountProduct = (TextView) findViewById(R.id.tvCountProduct);
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