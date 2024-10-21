package com.smart_ski_rent_ver1_2.security.controller;

import com.smart_ski_rent_ver1_2.security.dto.AuthResponse;
import com.smart_ski_rent_ver1_2.security.dto.LoginRequest;
import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/appusers")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/devel/register")
    public ResponseEntity<Void> registerUser(@RequestBody AppUser appUser) {
        log.info("Created user with name: " + appUser.getAppUserName());
        try {
            return appUserService.createUser(appUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/devel/findAll")
    public List<AppUser> findAllUsers(){
       return appUserService.findAllUsers();
    }

    @DeleteMapping("/devel/delete/{idUser}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long idUser){
        log.info("Delete User with ID: "+idUser);
        appUserService.deleteUserById(idUser);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AppUser> login(@RequestBody LoginRequest loginRequest) {
        AppUser appUser = appUserService.loginUser(loginRequest.getAppUserName(), loginRequest.getPassword());
        return ResponseEntity.ok(appUser); // Or return a DTO with token information, etc.
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Unieważnienie sesji
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Wylogowano pomyślnie");
    }



}