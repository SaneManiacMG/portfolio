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
    private User user;

    @Override
    public Boolean userDetailsExists(String userIdentifier) {
        if (userRepository.findByUsername(userIdentifier).isPresent()) {
            return true;
        } else if (userRepository.findByEmail(userIdentifier).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseEntity<Object> createNewUserLogin(@RequestBody RegisterRequest registerRequest) {
        if (userDetailsExists(registerRequest.getEmail()) && userDetailsExists(registerRequest.getUsername()) &&
                !registerRequest.getPassword().isEmpty()) {
            try {
                Optional<User> newUser = userRepository.findByUsername(registerRequest.getUsername());
                loginRepository.save(new Login(newUser.get().getUserId(), registerRequest.getPassword(),
                        true));
                Optional<Login> storedCreds = loginRepository.findById(newUser.get().getUserId());
                Login loginDetails = storedCreds.get();
                return new ResponseEntity<>(loginDetails, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else if (registerRequest.getEmail().isEmpty())
            return new ResponseEntity<>("Please email address", HttpStatus.BAD_REQUEST);
        else if (registerRequest.getUsername().isEmpty())
            return new ResponseEntity<>("Please provide username", HttpStatus.BAD_REQUEST);
        else if (registerRequest.getPassword().isEmpty())
            return new ResponseEntity<>("Please provide password", HttpStatus.BAD_REQUEST);
        else {
            return new ResponseEntity<>("Invalid User Details", HttpStatus.CONFLICT);
        }
    }
}
