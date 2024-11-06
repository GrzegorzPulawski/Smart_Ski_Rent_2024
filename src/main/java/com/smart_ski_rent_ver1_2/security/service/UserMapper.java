package com.smart_ski_rent_ver1_2.security.service;

import com.smart_ski_rent_ver1_2.security.dto.SignUpDto;
import com.smart_ski_rent_ver1_2.security.dto.UserDto;
import com.smart_ski_rent_ver1_2.security.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(ignore = true, target = "password" )
    User signUpToUser(SignUpDto userDto);
}
