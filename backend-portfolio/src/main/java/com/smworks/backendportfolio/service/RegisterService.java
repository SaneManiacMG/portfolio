package com.smworks.backendportfolio.service;

import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public Boolean userDetailsExists(String userIdentifier) {
        if (userRepository.findByUsername(userIdentifier).isPresent()) {
            return true;
        } else if (userRepository.findByEmail(userIdentifier).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public ResponseEntity<Object> createNewUser(@RequestBody User user) {
        if (!userDetailsExists(user.getEmail()) && !userDetailsExists(user.getUsername())) {
            return new ResponseEntity<>(user.toString(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Use unique email/username", HttpStatus.CONFLICT);
        }
    }
}
