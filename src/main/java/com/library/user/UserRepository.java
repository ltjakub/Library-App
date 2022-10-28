package com.library.user;

public interface UserRepository {
    void readAll();
    void createUser(User user);
    void deleteUserById(int userId);
    void findByUserLastName(String lastName);
    void inspectUsersBorrowedBooks(int userId);
}
