package com.smart_ski_rent_ver1_2.security;

import com.smart_ski_rent_ver1_2.security.dbauth.AppUserDetailsService;
import com.smart_ski_rent_ver1_2.security.jwt.JwtConfig;
import com.smart_ski_rent_ver1_2.security.jwt.JwtTokenVerifier;
import com.smart_ski_rent_ver1_2.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;


import javax.crypto.SecretKey;
import javax.management.relation.Role;

import static com.smart_ski_rent_ver1_2.security.entity.AppUserRole.DEVEL;
import static com.smart_ski_rent_ver1_2.security.entity.AppUserRole.STUDENT;
@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)  // Używamy @EnableMethodSecurity zamiast @EnableGlobalMethodSecurity
public class ApplicationSecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final AppUserDetailsService appUserDetailsService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    private final AuthenticationManager authenticationManager;


    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     AppUserDetailsService appUserDetailsService,
                                     SecretKey secretKey, JwtConfig jwtConfig,
                                     AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Configuring SecurityFilterChain");
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager, secretKey, jwtConfig))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "index.html", "/api/auth/login", "/login", "/api/equipments").permitAll()
                        .requestMatchers("/api/**").hasAnyRole(STUDENT.name(), DEVEL.name())
                        .anyRequest().authenticated()
                );
        log.info("SecurityFilterChain configured successfully");
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
