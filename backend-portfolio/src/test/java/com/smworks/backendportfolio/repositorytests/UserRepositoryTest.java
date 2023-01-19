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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User newUser;
    private String existingUsername = "dummy2user";
    private String existingEmail = "dummy2@email.com";

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
        assertThat(newUser.getUserId(), is(createdUser.getUserId()));
    }

    @Test
    public void findUserByUsername() {
        Optional<User> foundUser = userRepository.findByUsername(existingUsername);
        assertEquals( true, foundUser.isPresent());
    }

    @Test
    public void findUserByEmail() {
        Optional<User> foundUser = userRepository.findByEmail(existingEmail);
        assertEquals( true, foundUser.isPresent());
    }

    @Test
    public void updateUser() {
        Optional<User> foundUser = userRepository.findByUsername(existingUsername);
        User updatedUser = new User(foundUser.get().getUserId(), "Dummy2User", "Dummy", "Two",
                "dummy-two@email.com", "0129982254", "TEST", true);
        userRepository.save(updatedUser);
        assertEquals(updatedUser.getUsername(), userRepository.findByUsername("Dummy2User").get().getUsername());
        assertEquals(updatedUser.getEmail(), userRepository.findByEmail("dummy-two@email.com").get().getEmail());
        assertEquals(foundUser.get().getUserId(), updatedUser.getUserId());
    }

}
