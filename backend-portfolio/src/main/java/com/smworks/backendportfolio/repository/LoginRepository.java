package com.smworks.backendportfolio.repository;

import com.smworks.backendportfolio.model.Login;
import com.smworks.backendportfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, String> {

}
