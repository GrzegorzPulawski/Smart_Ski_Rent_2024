package com.smart_ski_rent_ver1_2.security.controller;

import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AppUser appUser) {
        appUserService.createUser(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}

