package com.library.user;

public class UserService {
        UserDatabaseRepository userDatabaseRepository;

    public UserService(UserDatabaseRepository userDatabaseRepository) {
        this.userDatabaseRepository = userDatabaseRepository;
    }

    public void readAll() {
        this.userDatabaseRepository.readAll();
    }
    public void createUser(String firstName, String lastName) {
        User user = new User(firstName, lastName);
        userDatabaseRepository.createUser(user);
    }

    public void deleteUserById(int userId) {
        userDatabaseRepository.deleteUserById(userId);
    }
    public void findByUserLastName(String lastName) {
        this.userDatabaseRepository.findByUserLastName(lastName);
    }
    public void inspectUsersBorrowedBooks(int userId) {
        this.userDatabaseRepository.inspectUsersBorrowedBooks(userId);
    }
}
