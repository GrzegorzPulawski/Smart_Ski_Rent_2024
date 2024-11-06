package com.smart_ski_rent_ver1_2.security.config;

import com.smart_ski_rent_ver1_2.security.service.JwtAuthFilter;
import com.smart_ski_rent_ver1_2.security.service.UserAuthProvider;
import com.smart_ski_rent_ver1_2.security.service.UserAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthProvider userAuthProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS )
                .and()
                .authorizeHttpRequests((requests)-> requests
                        .requestMatchers( "/api/auth/login", "/api/auth/register").permitAll()
                        .anyRequest().authenticated()
                );
        return  http.build();
    }

}
