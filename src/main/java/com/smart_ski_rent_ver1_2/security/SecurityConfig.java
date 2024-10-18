package com.smart_ski_rent_ver1_2.security;

import com.smart_ski_rent_ver1_2.security.service.AppUserDetailsService;
import com.smart_ski_rent_ver1_2.security.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     private final AppUserDetailsService appUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(AppUserDetailsService appUserDetailsService, PasswordEncoder passwordEncoder) {
        this.appUserDetailsService = appUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( "index.html","/api/appusers/login","api/","/").permitAll()
                        // Tylko dla ADMIN: POST i DELETE na /api/formEquipment
                        .requestMatchers("/api/appusers/devel/**").hasRole("DEVEL")

                        .requestMatchers( "/api/equipments/add").hasRole("ADMIN")
                        .requestMatchers( "/api/equipments/delete").hasRole("ADMIN")

                        .requestMatchers(  "/api/**").hasAnyRole("USER", "ADMIN", "DEVEL")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/api/appusers/login") // Wskazuje na niestandardową stronę logowania
                        .defaultSuccessUrl("/") // Adres, na który przekieruje po udanym logowaniu
                        .permitAll()
                )
                .httpBasic(withDefaults());

        return http.build();
    }

}