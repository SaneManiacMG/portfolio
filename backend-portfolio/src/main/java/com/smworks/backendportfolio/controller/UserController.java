package com.smworks.backendportfolio.controller;

import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<User>> findAllUsers() {
        return null;
    }

}
