package com.example.book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewBookActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewBookAdapter adapter;
    DAOBook daoBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        recyclerView = findViewById(R.id.rvBook);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new ViewBookAdapter(this);
        recyclerView.setAdapter(adapter);
        daoBook = new DAOBook();
        loadData();
    }

    private void loadData() {
        daoBook.getBookData().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Book> books = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()){
                    Book book = data.getValue(Book.class);
                    book.setKey(data.getKey());
                    books.add(book);
                }
                adapter.setItems(books);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewBookActivity.this, "FAIL TO GET DATA", Toast.LENGTH_SHORT).show();
            }
        });
    }
}