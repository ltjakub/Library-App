package com.library.ui;

import com.library.book.BookService;

import java.util.Scanner;

public class LibraryMenu {
    private BookService bookService;

    public LibraryMenu(BookService bookService) {
        this.bookService = bookService;
    }

    public void menu(Scanner scanner) {
        printLibraryMenuOptions();
        String usersChoice = scanner.nextLine();
        switch (usersChoice) {
            case "1" -> addBook(scanner);
            case "2" -> deleteBook(scanner);
            case "3" -> readAllBooks();
            default -> System.out.println("Powrót do menu głównego...");
        }
    }

    private void readAllBooks() {
        bookService.readAll();
    }

    private void deleteBook(Scanner scanner) {
        System.out.println("Podaj ID książki, którą chcesz usunąć:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        bookService.removeBookById(bookId);
    }

    private void addBook(Scanner scanner) {
        System.out.println("Podaj tytuł:");
        String title = scanner.nextLine();
        System.out.println("Podaj authora:");
        String author = scanner.nextLine();
        System.out.println("Podaj ilość stron:");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj ilość książek (1-100):");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        bookService.addNewBook(title, author, pages, quantity);
    }

    private void printLibraryMenuOptions() {
        System.out.println("1 - Dodaj książkę");
        System.out.println("2 - Usuń książkę");
        System.out.println("3 - Wyświetl wszystkie książki");
    }
}
