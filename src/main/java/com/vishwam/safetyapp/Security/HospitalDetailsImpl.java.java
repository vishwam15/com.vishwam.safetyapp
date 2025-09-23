package com.vishwam.safetyapp.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vishwam.safetyapp.model.Hospital;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HospitalDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private String name;
    private String address;
    private String phone;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public HospitalDetailsImpl(Long id, String code, String password, String name, String address, String phone,
                               Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.code = code;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.authorities = authorities;
    }

    public static HospitalDetailsImpl build(Hospital hospital) {
        List<GrantedAuthority> authorities = hospital.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new HospitalDetailsImpl(
                hospital.getId(),
                hospital.getCode(),
                hospital.getPassword(),
                hospital.getName(),
                hospital.getAddress(),
                hospital.getPhone(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return code; // Using hospital code as username for hospitals
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
        HospitalDetailsImpl hospital = (HospitalDetailsImpl) o;
        return Objects.equals(id, hospital.id);
    }
}