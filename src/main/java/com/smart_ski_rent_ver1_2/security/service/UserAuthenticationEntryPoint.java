package com.smart_ski_rent_ver1_2.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_ski_rent_ver1_2.security.dto.ErrorDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException{
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        OBJECT_MAPPER.writeValue(response.getOutputStream(), new ErrorDto("Ścieżka nieautoryzowana"));
    }

}
