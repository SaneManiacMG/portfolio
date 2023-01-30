package com.smworks.backendportfolio.controllers;

import com.smworks.backendportfolio.models.LoginRequest;
import com.smworks.backendportfolio.services.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUserIdentifier().isEmpty()) {
            return new ResponseEntity<>("No username or password provided", HttpStatus.BAD_REQUEST);
        } else if (loginRequest.getPassword().isEmpty()) {
            return new ResponseEntity<>("No username or password provided", HttpStatus.BAD_REQUEST);
        } else {
            return loginServiceImpl.authenticate(loginRequest);
        }
    }
}
