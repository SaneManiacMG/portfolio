package com.smworks.backendportfolio.services;

import com.smworks.backendportfolio.models.Login;
import com.smworks.backendportfolio.models.LoginRequest;
import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.LoginRepository;
import com.smworks.backendportfolio.repositories.UserRepository;
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

    String userId;
    Optional<User> user;

    @Override
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
    @Override
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