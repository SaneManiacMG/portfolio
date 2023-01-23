package com.smworks.backendportfolio.servicetests;

import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.UserRepository;
import com.smworks.backendportfolio.services.SequenceGeneratorService;
import com.smworks.backendportfolio.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    User user1;
    User user2;
    User user3;

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

    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        user2 = null;
        user3 = null;
    }

    @Test
    public void testFindAllUsers() {

    }
}
