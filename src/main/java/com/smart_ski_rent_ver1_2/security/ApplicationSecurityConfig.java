package com.smart_ski_rent_ver1_2.security;

import com.smart_ski_rent_ver1_2.security.dbauth.AppUserDetailsService;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;


import javax.management.relation.Role;

import static com.smart_ski_rent_ver1_2.security.entity.AppUserRole.DEVEL;
import static com.smart_ski_rent_ver1_2.security.entity.AppUserRole.STUDENT;
@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final AppUserDetailsService appUserDetailsService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, AppUserDetailsService appUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
        log.info("ApplicationSecurityConfig initialized with PasswordEncoder and AppUserDetailsService");
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Configuring SecurityFilterChain");
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // lub inne, zgodnie z potrzebami
                .and()
                .csrf().disable()
                .addFilter(new SecurityContextPersistenceFilter())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "index.html", "/api/auth/login","/login","/api/equipments").permitAll() // co ma być widoczne bez logowania (biała lista)
                        .requestMatchers("/api/**").hasAnyRole(STUDENT.name(), DEVEL.name())

                        .anyRequest().authenticated() // musi przejść autoryzację
                )

                .formLogin(form -> form
                        .loginPage("/api/auth/login")
                        .loginProcessingUrl("/api/auth/login")
                        .passwordParameter("password")
                        .usernameParameter("username")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                        .rememberMeParameter("pamietaj-mnie")
                        .key("jakis_strasznie_trudny_klucz")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "remember-me")
                        .logoutSuccessUrl("/api/auth/login")
                )
                .formLogin().disable();
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

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }

}
