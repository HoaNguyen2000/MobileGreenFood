package com.example.book;

public class Book {
    private String bookId;
    private String bookName;
    private String bookType;
    private int bookPrice;

    public Book() {
    }

    public Book(String bookId, String bookName, String bookType, int bookPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookType = bookType;
        this.bookPrice = bookPrice;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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
