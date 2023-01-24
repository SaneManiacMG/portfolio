package com.smworks.backendportfolio.servicetests;

import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.UserRepository;
import com.smworks.backendportfolio.services.SequenceGeneratorService;
import com.smworks.backendportfolio.services.UserService;
import com.smworks.backendportfolio.services.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    private SequenceGeneratorService sequenceGeneratorService = new SequenceGeneratorService();
    User user1;
    User user2;
    User user3;
    List<User> users = new ArrayList<>();
    private Optional<User> optionalUser;

    @BeforeEach
    public void setupData() {
        user1 = new User(sequenceGeneratorService.generateUserId(), "SaneManiacMG",
                "Mogomotsi", "Moroane",
                "mmoroane@hotmail.com", "0813916607", "ADMIN",true);
        user2 = new User(sequenceGeneratorService.generateUserId(), "dummy1admin",
                "dummy1", "admin",
                "dummy1@email.com", "0123456789", "ADMIN",true);
        user3 = new User(sequenceGeneratorService.generateUserId(), "TestAcc",
                "Test", "Acc",
                "test@email.com", "0123456789", "TEST",true);
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        user2 = null;
        user3 = null;
    }

    @Test
    public void testFindAllUsers() {
        when(userRepository.findAll()).thenReturn(users);
        ResponseEntity<Object> response = userServiceImpl.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }


}
