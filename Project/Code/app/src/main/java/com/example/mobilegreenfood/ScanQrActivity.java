package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.model.Carts;
import com.example.mobilegreenfood.model.Food;
import com.google.gson.Gson;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanQrActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView scannerView;
    Gson gson= new Gson();
    TextView popupId, popupName, popupPrice, popupQtyInput;
    Button popupBtnAddCart, popupBtnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
//        init();
        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void handleResult(Result rawResult) {
//        String mess = rawResult.getText();
        Food food = gson.fromJson(rawResult.getText(), Food.class);
        String message = "Mã sản phẩm: " + food.getProduct_id()+"\n"
                        +"Tên sản phẩm: " + food.getProduct_name()+"\n"
                        +"Giá: " + food.getProduct_price();
//        onBackPressed();
        scannerView.stopCamera();
        alert(message, food.getProduct_id());
    }
    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
//    private void init(){
//        popupId = findViewById(R.id.popupId);
//        popupName = findViewById(R.id.popupName);
//        popupPrice = findViewById(R.id.popupPrice);
//        popupQtyInput = findViewById(R.id.popupQtyInput);
//        popupBtnAddCart = findViewById(R.id.popupBtnAddCart);
//        popupBtnCancel = findViewById(R.id.popupBtnCancel);
//    }
    private void alert(String message, int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(ScanQrActivity.this);
        builder.setTitle("Kết quả").setMessage(message);
        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scannerView.startCamera();
            }
        });
        builder.setPositiveButton("Thêm vào giỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addProductCart(id);
            }
        });
        builder.show();
    }
    public String getToken(){
        SharedPreferences sharedPreferences= this.getSharedPreferences("PREFERENCE_DATA", Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString(LoginActivity.keyToken, "NULL");
    }
    private void addProductCart(int id){
        AppInterface.APP_INTERFACE.addProductCarts(getToken(), id, 1).enqueue(new Callback<Carts>() {
            @Override
            public void onResponse(Call<Carts> call, Response<Carts> response) {
                if(response.code() == 203){
                    Toast.makeText(getApplicationContext(), "Đã cộng số lượng vào giỏ", Toast.LENGTH_LONG).show();
                }else if(response.code() == 200){
                    Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ", Toast.LENGTH_LONG).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<Carts> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}