package com.example.mobilegreenfood.Interface;

import com.example.mobilegreenfood.model.Category;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface JsonFood {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    JsonFood jsonFood = new Retrofit.Builder()
            .baseUrl("https://mobilefood.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(JsonFood.class);
    @GET("api/get-category")
    Call <List<Category>> getCategory();
}
