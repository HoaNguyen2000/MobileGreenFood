package com.example.mobilegreenfood.Interface;

import com.example.mobilegreenfood.model.Carts;
import com.example.mobilegreenfood.model.Category;
import com.example.mobilegreenfood.model.Food;
import com.example.mobilegreenfood.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    //category & product
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

    //auth
    @POST("api/login")
    @FormUrlEncoded
    Call <User> login(
            @Field("email") String email,
            @Field("password") String password
    );
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/get-user")
    Call <User> getUser(@Header("Authorization") String authHeader);

//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/logout")
    Call <User> logout(@Header("Authorization") String authHeader);

    //cart
    @POST("api/add-product-cart")
    @FormUrlEncoded
    Call <Carts>  addProductCarts(
            @Header("Authorization") String authHeader,
            @Field("product_id") int product_id,
            @Field("quantity") int quantity
    );

    @GET("api/my-cart")
    Call<List<Carts>> getCarts(
            @Header("Authorization") String authHeader
    );

    //203 success ? 400 error
    @POST("api/update-qty-cart")
    @FormUrlEncoded
    Call<Carts> updateQtyCart(
            @Header("Authorization") String authHeader,
            @Field("product_id") int product_id,
            @Field("quantity") int quantity
    );

    //204 success ? 404 not found
    @GET("api/delete-product-cart/{id}")
    Call<Carts> deleteProductCarts(
            @Header("Authorization") String authHeader,
            @Path("id") int id
    );
}
