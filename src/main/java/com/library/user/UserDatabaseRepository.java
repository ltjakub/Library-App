package com.library.user;

import com.library.ui.ConsoleColors;
import com.library.util.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDatabaseRepository implements UserRepository {

    @Override
    public void readAll() {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String completeQuery = "SELECT * FROM user";
            ResultSet resultSet = statement.executeQuery(completeQuery);
            printResult(resultSet);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createUser(User user) {

        try {
            Statement statement = SystemUtils.connection.createStatement();
            String queryString = "INSERT INTO user (first_name, last_name) VALUES('%s', '%s')";
            String completeQuery = String.format(queryString, user.getFirstName(), user.getLastName());
            statement.executeUpdate(completeQuery);
            System.out.println(ConsoleColors.YELLOW + "Utworzono użytkownika pomyślnie." + ConsoleColors.RESET);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteUserById(int userId) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String queryString = "DELETE FROM user WHERE user_id = '%d'";
            String completeQuery = String.format(queryString, userId);
            statement.executeUpdate(completeQuery);
            System.out.println(ConsoleColors.YELLOW + "Usunięto użytkownika pomyślnie." + ConsoleColors.RESET);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findByUserLastName(String lastName) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String stringQuery = "SELECT * FROM user WHERE last_name = '%s'";
            String completeQuery = String.format(stringQuery, lastName);
            ResultSet resultSet = statement.executeQuery(completeQuery);
            printResult(resultSet);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void inspectUsersBorrowedBooks(int userId) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String stringQuery = "SELECT * FROM borrowed_books WHERE user_id = '%d'";
            String completeQuery = String.format(stringQuery, userId);
            ResultSet resultSet = statement.executeQuery(completeQuery);
            while (resultSet.next()) {
                System.out.print(ConsoleColors.YELLOW + resultSet.getString(1) + " | ");
                System.out.print(resultSet.getString(2) + " | ");
                System.out.print(resultSet.getString(3) + " | ");
                System.out.print(resultSet.getString(4) + " | ");
                System.out.print(resultSet.getString(5) + ConsoleColors.RESET);
                System.out.println();
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void printResult(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.print(ConsoleColors.YELLOW + resultSet.getString(1) + " | ");
            System.out.print(resultSet.getString(2) + " | ");
            System.out.print(resultSet.getString(3) + ConsoleColors.RESET);
            System.out.println();
        }
    }

}
