package com.example.book;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOBook {
    private DatabaseReference databaseReference;
    public DAOBook(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Book.class.getSimpleName());
    }
    public Task<Void> AddBook(Book book){
        return databaseReference.push().setValue(book);
    }
    public Query getBookData(){
        return databaseReference.orderByKey();
    }
}
