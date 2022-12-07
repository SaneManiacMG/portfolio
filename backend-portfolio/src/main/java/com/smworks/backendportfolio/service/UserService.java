package com.smworks.backendportfolio.service;

import com.smworks.backendportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
