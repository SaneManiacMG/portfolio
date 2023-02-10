package com.smworks.backendportfolio.services;

import com.smworks.backendportfolio.models.Login;
import com.smworks.backendportfolio.models.LoginRequest;
import com.smworks.backendportfolio.models.LoginResponse;
import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.LoginRepository;
import com.smworks.backendportfolio.repositories.UserRepository;
import com.smworks.backendportfolio.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;
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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(loginUser.get().getPassword());
        System.out.println("\n" + loginUser.get().getPassword() + "\n" + encodedPassword + "\n");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.get().getUsername(), loginUser.get().getPassword()));
        System.out.println(authentication);
        Login login = (Login) authentication.getPrincipal();
        System.out.println(login.toString());
        String accessToken = jwtUtil.generateAccessToken(login);
        System.out.println(accessToken);

        if (loginUser.isPresent() && encodedPassword.equals(loginUser.get().getPassword())) {
            if (!loginUser.get().isEnabled()) {
                return new ResponseEntity<>("Account locked", HttpStatus.FORBIDDEN);
            } else {
                try {

                    LoginResponse loginResponse = new LoginResponse(login.getUsername(), accessToken);
                    return new ResponseEntity<>(loginResponse, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            return new ResponseEntity<>("Invalid username/password", HttpStatus.UNAUTHORIZED);
        }
    }
}