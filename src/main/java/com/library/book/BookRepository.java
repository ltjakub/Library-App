package com.library.book;


public interface BookRepository {

    void addNewBook(Book book);
    void readAll();
    void removeBookById(int id);
    void borrowBook(int userId, int bookId);
    void returnBook(int userId, int bookId);
}
