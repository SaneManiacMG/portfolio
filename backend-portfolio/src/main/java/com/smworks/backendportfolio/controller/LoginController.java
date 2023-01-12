package com.smworks.backendportfolio.controller;

import com.smworks.backendportfolio.model.LoginRequest;
import com.smworks.backendportfolio.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getPassword() + loginRequest.getUserIdentifier() + "1");
        if (loginRequest.getUserIdentifier().isEmpty()) {
            System.out.println(loginRequest.getPassword() + loginRequest.getUserIdentifier() + "2");
            return new ResponseEntity<>("No username or password provided", HttpStatus.BAD_REQUEST);
        } else if (loginRequest.getPassword().isEmpty()) {
            System.out.println(loginRequest.getPassword() + loginRequest.getUserIdentifier() + "3");
            return new ResponseEntity<>("No username or password provided", HttpStatus.BAD_REQUEST);
        } else {
            return loginService.authenticate(loginRequest);
        }
    }
}
