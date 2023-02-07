package com.smworks.backendportfolio.services;

import com.smworks.backendportfolio.models.Login;
import com.smworks.backendportfolio.models.LoginRequest;
import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.LoginRepository;
import com.smworks.backendportfolio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;

    private String userId;

    @Override
    public ResponseEntity<Object> authenticate(LoginRequest request) {
        if (request.getPassword().isBlank() || request.getUserIdentifier().isBlank()) {
            return new ResponseEntity<>("Missing value/s", HttpStatus.NOT_ACCEPTABLE);
        }

        Optional<User> existingUserByEmail = userRepository.findByEmail(request.getUserIdentifier());
        Optional<User> existingUserByUsername = userRepository.findByUsername(request.getUserIdentifier());

        if (existingUserByEmail.isPresent()) {
            userId = existingUserByEmail.get().getUserId();
        } else if (existingUserByUsername.isPresent()) {
            userId = existingUserByUsername.get().getUserId();
        } else {
            return new ResponseEntity<>("Invalid username/password", HttpStatus.UNAUTHORIZED);
        }

        Optional<Login> loginUser = loginRepository.findById(userId);
        Login loggedInUser = loginUser.get();

        if (loginUser.isPresent() && request.getPassword().equals(loginUser.get().getPassword())) {
            if (!loginUser.get().getActive()) {
                return new ResponseEntity<>("Account locked", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(loadUserByUsername(loggedInUser.getUserId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username/password", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Login> loginCreds = loginRepository.findById(userId);
        Login loginUser = loginCreds.get();
        if(loginCreds.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username/password");
        }
        return new org.springframework.security.core.userdetails.User(loginUser.getUserId(), loginUser.getPassword(),
                new ArrayList<>());
    }
}