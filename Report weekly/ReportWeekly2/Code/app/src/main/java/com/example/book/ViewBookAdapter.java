package com.example.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Book> bookList = new ArrayList<>();
    public ViewBookAdapter(Context context){
        this.context = context;
    }
    public void setItems(ArrayList<Book> books){
        bookList.addAll(books);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookViewHolder bookViewHolder = (BookViewHolder) holder;
        Book book = bookList.get(position);
        bookViewHolder.tvBookName.setText(book.getBookName());
        bookViewHolder.tvBookType.setText(book.getBookType());
        bookViewHolder.tvBookPrice.setText("$" + String.valueOf(book.getBookPrice()));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
    public class BookViewHolder extends RecyclerView.ViewHolder{
        TextView tvBookName, tvBookType, tvBookPrice;
        public BookViewHolder(@NonNull View itemView){
            super(itemView);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvBookPrice = itemView.findViewById(R.id.tvBookPrice);
            tvBookType = itemView.findViewById(R.id.tvBookType);
        }
    }
}
