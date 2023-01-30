package com.smworks.backendportfolio.services;

import com.smworks.backendportfolio.models.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    public Boolean userExists(String userIdentifier);
    public ResponseEntity<Object> authenticate(LoginRequest request);
}
