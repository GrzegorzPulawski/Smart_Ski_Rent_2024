package com.smart_ski_rent_ver1_2.security.jwtnew;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrorDto {
   private String message;
}
