package com.smart_ski_rent_ver1_2.security.controller;

import com.smart_ski_rent_ver1_2.security.dto.LoginRequest;
import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/appuser")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUser appUser) {
        log.info("Created user with name: " + appUser.getAppUserName());
        appUserService.createUser(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
    @GetMapping
    public List<AppUser> findAllUsers(){
       return appUserService.findAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login attempt: " + loginRequest.getAppUserName() + " / " + loginRequest.getPassword());
        try {
            AppUser appUser = appUserService.loginUser(loginRequest.getAppUserName(), loginRequest.getPassword());
            return ResponseEntity.ok("Login udany" + appUser.getAppUserName());
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}