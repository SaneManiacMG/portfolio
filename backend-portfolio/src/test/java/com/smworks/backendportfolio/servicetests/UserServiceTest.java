package com.smworks.backendportfolio.servicetests;

import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.UserRepository;
import com.smworks.backendportfolio.services.SequenceGeneratorService;
import com.smworks.backendportfolio.services.UserService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;

    private User newUser = new User();
    private ResponseEntity<Object> listOfUsers;

    @BeforeEach
    public void setup() {
        SequenceGeneratorService sequenceGeneratorService = new SequenceGeneratorService();
        newUser = new User(sequenceGeneratorService.generateUserId(), "TestUser1",
                "Test", "User",
                "testuser@email.com", "0123456789",
                "Test", true);
        listOfUsers = new ResponseEntity<>(userService.getAllUsers().getStatusCode());
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        newUser = null;
    }

    @Test
    public void getAllUsersSuccessfully() {
        ResponseEntity<Object> listOfUsers = new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        String expectedResponse = HttpStatus.OK.toString() + userRepository.findAll().toString();
        String actualResponse = listOfUsers.getStatusCode().toString() + listOfUsers.getBody().toString();
        assertThat(expectedResponse.equals(actualResponse));
    }
}
