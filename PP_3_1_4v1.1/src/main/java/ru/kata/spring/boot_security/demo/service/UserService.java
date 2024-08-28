package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Set;

public interface UserService {
    User findById(Long id);

    User saveUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    Set<User> getAllUsers();

    User findByUsername(String username);
}
