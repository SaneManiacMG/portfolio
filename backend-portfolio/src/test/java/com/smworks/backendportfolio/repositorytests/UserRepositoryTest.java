package com.smworks.backendportfolio.repositorytests;

import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.UserRepository;
import com.smworks.backendportfolio.services.SequenceGeneratorService;
import com.smworks.backendportfolio.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private UserService userService;
    private User newUser;

    @BeforeEach
    public void setup() {
        SequenceGeneratorService sequenceGeneratorService = new SequenceGeneratorService();
        newUser = new User(sequenceGeneratorService.generateUserId(), "TestUser1",
                "Test", "User",
                "testuser@email.com", "0123456789",
                "Test", true);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        newUser = null;
    }

    @Test
    public void userAccountCreated() {
        userRepository.save(newUser);
        User createdUser = userRepository.findByUsername(newUser.getUsername()).get();
        assertEquals(newUser.getUserId(), createdUser.getUserId());
    }

    @Test
    public void usernameAlreadyExists() {
        newUser.setUsername("dummy2user");
        Optional<User> existingUser = userRepository.findByUsername("dummy2user");
        assertEquals(existingUser.get().getUsername(), "dummy55user");
    }
}
