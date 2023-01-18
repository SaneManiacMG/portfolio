package com.smworks.backendportfolio.services;

import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public ResponseEntity<Object> findAllUsers() {
        try {
            if (!userRepository.findAll().isEmpty())
                return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
            else
                return new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> findByUserId(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUserId());
        try {
            if (userRepository.existsById(user.getUserId()))
                return new ResponseEntity<>(userOptional.get(), HttpStatus.FOUND);
            else
                return new ResponseEntity<>("User ID not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> findByUsername(User user) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent())
                return new ResponseEntity<>(userOptional.get(), HttpStatus.FOUND);
            else
                return new ResponseEntity<>("Username not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> findByEmail(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        try {
            if (userRepository.findByEmail(user.getEmail()).isPresent())
                return new ResponseEntity<>(userOptional.get(), HttpStatus.FOUND);
            else
                return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateUserDetails(User user) {
        try {
            if (userRepository.findById(user.getUserId()).isPresent()) {
                userRepository.save(new User(user.getUserId(), user.getUsername(), user.getFirstName(),
                        user.getLastName(), user.getEmail(), user.getPhoneNr(), user.getRole(), user.isActive()));
                return new ResponseEntity<>(userRepository.findById(user.getUserId()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Username or email already taken", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Object> deleteUserRecord(User user) {
        try {
            if (userRepository.existsById(user.getUserId())) {
                userRepository.deleteById(user.getUserId());
                return new ResponseEntity<>("User deleted", HttpStatus.OK);
            } else
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createUserRecord(User user) {
        try {
            if ((!userRepository.findByEmail(user.getEmail()).isPresent())
                    && (!userRepository.findByUsername(user.getUsername()).isPresent())) {
                userRepository.save(new User(sequenceGeneratorService.generateUserId(), user.getUsername(),
                        user.getFirstName(), user.getLastName(), user.getEmail(),
                        user.getPhoneNr(), user.getRole(), user.isActive()));
                return new ResponseEntity<>(userRepository.findByEmail(user.getEmail()), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Username or email is already taken", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
