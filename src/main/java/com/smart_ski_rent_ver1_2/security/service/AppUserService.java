package com.smart_ski_rent_ver1_2.security.service;

import com.smart_ski_rent_ver1_2.exception.ClientNotExistsException;
import com.smart_ski_rent_ver1_2.exception.UserNotFoundException;
import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.entity.AppUserRole;
import com.smart_ski_rent_ver1_2.security.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
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
        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        // Optionally assign a default role if not provided
        if (appUser.getRole() == null) {
            appUser.setRole(AppUserRole.ROLE_USER); // Assign default role ROLE_USER
        }

        // Save the user
        userRepository.save(appUser);

        // Return 201 Created status
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public List<AppUser> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<AppUser> findUserByUsername(String username) {
        return userRepository.findByAppUserName(username);
    }
    public void deleteUserById(Long idUser) {
        boolean existsUser = userRepository.existsById(idUser);
        if (existsUser) {
            userRepository.deleteById(idUser);
        } else {
            throw new ClientNotExistsException("Użytkownik o ID: " + idUser + "nie istnieje");
        }
    }

    public AppUser loginUser(String appUserName, String password) {
        Optional<AppUser> optionalAppUser = userRepository.findByAppUserName(appUserName);

        // Check if user exists
        if (optionalAppUser.isPresent()) {
            AppUser appUser = optionalAppUser.get();

            if (passwordEncoder.matches(password, appUser.getPassword())) {
                return appUser;
            } else {
                throw new BadCredentialsException("Złe hasło");
            }
        } else {
            throw new UserNotFoundException("Nie znaleziono Użytkownika");
        }
    }

    public void logout() {
        // Unieważnienie tokenu lub czyszczenie sesji
        SecurityContextHolder.clearContext();
    }

}
