package com.vishwam.safetyapp.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vishwam.safetyapp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private String emergencyEmail;
    private String emergencyPhone;
    private String bloodGroup;
    private String diseases;
    private String allergies;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String email, String password, String fullName, String phone,
                           String emergencyEmail, String emergencyPhone, String bloodGroup,
                           String diseases, String allergies,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.emergencyEmail = emergencyEmail;
        this.emergencyPhone = emergencyPhone;
        this.bloodGroup = bloodGroup;
        this.diseases = diseases;
        this.allergies = allergies;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFullName(),
                user.getPhone(),
                user.getEmergencyEmail(),
                user.getEmergencyPhone(),
                user.getBloodGroup(),
                user.getDiseases(),
                user.getAllergies(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getEmergencyEmail() { return emergencyEmail; }
    public String getEmergencyPhone() { return emergencyPhone; }
    public String getBloodGroup() { return bloodGroup; }
    public String getDiseases() { return diseases; }
    public String getAllergies() { return allergies; }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // Using email as username for users
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}