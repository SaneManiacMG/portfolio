package com.smworks.backendportfolio.servicetests;

import com.smworks.backendportfolio.models.Login;
import com.smworks.backendportfolio.models.RegisterRequest;
import com.smworks.backendportfolio.models.User;
import com.smworks.backendportfolio.repositories.LoginRepository;
import com.smworks.backendportfolio.repositories.UserRepository;
import com.smworks.backendportfolio.services.RegisterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RegisterServiceImplTest {
    User newUser;
    RegisterRequest newRegisterRequest;
    private Login login;
    @Mock
    private UserRepository userRepository;
    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private RegisterServiceImpl registerService;

    @BeforeEach
    public void setup() {
        newUser = new User("GeneratedSequence", "Dummy2User", "Dummy", "Two",
                "dummy-two@email.com", "0129982254", "TEST", true);
        userRepository.save(newUser);
    }

    @Test
    public void registerNewUserTest() {
        newRegisterRequest = new RegisterRequest(newUser.getUserId(), newUser.getUsername(), newUser.getEmail(),
                "123456789");
        when(userRepository.findByUsername(newRegisterRequest.getUsername())).thenReturn(Optional.of(newUser));
        when(userRepository.findByEmail(newRegisterRequest.getEmail())).thenReturn(Optional.of(newUser));
        when(loginRepository.save(new Login(newRegisterRequest.getUserId(), newRegisterRequest.getPassword(),
                true))).thenReturn(new Login(newRegisterRequest.getUserId(), newRegisterRequest.getPassword(),
                true));
        login = new Login(newUser.getUserId(), newRegisterRequest.getPassword(), true);
        when(loginRepository.save(login)).
                thenReturn(login);
        when(loginRepository.findById(newUser.getUserId())).thenReturn(Optional.of(login));
        ResponseEntity<Object> response = registerService.createNewUserLogin(newRegisterRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(login, response.getBody());
    }
}
