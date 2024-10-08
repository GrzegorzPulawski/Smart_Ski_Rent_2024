package com.smart_ski_rent_ver1_2.security.controller;

import com.smart_ski_rent_ver1_2.security.dto.AuthResponse;
import com.smart_ski_rent_ver1_2.security.dto.LoginRequest;
import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/devel/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUser appUser) {
        log.info("Created user with name: " + appUser.getAppUserName());
        appUserService.createUser(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
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
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login attempt: " + loginRequest.getAppUserName() + " / " + loginRequest.getPassword());
        try {
            AppUser appUser = appUserService.loginUser(loginRequest.getAppUserName(), loginRequest.getPassword());
            String token = "stworzono Token";
            return ResponseEntity.ok(new AuthResponse(token));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Zły login; "+e.getMessage()));
        }
    }
}