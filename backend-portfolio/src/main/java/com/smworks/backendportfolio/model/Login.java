package com.smworks.backendportfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private Boolean active;

    public Login() {
    }

    public Login(String userId, String username, String email, String password, Boolean active) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public Login(String userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
