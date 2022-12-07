package com.smworks.backendportfolio.service;

import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ResponseEntity<Object> findByUserId(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUserId());
        try {
            if (userOptional.isPresent()) {
                return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User ID not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred performing user ID lookup",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> findByEmail(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        try {
            if (userOptional.isPresent()) {
                return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred looking for user email",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateUserDetails(User user) {
        return null;
    }

    public ResponseEntity<Object> deleteUserRecord(User user) {
        return null;
    }

    public ResponseEntity<Object> createUserRecord(User user) {
        return null;
    }
}
