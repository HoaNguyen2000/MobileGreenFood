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
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.SharedPreference.TokenUser;
import com.example.mobilegreenfood.adapter.CategoryAdapter;
import com.example.mobilegreenfood.adapter.SlideAdapter;
import com.example.mobilegreenfood.model.Category;
import com.example.mobilegreenfood.model.User;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;
    ImageView btnSearchProduct, imgAvatarUser;
    EditText edSearchProduct;
    TextView tvHome;
    SliderView sliderView;
    RelativeLayout btnMain, btnCart;
    public static User infoUser;
    int[] listImage = {
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4,
            R.drawable.banner5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        setImageSlide(listImage);
        getCategory();
        getUser(getToken());
        btnSearchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SearchProductActivity.class);
                intent.putExtra("query", edSearchProduct.getText().toString());
                startActivity(intent);
            }
        });

        imgAvatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                alertDialogMessage("Đăng xuất", "Bạn có chắc muốn đăng xuất");
                alertDialogMessage("Đăng xuất", getToken());
            }
        });
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void logout() {
        AppInterface.APP_INTERFACE.logout(getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                clearToken();
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(DashboardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(){
        btnSearchProduct = findViewById(R.id.btnSearchProduct);
        sliderView = findViewById(R.id.imageSlide);
        edSearchProduct = findViewById(R.id.edSearchProduct);
        btnMain = findViewById(R.id.btnMain);
        btnCart = findViewById(R.id.btnCart);
        tvHome = findViewById(R.id.tvHome);
        imgAvatarUser = findViewById(R.id.imgAvatarUser);
        categoryRecycler = findViewById(R.id.categoryRecycler);
    }
    private void setImageSlide(int[] listImage) {
        SlideAdapter slideAdapter = new SlideAdapter(listImage);
        sliderView.setSliderAdapter(slideAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }

    private void setCategoryRecycler(ArrayList<Category> items) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, items);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    private void getUser(String token){
        AppInterface.APP_INTERFACE.getUser(token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                infoUser = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getCategory() {
        AppInterface.APP_INTERFACE.getListCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categories = (List<Category>) response.body();
                setCategoryRecycler((ArrayList<Category>) categories);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void alertDialogMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setNegativeButton("Huỷ", null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });
        builder.show();
    }
    private String getToken(){
        SharedPreferences sharedPreferences= this.getSharedPreferences("PREFERENCE_DATA", Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString(LoginActivity.keyToken, "NULL");
    }
    private void clearToken() {
        SharedPreferences sharedPreferences= this.getSharedPreferences("PREFERENCE_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LoginActivity.keyToken, "");
        editor.apply();
    }
}