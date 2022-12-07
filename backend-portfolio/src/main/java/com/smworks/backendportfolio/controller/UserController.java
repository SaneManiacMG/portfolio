package com.smworks.backendportfolio.controller;

import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.UserRepository;
import com.smworks.backendportfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
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

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public ResponseEntity<Object> findUserById(@RequestBody User user) {
        Optional<User> userOptional = userRepository.findById(user.getUserId());
        try {
            if (userOptional.isPresent()) {
                return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User ID not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred looking for user id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/findByEmail", method = RequestMethod.POST)
    public ResponseEntity<Object> findUserByEmail(@RequestBody User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        try {
            if (userOptional.isPresent()) {
                return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred looking for user email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        try {
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        return null;
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return null;
    }
}
