package com.smworks.backendportfolio.service;

import com.smworks.backendportfolio.model.User;
import com.smworks.backendportfolio.repository.UserRepository;
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
        /*try {
            if (userRepository.existsById(user.getUserId()) &&
                    !userRepository.findByEmail(user.getEmail()).isPresent()) {
                //then we are updating the email and other properties
                Optional<User> userOptional = userRepository.findById(user.getUserId());
                userRepository.deleteById(user.getUserId());
                userRepository.save(user);
                return new ResponseEntity<>("Updated using user ID as identifier\n\n" +
                        userOptional.get(), HttpStatus.OK);
            } else if (userRepository.findByEmail(user.getEmail()).isPresent() &&
                    !userRepository.findById(user.getUserId()).isPresent()) {
                //we are updating the id and other properties
                Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
                userRepository.deleteByEmail(user.getEmail());
                userRepository.save(user);
                return new ResponseEntity<>("Updated using user email as identifier\n\n" +
                        userOptional.get(), HttpStatus.OK);
            } else
                return new ResponseEntity<>("Could not find user id or email", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
        try {
            if ((userRepository.findByEmail(user.getEmail()).isPresent())
                    || (userRepository.existsById(user.getUserId()))) {
                userRepository.save(new User(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                        user.getPhoneNr(), user.getRole(), user.isActive()));
                return new ResponseEntity<>(userRepository.findById(user.getUserId()), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("User not found",
                        HttpStatus.NOT_FOUND);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email already taken", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Not allowed to change user ID", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Object> deleteUserRecord(User user) {
        try {
            if (userRepository.existsById(user.getUserId())) {
                userRepository.deleteById(user.getUserId());
                return new ResponseEntity<>("User deleted", HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createUserRecord(User user) {
        try {
            if ((!userRepository.findByEmail(user.getEmail()).isPresent())
                    && (!userRepository.existsById(user.getUserId()))) {
                userRepository.save(new User(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                        user.getPhoneNr(), user.getRole(), user.isActive()));
                return new ResponseEntity<>(userRepository.findById(user.getUserId()), HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("User ID or email is already taken",
                        HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
