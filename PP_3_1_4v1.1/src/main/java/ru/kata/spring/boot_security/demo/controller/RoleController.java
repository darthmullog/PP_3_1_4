package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @GetMapping
    public ResponseEntity<Map<String, List<String>>> getRoles() {

        List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
        Map<String, List<String>> response = new HashMap<>();
        response.put("roles", roles);
        return ResponseEntity.ok(response);
    }
}