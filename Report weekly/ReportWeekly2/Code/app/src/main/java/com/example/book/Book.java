package com.example.book;

public class Book {
    private String bookName;
    private String bookType;
    private int bookPrice;

    public Book() {
    }

    public Book(String bookName, String bookType, int bookPrice) {
        this.bookName = bookName;
        this.bookType = bookType;
        this.bookPrice = bookPrice;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }
}
