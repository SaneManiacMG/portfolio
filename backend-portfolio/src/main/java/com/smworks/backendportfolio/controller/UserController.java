package com.smworks.backendportfolio.controller;

import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("s")
    public ResponseEntity<Object> findAllUsers() {
        try {
            if (!userRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No users found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred looking for users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("-id/{userId}")
    public ResponseEntity<Object> findUserById(@PathVariable("userId") long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        try {
            if (userOptional.isPresent()) {
                return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User ID not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred looking for user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("-email/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable("email") String email) {
        return null; //use custom record lookup using email
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestParam Long userId, @RequestBody User user) {
        return null;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@RequestParam Long userId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return null;
    }
}
