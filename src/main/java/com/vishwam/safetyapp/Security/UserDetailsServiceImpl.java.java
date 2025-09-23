package com.vishwam.safetyapp.security.services;

import com.vishwam.safetyapp.model.Hospital;
import com.vishwam.safetyapp.model.User;
import com.vishwam.safetyapp.repository.HospitalRepository;
import com.vishwam.safetyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HospitalRepository hospitalRepository; // To load hospital details

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        // Try to load as a regular user (by email)
        Optional<User> userOptional = userRepository.findByEmail(identifier);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return UserDetailsImpl.build(user);
        }

        // If not a user, try to load as a hospital (by code)
        Optional<Hospital> hospitalOptional = hospitalRepository.findByCode(identifier);
        if (hospitalOptional.isPresent()) {
            Hospital hospital = hospitalOptional.get();
            return HospitalDetailsImpl.build(hospital); // You'll need to create HospitalDetailsImpl
        }

        throw new UsernameNotFoundException("User or Hospital not found with identifier: " + identifier);
    }
}