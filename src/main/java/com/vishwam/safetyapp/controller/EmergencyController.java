package com.vishwam.safetyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vishwam.safetyapp.model.Emergency;
import com.vishwam.safetyapp.repository.EmergencyRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/emergency")
public class EmergencyController {

    @Autowired
    private EmergencyRepository emergencyRepo;

    // Raise emergency
    @PostMapping("/raise")
    public ResponseEntity<Emergency> raiseEmergency(@RequestBody Emergency emergency) {
        emergency.setTimestamp(LocalDateTime.now());
        emergency.setStatus("ACTIVE");
        return ResponseEntity.ok(emergencyRepo.save(emergency));
    }

    // Get all emergencies
    @GetMapping("/all")
    public List<Emergency> getAllEmergencies() {
        return emergencyRepo.findAll();
    }
}
