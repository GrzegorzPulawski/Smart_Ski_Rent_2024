package com.smart_ski_rent_ver1_2.security.controller;

import com.smart_ski_rent_ver1_2.security.dto.UserDetailsDto;
import com.smart_ski_rent_ver1_2.security.dto.UserDto;
import com.smart_ski_rent_ver1_2.security.entity.User;
import com.smart_ski_rent_ver1_2.security.service.JwtAuthFilter;
import com.smart_ski_rent_ver1_2.security.service.UserAuthProvider;
import com.smart_ski_rent_ver1_2.security.service.UserServiceNew;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceNew userService;
    private final UserAuthProvider userAuthProvider;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/details")
    public ResponseEntity<UserDetailsDto> getUserDetails(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        Long userId = userAuthProvider.extractUserId(token);  // Assume your JWT has a method to get `userId`

        UserDto user = userService.getUserById(userId);

        UserDetailsDto userDetails = new UserDetailsDto();
        userDetails.setFirstName(user.getFirstName());
        userDetails.setLastName(user.getLastName());

        return ResponseEntity.ok(userDetails);
    }
    @PreAuthorize("hasAuthority('DEVEL')")
    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        List <UserDto> userDtoList = userService.getAllUsers();
        log.info("List of all users");
        return userDtoList;

    }
}
