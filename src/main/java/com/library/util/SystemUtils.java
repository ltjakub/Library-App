package com.library.util;

import java.sql.*;

public class SystemUtils {
    public static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void connectToDatabase() {


        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Błąd podczas łączenia z bazą danych... " + e.getMessage());
            System.err.println("Zatrzymywanie programu...");
            System.exit(1);
        }

    }


}
