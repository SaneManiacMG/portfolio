package com.smworks.backendportfolio.service;

import com.smworks.backendportfolio.model.Login;
import com.smworks.backendportfolio.model.LoginRequest;
import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.LoginRepository;
import com.smworks.backendportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private LoginRequest loginRequest;
    String userId;
    String emailRegex = "^(.+)@(.+)$";
    String usernamePattern = "^[a-zA-Z0-9]*$";
    Optional<User> user;

    public Boolean userExists(String userIdentifier) {
        try {
            if (userRepository.findByUsername(userIdentifier).isPresent()) {
                user = userRepository.findByUsername(userIdentifier);
            } else if (userRepository.findByEmail(userIdentifier).isPresent()) {
                user = userRepository.findByEmail(userIdentifier);
            } else {
                return false;
            }
            userId = user.get().getUserId();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public ResponseEntity<Object> authenticate(LoginRequest request) {

        if (userExists(request.getUserIdentifier())) {
            Optional<Login> authUser = loginRepository.findById(userId);
            if (request.getPassword().equals(authUser.get().getPassword())) {
                return new ResponseEntity<>(authUser.get(), HttpStatus.OK);
            } else if (request.getPassword().equals(authUser.get().getPassword()) && !authUser.get().getActive()) {
                return new ResponseEntity<>("Account blocked", HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}

