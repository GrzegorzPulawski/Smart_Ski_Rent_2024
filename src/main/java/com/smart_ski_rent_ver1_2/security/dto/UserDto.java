package com.smart_ski_rent_ver1_2.security.dto;

import com.smart_ski_rent_ver1_2.security.userrole.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private  String login;
    private  String token;
    //private Role role;
}
