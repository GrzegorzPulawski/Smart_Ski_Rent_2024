package com.smart_ski_rent_ver1_2.security.service;

import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.repositories.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository =userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseEntity<Void> createUser( AppUser appUser) {
      String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        userRepository.save(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    public List<AppUser> findAllUsers(){
        return userRepository.findAll();
    }
}
