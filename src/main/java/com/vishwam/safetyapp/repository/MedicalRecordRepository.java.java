package com.vishwam.safetyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vishwam.safetyapp.model.MedicalRecord;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByUserId(Long userId);
}