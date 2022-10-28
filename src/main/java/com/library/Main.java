package com.library;

import com.library.book.BookDatabaseRepository;
import com.library.book.BookService;
import com.library.ui.TextUi;
import com.library.user.UserDatabaseRepository;
import com.library.user.UserService;
import com.library.util.SystemUtils;
import org.w3c.dom.Text;

import javax.swing.plaf.TextUI;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Db connection
        SystemUtils.connectToDatabase();

        BookDatabaseRepository bookDatabaseRepository = new BookDatabaseRepository();
        BookService bookService = new BookService(bookDatabaseRepository);

        UserDatabaseRepository userDatabaseRepository = new UserDatabaseRepository();
        UserService userService = new UserService(userDatabaseRepository);


        TextUi textUi = new TextUi(bookService, userService);
        textUi.mainLoop();


    }


}
