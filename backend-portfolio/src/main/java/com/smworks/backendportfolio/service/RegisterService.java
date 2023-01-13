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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class RegisterService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;

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
        if (userDetailsExists(user.getEmail())) {
            return new ResponseEntity<>("Use unique email/username", HttpStatus.CONFLICT);
        } else if (userDetailsExists(user.getUsername()))
            return new ResponseEntity<>("Use unique email/username", HttpStatus.CONFLICT);
        else {
            try {
                userService.createUserRecord(user);
                Optional<User> newUser = userRepository.findByUsername(user.getUsername());
                //need to prompt user for password
                loginRepository.save(new Login(newUser.get().getUserId(), "DEFAULT", false));
                //userId = (newUser.get().getUserId());
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public ResponseEntity<Object> savePassword(LoginRequest request) {
        Optional<Login> loginCred;
        Optional<User> authUser;
        try {
            if (userRepository.findByUsername(request.getUserIdentifier()).isPresent()) {
                authUser = userRepository.findByUsername(request.getUserIdentifier());
            } else if (userRepository.findByEmail(request.getUserIdentifier()).isPresent()) {
                authUser = userRepository.findByEmail(request.getUserIdentifier());
            } else {
                return new ResponseEntity<>("Invalid login credentials", HttpStatus.UNAUTHORIZED);
            }
            loginCred = loginRepository.findById(authUser.get().getUserId());
            loginRepository.save(new Login(authUser.get().getUserId(), request.getPassword(), loginCred.get().getActive()));
            return new ResponseEntity<>(loginCred.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
