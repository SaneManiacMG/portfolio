package com.smworks.backendportfolio.repository;

import com.smworks.backendportfolio.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {

}
