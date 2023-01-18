package com.smworks.backendportfolio.controllers;

import com.smworks.backendportfolio.models.LoginRequest;
import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.services.RegisterService;
import com.smworks.backendportfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserService userService;

    @PostMapping("/newUser")
    public ResponseEntity<Object> registerNewUser(@RequestBody User user) {
        if (user.getEmail().isEmpty()) {
            return new ResponseEntity<>("Missing email", HttpStatus.BAD_REQUEST);
        } else if (user.getUsername().isEmpty()) {
            return new ResponseEntity<>("Missing username", HttpStatus.BAD_REQUEST);
        } else {
            return registerService.createNewUser(user);
        }
    }

    @PostMapping("/setPassword")
    public ResponseEntity<Object> setPasswordForAccount(@RequestBody LoginRequest request) {
        if (registerService.userDetailsExists(request.getUserIdentifier())) {
            return registerService.savePassword(request);
        } else {
            return new ResponseEntity<>("Invalid user details", HttpStatus.UNAUTHORIZED);
        }
    }
}
