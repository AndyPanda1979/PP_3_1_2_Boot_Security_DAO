package ru.kata.spring.boot_security.demo.dao.user;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User findUserById(Long id);
    User findUserByUsername(String username);
    List<User> findAllUsers();
    void saveUser(User user);
    void deleteUser(Long id);
}
