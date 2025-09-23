package com.vishwam.safetyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vishwam.safetyapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
