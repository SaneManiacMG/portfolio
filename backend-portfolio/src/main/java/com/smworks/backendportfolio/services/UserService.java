package com.smworks.backendportfolio.services;

import com.smworks.backendportfolio.models.User;

import org.springframework.http.ResponseEntity;


public interface UserService {
    public String generateUserId();
    public String generateDateTime();
    public int generateNumber();
    public ResponseEntity<Object> getAllUsers();
    public ResponseEntity<Object> findByUserId(User user);
    public ResponseEntity<Object> findByUsername(User user);
    public ResponseEntity<Object> findByEmail(User user);
    public ResponseEntity<Object> updateUserDetails(User user);
    public ResponseEntity<Object> deleteUserRecord(User user);
    public ResponseEntity<Object> createUserRecord(User user);
}
