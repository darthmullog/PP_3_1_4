package ru.kata.spring.boot_security.demo.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, EntityManager entityManager, RoleRepository roleRepository, RoleRepository roleRepository1) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository1;
    }

    @Transactional
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            Role existingRole = roleRepository.findByName(role.getName());
            if (existingRole == null) { // если роль еще не сохранена
                existingRole = roleRepository.save(role);
            }
            roles.add(existingRole);
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long userId, User updatedUserDetails) {
        User existingUser = userRepository.findById(userId).get();
        existingUser.setUsername(updatedUserDetails.getUsername());
        existingUser.setEmail(updatedUserDetails.getEmail());
        existingUser.setPassword(updatedUserDetails.getPassword());
        Set<Role> updatedRoles = new HashSet<>();
        for (Role role : updatedUserDetails.getRoles()) {
            Role existingRole = roleRepository.findByName(role.getName());
            updatedRoles.add(existingRole);
        }
        existingUser.setRoles(updatedRoles);

        return userRepository.save(existingUser);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}

