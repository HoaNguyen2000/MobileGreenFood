package com.example.mobilegreenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.SharedPreference.TokenUser;
import com.example.mobilegreenfood.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView tvForgotPassword;
    EditText edUsername, edPassword;
    public static String TOKEN_API;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(edUsername.getText().toString(), edPassword.getText().toString());

            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogMessage("Thông báo", "Vui lòng liên hệ amdin để được cấp lại mật khẩu\nSĐT: 039-998-899");
            }
        });
    }
    private void init(){
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
    }
    private void login(String email, String password){
        AppInterface.APP_INTERFACE.login(email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200){
                    User users = response.body();
                    TOKEN_API = users.getApi_token();
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                }else if(response.code() == 400){
                    alertDialogMessage("Thông báo", "Tên tài khoản hoặc mật khẩu sai");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void alertDialogMessage(String title, String message){
        AlertDialog.Builder ab = new AlertDialog.Builder(LoginActivity.this);
        ab.setTitle(title);
        ab.setMessage(message);
        ab.setPositiveButton("OK", null);
        ab.show();
    }

}