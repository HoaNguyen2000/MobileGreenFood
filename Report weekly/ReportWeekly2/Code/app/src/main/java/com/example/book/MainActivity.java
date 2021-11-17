package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText edBookName, edBookPrice;
    Button btnAddBook, btnOpenViewBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        edBookName = findViewById(R.id.edBookName);
        edBookPrice = findViewById(R.id.edBookPrice);
        btnAddBook = findViewById(R.id.btnAddBook);
        btnOpenViewBook = findViewById(R.id.btnOpenViewBook);
        setItemOnSpinner();

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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