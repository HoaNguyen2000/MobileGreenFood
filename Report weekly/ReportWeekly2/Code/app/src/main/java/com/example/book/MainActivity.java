package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText edBookName, edBookPrice;
    Button btnAddBook, btnOpenViewBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DAOBook dao = new DAOBook();
        spinner = findViewById(R.id.spinner);
        edBookName = findViewById(R.id.edBookName);
        edBookPrice = findViewById(R.id.edBookPrice);
        btnAddBook = findViewById(R.id.btnAddBook);
        btnOpenViewBook = findViewById(R.id.btnOpenViewBook);
        setItemOnSpinner();

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bookName = edBookName.getText().toString();
                String bookType = spinner.getSelectedItem().toString();
                int bookPrice = Integer.parseInt(edBookPrice.getText().toString());
                Book book = new Book(bookName, bookType, bookPrice);
                dao.AddBook(book).addOnSuccessListener(suc->
                {
                    Toast.makeText(MainActivity.this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(MainActivity.this, er.getMessage() , Toast.LENGTH_SHORT).show();
                });
            }
        });
        btnOpenViewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ViewBookActivity.class);
                startActivity(i);
            }
        });
    }
    private void setItemOnSpinner(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Giáo dục");
        arrayList.add("Đời sống");
        arrayList.add("Tiểu thuyết");
        arrayList.add("Trinh thám");
        arrayList.add("Truyện tranh");
        arrayList.add("Kinh tế");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}