package com.vishwam.safetyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vishwam.safetyapp.model.Hospital;
import com.vishwam.safetyapp.repository.HospitalRepository;

import java.util.Map;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepo;

    // Hospital login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String code = loginData.get("code");
        String password = loginData.get("password");

        Hospital hospital = hospitalRepo.findByCode(code);
        if (hospital != null && hospital.getPassword().equals(password)) {
            return ResponseEntity.ok(hospital);
        }
        return ResponseEntity.status(401).body("Invalid hospital credentials");
    }
}
