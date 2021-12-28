package com.example.mobilegreenfood.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobilegreenfood.CartActivity;
import com.example.mobilegreenfood.DashboardActivity;
import com.example.mobilegreenfood.Interface.AppInterface;
import com.example.mobilegreenfood.LoginActivity;
import com.example.mobilegreenfood.R;
import com.example.mobilegreenfood.ScanQrActivity;
import com.example.mobilegreenfood.model.Carts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartsAdapter extends  RecyclerView.Adapter<CartsAdapter.CartsViewHolder>{
    public static Context context;
    private List<Carts> cartsList;

    public CartsAdapter(Context context, List<Carts> cartsList) {
        this.context = context;
        this.cartsList = cartsList;
    }

    @NonNull
    @Override
    public CartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartsAdapter.CartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Carts item = cartsList.get(position);
        holder.cartProductName.setText(item.getProduct_name());
        holder.tvCartQuantity.setText(String.valueOf(item.getQuantity()));
        holder.cartProductPrice.setText("$"+ String.valueOf(item.getProduct_price()));
        Glide.with(context).load(item.getProduct_image()).into(holder.imgCartItem);
        holder.btnDownCartQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQty = Integer.parseInt(holder.tvCartQuantity.getText().toString());
                if(currentQty <= 1){
                    Toast.makeText(context.getApplicationContext(), "Số lượng tối thiểu là 1", Toast.LENGTH_LONG).show();
                }
                else{
                    int result = currentQty - 1;
                    holder.tvCartQuantity.setText(String.valueOf(result));
                }
            }
        });
        holder.btnUpCartQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQty = Integer.parseInt(holder.tvCartQuantity.getText().toString());
                if(currentQty > 20){
                    Toast.makeText(context.getApplicationContext(), "Số lượng không được vượt quá 20", Toast.LENGTH_LONG).show();
                }
                else{
                    int result = currentQty + 1;
                    holder.tvCartQuantity.setText(String.valueOf(result));
                }
            }
        });
        holder.btnDeleteCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDeleteDiaglog(item.getCart_id(), position);
            }
        });
        holder.btnUpdateCartQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(holder.tvCartQuantity.getText().toString());
                updateCartQty(item.getProduct_id(), qty);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartsList.size();
    }

    public static class CartsViewHolder extends RecyclerView.ViewHolder{
        TextView cartProductName, tvCartQuantity, cartProductPrice;
        ImageView imgCartItem, btnUpCartQty, btnDownCartQty,btnDeleteCartItem, btnUpdateCartQty;

        public CartsViewHolder(@NonNull View itemView) {
            super(itemView);
            cartProductName = itemView.findViewById(R.id.cartProductName);
            imgCartItem = itemView.findViewById(R.id.imgCartItem);
            tvCartQuantity = itemView.findViewById(R.id.tvCartQuantity);
            btnUpCartQty = itemView.findViewById(R.id.btnUpCartQty);
            btnDownCartQty = itemView.findViewById(R.id.btnDownCartQty);
            cartProductPrice = itemView.findViewById(R.id.cartProductPrice);
            btnDeleteCartItem = itemView.findViewById(R.id.btnDeleteCartItem);
            btnUpdateCartQty = itemView.findViewById(R.id.btnUpdateCartQty);
        }
    }

    private void updateCartQty(int product_id, int quantity){
        AppInterface.APP_INTERFACE.updateQtyCart(DashboardActivity.TOKEN_USER, product_id, quantity).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.code() == 203){
                    Toast.makeText(context.getApplicationContext(), "Đã cập nhật số lượng", Toast.LENGTH_LONG).show();
                }else if(response.code() == 400){
                    Toast.makeText(context.getApplicationContext(), "Đã có lỗi khi sửa", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void alertDeleteDiaglog(int cart_id, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xoá sản phẩm").setMessage("Bạn có chắc muốn xoá sản phẩm này khỏi giỏ hàng không");
        builder.setNegativeButton("Huỷ", null);
        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteCartItem(cart_id, position);
            }
        });
        builder.show();
    }
    private void deleteCartItem(int cart_id, int position){
        AppInterface.APP_INTERFACE.deleteProductCarts(DashboardActivity.TOKEN_USER, cart_id).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.code() == 204){
                    Toast.makeText(context, "Đã xoá sản phẩm khỏi giỏ hàng", Toast.LENGTH_LONG).show();
                    removeAt(position);
                }else if(response.code() == 404){
                    Toast.makeText(context, "Đã có lỗi khi xoá sản phẩm này", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void removeAt(int position) {
        cartsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cartsList.size());
    }

}
