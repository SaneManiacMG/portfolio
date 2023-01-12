package com.smworks.backendportfolio.controller;

import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.service.RegisterService;
import com.smworks.backendportfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<Object> registerNewUser(@RequestBody User user) {
        if (user.getEmail().isEmpty()) {
            return new ResponseEntity<>("Missing email", HttpStatus.BAD_REQUEST);
        } else if (user.getUsername().isEmpty()) {
            return new ResponseEntity<>("Missing username", HttpStatus.BAD_REQUEST);
        } else {
            return registerService.createNewUser(user);
        }
    }
}
