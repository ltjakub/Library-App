package com.library.ui;

import com.library.book.BookService;
import com.library.user.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;


public class TextUi {
    private BookService bookService;
    private UserService userService;

    public TextUi(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    private boolean exit = false;

    public void mainLoop() {
        System.out.println("Witaj w panelu zarządzania biblioteką.");
        while (!exit) {
            printMainMenuOptions();
            getUserIntentions();
        }

    }

    private void printMainMenuOptions() {
        System.out.println("Wybierz, co chcesz zrobić:");
        System.out.println("1 - Wypozyczanie i zwrot książek.");
        System.out.println("2 - Opcje użytkowników.");
        System.out.println("3 - Opcje biblioteki.");
        System.out.println("Kliknij Enter aby wyjść z aplikacji.");
    }

    private void getUserIntentions() {
        try {
            Scanner scanner = new Scanner(System.in);
            String usersChoice = scanner.nextLine();
            switch (usersChoice) {
                case "1" -> {
                    BorrowAndReturnMenu borrowMenu = new BorrowAndReturnMenu(bookService);
                    borrowMenu.menu(scanner);
                }
                case "2" -> {
                    UsersMenu usersMenu = new UsersMenu(userService);
                    usersMenu.menu(scanner);
                }
                case "3" -> {
                    LibraryMenu libraryMenu = new LibraryMenu(bookService);
                    libraryMenu.menu(scanner);
                }
                default -> setExitToTrue();
            }
        } catch (InputMismatchException e) {
            System.err.println("Podano zly znak.");
        }
    }


    private void setExitToTrue() {
        System.out.println("Do widzenia!");
        this.exit = true;
    }
}
