package com.example.mobilegreenfood.Interface;

import com.example.mobilegreenfood.model.Category;
import com.example.mobilegreenfood.model.Food;
import com.example.mobilegreenfood.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppInterface {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    AppInterface APP_INTERFACE = new Retrofit.Builder()
            .baseUrl("https://mobilefood.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AppInterface.class);

    @GET("api/get-category")
    Call <List<Category>> getListCategory();

    @GET("api/get-product-by-category/{category_id}")
    Call <List<Food>> getProductByCategory(
            @Path("category_id") int category_id
    );

    @GET("api/search-product/{query}")
    Call <List<Food>> getProductByQuery(
            @Path("query") String query
    );

    @GET("api/details-product/{product_id}")
    Call <List<Food>> getDetailsProduct(
            @Path("product_id") int product_id
    );

    @POST("api/login")
    @FormUrlEncoded
    Call <List<User>> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/get-user")
    Call<List<User>> getUser(@Header("Authorization") String authHeader);

    @GET("api/logout")
    Call logout(@Header("Authorization") String authHeader);
}
