package com.smart_ski_rent_ver1_2.security.jwtnew;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CredentialsDto {
    private String login;
    private char[] password;
}
