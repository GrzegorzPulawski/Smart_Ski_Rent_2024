package com.smart_ski_rent_ver1_2.security.service;

import com.smart_ski_rent_ver1_2.security.dto.SignUpDto;
import com.smart_ski_rent_ver1_2.security.dto.UserDto;
import com.smart_ski_rent_ver1_2.security.entity.User;
import org.springframework.stereotype.Service;

@Service
public class CustomUserMapper {

    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLogin(user.getLogin());
        // Pomiń hasło, jeśli nie jest potrzebne
        return userDto;
    }

    public User signUpToUser (SignUpDto signUpDto) {
        if (signUpDto == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setLogin(signUpDto.getLogin());
        user.setPassword(signUpDto.getPassword());
        return user;
    }
}
