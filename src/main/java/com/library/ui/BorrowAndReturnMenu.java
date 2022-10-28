package com.library.ui;

import com.library.book.BookService;

import java.util.Scanner;

public class BorrowAndReturnMenu {

    private BookService bookService;

    public BorrowAndReturnMenu(BookService bookService) {
        this.bookService = bookService;
    }

    public void menu(Scanner scanner) {
        printBorrowAndReturnMenuOptions();
        String usersChoice = scanner.nextLine();
        switch(usersChoice) {
            case "1" -> borrowAndReturn(scanner, "borrow");
            case "2" -> borrowAndReturn(scanner, "return");
            default -> System.out.println("Borrow and Return Exit Default");
        }
    }
    private void borrowAndReturn(Scanner scanner, String option) {
        System.out.println("Podaj ID użytkownika");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj ID ksiazki");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        if(option.equals("borrow")) {
            bookService.borrowBook(userId, bookId);
        } else if (option.equals("return")) {
            bookService.returnBook(userId, bookId);
        }
    }
    private static void printBorrowAndReturnMenuOptions() {
        System.out.println("1 - Wypożycz książkę");
        System.out.println("2 - Zwróć książkę");
    }
}
