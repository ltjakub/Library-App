package com.library.book;

import com.library.ui.ConsoleColors;
import com.library.util.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class BookDatabaseRepository implements BookRepository {

    @Override
    public void addNewBook(Book book) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            if (checkIfBookAlreadyExists(book.getTitle(), statement)) {
                String queryString = "UPDATE book SET quantity = quantity + %d WHERE title = '%s'";
                String completeQuery = String.format(queryString, book.getQuantity(), book.getTitle());
                statement.executeUpdate(completeQuery);
                System.out.println(ConsoleColors.YELLOW + "Zaktualizowano ilość książek." + ConsoleColors.RESET);
            } else {
                String queryString = "INSERT INTO book (title, author, pages, quantity) VALUES ('%s', '%s', %d, %d)";
                String completeQuery = String.format(queryString, book.getTitle(), book.getAuthor(), book.getPages(), book.getQuantity());
                statement.executeUpdate(completeQuery);
                System.out.println(ConsoleColors.YELLOW + "Dodano nową książkę." + ConsoleColors.RESET);
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean checkIfBookAlreadyExists(String title, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
        while (resultSet.next()) {
            if (resultSet.getString("title").equalsIgnoreCase(title))
                return true;
        }
        return false;
    }

    @Override
    public void readAll() {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String completeQuery = "SELECT * FROM book";
            ResultSet resultSet = statement.executeQuery(completeQuery);
            printResult(resultSet);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void printResult(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.print(ConsoleColors.YELLOW + resultSet.getString("book_id") + " | ");
            System.out.print(resultSet.getString("title") + " | ");
            System.out.print(resultSet.getString("author") + " | ");
            System.out.print(resultSet.getString("pages") + " | ");
            System.out.print(resultSet.getString("quantity") + " | " + ConsoleColors.RESET);
            System.out.println();
        }
    }

    @Override
    public void removeBookById(int id) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String queryString = "DELETE FROM book WHERE book_id = '%d'";
            String completeQuery = String.format(queryString, id);
            statement.executeUpdate(completeQuery);
            System.out.println(ConsoleColors.YELLOW + "Usunięto książkę." + ConsoleColors.RESET);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void borrowBook(int userId, int bookId) {
        if (isBookOnStock(bookId)) {

            try {
                Statement statement = SystemUtils.connection.createStatement();
                String queryString =
                        "INSERT INTO borrowed_books (user_id, first_name, last_name, book_id, title)\n" +
                                "VALUES (\n" +
                                " %d,\n" +
                                " (SELECT user.first_name\n" +
                                " FROM user\n" +
                                " WHERE user.user_id = %d),\n" +
                                " (SELECT user.last_name\n" +
                                " FROM user\n" +
                                " WHERE user.user_id = %d),\n" +
                                " %d,\n" +
                                " (SELECT title\n" +
                                "FROM book\n" +
                                "WHERE book_id = %d));";
                String completeQuery = String.format(queryString, userId, userId, userId, bookId, bookId);
                statement.executeUpdate(completeQuery);
                System.out.println(ConsoleColors.YELLOW + "Książka wypożyczona pomyślnie." + ConsoleColors.RESET);
                statement.close();
            } catch (SQLIntegrityConstraintViolationException e) {
                System.err.println("Użytkownik nie istnieje lub ma juz wypożyczoną daną książkę.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.err.println("Wszystkie ksiażki zostały wpożyczone.");
        }
    }

    private boolean isBookOnStock(int bookId) {
        int quantity = checkQuantity(bookId);
        int borrowedBooks = countBorrowedBooks(bookId);
        return quantity > borrowedBooks;
    }

    private int checkQuantity(int bookId) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String StringQuery = "SELECT quantity FROM book WHERE book_id = %d";
            String completeQuery = String.format(StringQuery, bookId);
            ResultSet resultSet = statement.executeQuery(completeQuery);
            if (resultSet.next()) {
                return (int) resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private int countBorrowedBooks(int bookId) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String queryString =
                    "SELECT COUNT(user_id)\n" +
                            "FROM borrowed_books\n" +
                            "WHERE book_id = %d;";
            String completeQuery = String.format(queryString, bookId);
            ResultSet resultSet = statement.executeQuery(completeQuery);
            if (resultSet.next()) {
                return (int) resultSet.getLong(1);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    @Override
    public void returnBook(int userId, int bookId) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String queryString =
                    "DELETE FROM borrowed_books\n" +
                            "WHERE user_id = %d AND book_id = %d;";
            String completeQuery = String.format(queryString, userId, bookId);
            statement.executeUpdate(completeQuery);
            System.out.println(ConsoleColors.YELLOW + "Pomyślnie zwrócono książkę." + ConsoleColors.RESET);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
