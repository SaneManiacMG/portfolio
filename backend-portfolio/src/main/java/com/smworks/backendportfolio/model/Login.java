package com.smworks.backendportfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logins")
public class Login {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean active;

    public Login() {
    }

    public Login(String userId, String password, Boolean active) {
        this.userId = userId;
        this.password = password;
        this.active = active;
    }

    public String getUserId() {
        return userId;
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
