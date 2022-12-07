package com.smworks.backendportfolio.controller;

import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> findAllUsers() {
        try {
            if (!userRepository.findAll().isEmpty())
                return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
            else
                return new ResponseEntity<>("No users found", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("An error has occurred retrieving result set",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public ResponseEntity<Object> findUserById(@RequestBody User user) {
        return null;
    }

    @RequestMapping(value = "/findByEmail", method = RequestMethod.POST)
    public ResponseEntity<Object> findUserByEmail(@RequestBody User user) {
        return null;
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
        return null;
    }
}
