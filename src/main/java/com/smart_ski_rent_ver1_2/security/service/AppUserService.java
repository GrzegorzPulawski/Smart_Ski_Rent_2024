package com.smart_ski_rent_ver1_2.security.service;

import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService{
    private final AppUserRepository userRepository;
    private final   PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<Void> createUser(AppUser appUser) {
      String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        userRepository.save(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    public List<AppUser> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<AppUser> findUserByUsername(String username) {
        return userRepository.findByAppUserName(username);
    }

    public AppUser loginUser(String appUserName, String password){
       Optional<AppUser> optionalAppUser = userRepository.findByAppUserName(appUserName);
        if(optionalAppUser.isPresent() && passwordEncoder.matches(password, optionalAppUser.get().getPassword())) {
            return optionalAppUser.get();
        }else{
            throw new RuntimeException("Błędne dane");
        }
    }
}
