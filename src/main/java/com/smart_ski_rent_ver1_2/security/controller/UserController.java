package com.smart_ski_rent_ver1_2.security.controller;

import com.smart_ski_rent_ver1_2.security.dto.UserDetailsDto;
import com.smart_ski_rent_ver1_2.security.dto.UserDto;
import com.smart_ski_rent_ver1_2.security.entity.User;
import com.smart_ski_rent_ver1_2.security.service.JwtAuthFilter;
import com.smart_ski_rent_ver1_2.security.service.UserServiceNew;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceNew userService;
 private final JwtAuthFilter jwtAuthFilter;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/details")
    public ResponseEntity<UserDetailsDto> getUserDetails(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtAuthFilter.extractUserId(token);  // Assume your JWT has a method to get `userId`

        UserDto user = userService.getUserById(userId);

        UserDetailsDto userDetails = new UserDetailsDto();
        userDetails.setFirstName(user.getFirstName());
        userDetails.setLastName(user.getLastName());

        return ResponseEntity.ok(userDetails);
    }
}
