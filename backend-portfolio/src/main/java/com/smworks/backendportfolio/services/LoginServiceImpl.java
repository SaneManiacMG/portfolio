package com.smworks.backendportfolio.services;

import com.smworks.backendportfolio.models.Login;
import com.smworks.backendportfolio.models.LoginRequest;
import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.LoginRepository;
import com.smworks.backendportfolio.repositories.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public ResponseEntity<Object> authenticate(LoginRequest request) {
        if (request.getPassword().isBlank() || request.getUserIdentifier().isBlank()) {
            return new ResponseEntity<>("Not all values provided", HttpStatus.BAD_REQUEST);
        }

        Optional<User> existingUserByEmail = userRepository.findByEmail(request.getUserIdentifier());
        Optional<User> existingUserByUsername = userRepository.findByUsername(request.getUserIdentifier());
        String userId;

        if (existingUserByEmail.isPresent()) {
            userId = existingUserByEmail.get().getUserId();
        } else if (existingUserByUsername.isPresent()) {
            userId = existingUserByUsername.get().getUserId();
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

        Optional<Login> loginUser = loginRepository.findById(userId);

        if (loginUser.get().getActive()) {
            Login loggedInUser = loginUser.get();
            if (loginUser.isPresent() && request.getPassword().equals(loginUser.get().getPassword())) {
                return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Account locked", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

}