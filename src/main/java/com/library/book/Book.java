package com.library.book;

import java.sql.Date;

public class Book {

    private String title;
    private String author;
    private int pages;
    private int quantity;

    public Book(String title, String author, int pages, int quantity) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }


    public int getPages() { return pages; }

    public int getQuantity() {
        return quantity;
    }
}
