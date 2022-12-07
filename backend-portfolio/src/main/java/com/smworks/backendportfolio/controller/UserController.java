package com.smworks.backendportfolio.controller;

import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.UserRepository;
import com.smworks.backendportfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = ("/getAllUsers"), method = RequestMethod.GET)
    public ResponseEntity<Object> findAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public ResponseEntity<Object> findUserById(@RequestBody User user) {
        return userService.findByUserId(user);
    }

    @RequestMapping(value = "/findByEmail", method = RequestMethod.POST)
    public ResponseEntity<Object> findUserByEmail(@RequestBody User user) {
        return userService.findByEmail(user);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        return null;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<Object> deleteUser(@RequestBody User user) {
        return null;
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return userService.createUserRecord(user);
    }
}
