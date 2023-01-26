package com.smworks.backendportfolio.servicetests;

import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.UserRepository;
import com.smworks.backendportfolio.services.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class RegisterServiceTest {
    User newUser;
    @Mock
    private UserRepository userRepository;

    @Autowired
    private RegisterService registerService;

    @BeforeEach
    public void setup() {
        newUser = new User("GeneratedSequence", "Dummy2User", "Dummy", "Two",
                "dummy-two@email.com", "0129982254", "TEST", true);
    }

    @Test
    public void registerNewUserTest() {

    }
}
