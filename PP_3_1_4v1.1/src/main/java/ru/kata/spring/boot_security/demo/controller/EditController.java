//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.entity.User;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import java.util.Set;
//
//@RestController
//@RequestMapping("/users.html")
//public class EditController {
//
//    private final UserService userService;
//
//    @Autowired
//    public EditController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public Set<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userService.findById(id);
//    }
//
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User user) {
//        return userService.updateUser(id, user);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }
//}
//
