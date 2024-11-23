package com.smart_ski_rent_ver1_2.security.service;

import com.smart_ski_rent_ver1_2.security.dto.SignUpDto;
import com.smart_ski_rent_ver1_2.security.dto.UserDto;
import com.smart_ski_rent_ver1_2.security.entity.User;
import com.smart_ski_rent_ver1_2.security.userrole.Role;
import org.springframework.stereotype.Service;

import java.util.Calendar;

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
        userDto.setRole(user.getRole());
        userDto.setCalendar(user.isCalendar());
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
        user.setRole(Role.USER);
        //user.setCalendar();
        return user;
    }
}
