package com.smart_ski_rent_ver1_2.security.controller;

import com.smart_ski_rent_ver1_2.security.dto.AuthResponse;
import com.smart_ski_rent_ver1_2.security.dto.LoginRequest;
import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<String> loginUser(@RequestHeader("Authorization") String authHeader) {
        try {
            // Extract the Basic Auth credentials from the Authorization header
            if (authHeader != null && authHeader.startsWith("Basic ")) {
                String base64Credentials = authHeader.substring("Basic ".length());
                byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Credentials);
                String credentials = new String(decodedBytes);

                // Credentials are in the format "username:password"
                String[] values = credentials.split(":", 2);
                String username = values[0];
                String password = values[1];

                // Authenticate the user using the loginUser service method
                AppUser appUser = appUserService.loginUser(username, password);

                // If successful, return an OK response (you can return a token here if needed)
                return ResponseEntity.ok("Logged in successfully as " + appUser.getAppUserName());
            } else {
                // If the Authorization header is missing or malformed
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
            }
        } catch (RuntimeException e) {
            // Catch any exceptions from the loginUser method (e.g., invalid credentials)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        appUserService.logout();
        return ResponseEntity.ok("Wylogowano pomyślnie");
    }
}