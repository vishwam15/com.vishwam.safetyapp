package com.vishwam.safetyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vishwam.safetyapp.model.Emergency;
import java.util.List;

public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
    List<Emergency> findByStatus(String status);
    List<Emergency> findByUserId(Long userId); // To get emergencies for a specific user
}