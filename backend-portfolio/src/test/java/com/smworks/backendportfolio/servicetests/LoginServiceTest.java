package com.smworks.backendportfolio.servicetests;

import com.smworks.backendportfolio.models.Login;
import com.smworks.backendportfolio.models.LoginRequest;
import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.LoginRepository;
import com.smworks.backendportfolio.repositories.UserRepository;
import com.smworks.backendportfolio.services.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoginServiceTest {
    @Mock
    private LoginRepository loginRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginServiceImpl loginService;
    private User user;
    private Login login;
    private LoginRequest request1, request2;

    @BeforeEach
    public void setup() {
        user = new User("GeneratedSequence", "Dummy2User", "Dummy", "Two",
                "dummy-two@email.com", "0129982254", "TEST", true);
        userRepository.save(user);
        login = new Login("GeneratedSequence", "123456789", true);
        loginRepository.save(login);
    }

    @Test
    public void authenticateTest() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(loginRepository.findById(user.getUserId())).thenReturn(Optional.of(login));
        request1 = new LoginRequest(user.getUsername(), login.getPassword());
        request2 = new LoginRequest(user.getEmail(), login.getPassword());
        ResponseEntity<Object> response1 = loginService.authenticate(request1);
        ResponseEntity<Object> response2 = loginService.authenticate(request2);
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(HttpStatus.OK, response2.getStatusCode());

    }
}
