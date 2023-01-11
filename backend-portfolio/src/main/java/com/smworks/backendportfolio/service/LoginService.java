package com.smworks.backendportfolio.service;

import com.smworks.backendportfolio.model.Login;
import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.LoginRepository;
import com.smworks.backendportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;
    private String userId;
    private static Pattern username = Pattern.compile("^[a-zA-Z0-9]*$");

    public ResponseEntity<Object> userExists(String userIdentifier) {
        if (userIdentifier.isEmpty()) {
            return new ResponseEntity<>("No username or email provided", HttpStatus.BAD_REQUEST);
        }
        else if (username.matcher(userIdentifier).find()) {
            Optional<User> optionalUser = userRepository.findByUsername(userIdentifier);
            userId = optionalUser.get().getUserId();
        } else {
            Optional<User> optionalUser = userRepository.findByEmail(userIdentifier);
            userId = optionalUser.get().getUserId();
        }
        return null;
    }

    public ResponseEntity<Object> authenticate (String userId) {
        Optional<Login> user = loginRepository.findById(userId);
        return null;
    }

}
