package com.smart_ski_rent_ver1_2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

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
                        .requestMatchers("/api/**").permitAll()   // Dostęp do endpointów publicznych bez autoryzacji
                        .requestMatchers(HttpMethod.POST, "/api/clients/**").permitAll()
                         .requestMatchers("/admin/**").hasRole("ADMIN")  // Endpointy dostępne tylko dla roli ADMIN
                       .requestMatchers("/users/**").hasRole("USER")    // Endpointy dostępne tylko dla roli USER
                        .anyRequest().authenticated()  // Wymagana autoryzacja dla pozostałych żądań
                );
              //  .formLogin(withDefaults())

        // .formLogin(formLogin -> formLogin
                 //       .loginPage("/login").permitAll()  // Własna strona logowania
                   //     .defaultSuccessUrl("/home", true)  // Strona po pomyślnym zalogowaniu
              //  )
      //  .logout(withDefaults());
               // .logout(logout -> logout
                     //   .logoutUrl("/logout")
                       // .logoutSuccessUrl("/login?logout")
                        //.permitAll()
             //   );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
