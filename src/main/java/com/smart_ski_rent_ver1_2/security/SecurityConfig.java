package com.smart_ski_rent_ver1_2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())  // Wyłączenie CSRF (jeśli potrzebne)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll()   // Dostęp do endpointów publicznych bez autoryzacji
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Endpointy dostępne tylko dla roli ADMIN
                        .requestMatchers("/user/**").hasRole("USER")    // Endpointy dostępne tylko dla roli USER
                        .anyRequest().authenticated()  // Wymagana autoryzacja dla pozostałych żądań
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login").permitAll()  // Własna strona logowania
                        .defaultSuccessUrl("/home", true)  // Strona po pomyślnym zalogowaniu
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
