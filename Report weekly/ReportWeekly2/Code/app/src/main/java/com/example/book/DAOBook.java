package com.example.book;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOBook {
    private DatabaseReference databaseReference;
    public DAOBook(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Book.class.getSimpleName());
    }

}
