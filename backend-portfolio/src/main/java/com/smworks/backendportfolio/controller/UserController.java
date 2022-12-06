package com.smworks.backendportfolio.controller;

import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@RequestParam Long userId) {
        return null;
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
        return null;
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestParam Long userId, @RequestBody User user) {
        return null;
    }

    @DeleteMapping("/{userId")
    public ResponseEntity<User> deleteUser(@RequestParam Long userId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return null;
    }
}
