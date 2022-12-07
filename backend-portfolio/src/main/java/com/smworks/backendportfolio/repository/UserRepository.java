package com.smworks.backendportfolio.repository;

import com.smworks.backendportfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    String updateUsingId = "UPDATE Users u SET " +
            "u.first_name = ?1, " +
            "u.last_name = ?2," +
            "u.email = ?3," +
            "u.phone_nr = ?4," +
            "u.role = ?5," +
            "u.active = ?6" +
            "where user_id = ?7";
    String updateUsingEmail = "UPDATE Users u SET " +
            "u.email = ?1," +
            "u.first_name = ?2, " +
            "u.last_name = ?3," +
            "u.email = ?4," +
            "u.phone_nr = ?5," +
            "u.role = ?6," +
            "u.active = ?7," +
            "where email = ?8";
    @Modifying(clearAutomatically = true)
    @Query(updateUsingId)
    void updateUserById(String firstName, String lastName, String email, String phoneNr, String role, boolean active,
                        Long userId);

    @Modifying(clearAutomatically = true)
    @Query(updateUsingEmail)
    void updateUserByEmail(Long userId, String firstName, String lastName, String phoneNr, String role, boolean active,
                           String email);
}
