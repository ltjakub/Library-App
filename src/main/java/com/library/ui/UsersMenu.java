package com.library.ui;

import com.library.user.UserService;

import java.util.Scanner;

public class UsersMenu {
    private UserService userService;

    public UsersMenu(UserService userService) {
        this.userService = userService;
    }

    public void menu(Scanner scanner) {
        printUsersMenuOptions();
        String usersChoice = scanner.nextLine();
        switch(usersChoice) {
            case "1" -> createUser(scanner);
            case "2" -> deleteUser(scanner);
            case "3" -> readAllUsers();
            case "4" -> findUserByLastName(scanner);
            case "5" -> inspectUsersBorrowedBooks(scanner);
            default -> System.out.println("Powrót do menu głównego...");
        }
    }

    private void inspectUsersBorrowedBooks(Scanner scanner) {
        System.out.println("Podaj ID użytkownika, którego chcesz sprawdzić:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        userService.inspectUsersBorrowedBooks(userId);
    }

    private void findUserByLastName(Scanner scanner) {
        System.out.println("Podaj nazwisko użytkownika, którego chcesz znaleźć");
        String lastName = scanner.nextLine();
        userService.findByUserLastName(lastName);
    }
    private void readAllUsers() {
        userService.readAll();
    }
    private void deleteUser(Scanner scanner) {
        System.out.println("Podaj ID użytkownika, którego chcesz usunąć:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        userService.deleteUserById(userId);
    }

    private void createUser(Scanner scanner) {
        System.out.println("Podaj imię:");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko:");
        String lastName = scanner.nextLine();
        userService.createUser(firstName, lastName);
    }

    private void printUsersMenuOptions() {
        System.out.println("1 - Stwórz użytkownika");
        System.out.println("2 - Usuń użytkownika");
        System.out.println("3 - Wyświetl wszystkich użytkowników");
        System.out.println("4 - Znajdź użytkownika po nazwisku");
        System.out.println("5 - Sprawdź wypożyczone książki użytkownika");
    }
}
