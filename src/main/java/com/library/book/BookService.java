package com.library.book;

public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addNewBook(String title, String author, int pages, int quantity) {
        Book book = new Book(title, author, pages, quantity);
        this.bookRepository.addNewBook(book);
    }

    public void readAll() {
        this.bookRepository.readAll();
    }

    public void removeBookById(int id) {
        this.bookRepository.removeBookById(id);
    }

    public void borrowBook(int userId, int bookId) {
        this.bookRepository.borrowBook(userId, bookId);
    }
    public void returnBook(int userId, int bookId) {
        this.bookRepository.returnBook(userId, bookId);
    }

}
