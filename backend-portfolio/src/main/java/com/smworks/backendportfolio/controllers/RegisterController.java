package com.smworks.backendportfolio.controllers;

import com.smworks.backendportfolio.models.RegisterRequest;
import com.smworks.backendportfolio.services.RegisterServiceImpl;
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
    private RegisterServiceImpl registerServiceImpl;
    @Autowired
    private UserService userService;

    @PostMapping("/newUser")
    public ResponseEntity<Object> registerNewUser(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getEmail().isEmpty()) {
            return new ResponseEntity<>("Missing email", HttpStatus.BAD_REQUEST);
        } else if (registerRequest.getUsername().isEmpty()) {
            return new ResponseEntity<>("Missing username", HttpStatus.BAD_REQUEST);
        } else {
            return registerServiceImpl.createNewUserLogin(registerRequest);
        }
    }
}
