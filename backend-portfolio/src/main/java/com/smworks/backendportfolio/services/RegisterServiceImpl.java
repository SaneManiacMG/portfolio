package com.smworks.backendportfolio.services;

import com.smworks.backendportfolio.models.Login;
import com.smworks.backendportfolio.models.RegisterRequest;
import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.LoginRepository;
import com.smworks.backendportfolio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public ResponseEntity<Object> createNewUserLogin(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getUsername().isBlank() || registerRequest.getEmail().isBlank() ||
                registerRequest.getPassword().isBlank()) {
            return new ResponseEntity<>("Missing value/s", HttpStatus.NOT_ACCEPTABLE);
        }

        Optional<User> existingUserByEmail = userRepository.findByEmail(registerRequest.getEmail());
        Optional<User> existingUserByUsername = userRepository.findByUsername(registerRequest.getUsername());

        if (existingUserByUsername.isPresent() && existingUserByEmail.isPresent() &&
                existingUserByEmail.get().getUserId().equals(existingUserByUsername.get().getUserId())) {
            Login savedUser = new Login(existingUserByUsername.get().getUserId(), registerRequest.getPassword(),
                    true);
            try {
                loginRepository.save(savedUser);
                return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid user credentials", HttpStatus.UNAUTHORIZED);
        }
    }
}
