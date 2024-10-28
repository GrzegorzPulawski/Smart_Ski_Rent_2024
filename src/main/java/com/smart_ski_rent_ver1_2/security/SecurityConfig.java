package com.smart_ski_rent_ver1_2.security;

import com.smart_ski_rent_ver1_2.security.dbauth.AppUserDetailsService;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.smart_ski_rent_ver1_2.security.entity.AppUserRole.DEVEL;
import static edu.emory.mathcs.backport.java.util.concurrent.TimeUnit.DAYS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private PasswordEncoder passwordEncoder;
    private final AppUserDetailsService appUserDetailsService;

    public SecurityConfig(PasswordEncoder passwordEncoder, AppUserDetailsService appUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/appusers/login", "/api/home","/", "index.html", "swagger-ui/**").permitAll()
                        .requestMatchers("/api/appusers/devel/**").hasRole(DEVEL.name())
                        .requestMatchers("/api/**").hasAnyRole(DEVEL.name())
                        .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/login")
                .passwordParameter("password2")
                .usernameParameter("username2")
                .defaultSuccessUrl("/api/home", true)
                .permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds((int) DAYS.toSeconds(21))
                .rememberMeParameter("pamietaj-mnie")
                .key("jakis_strasznie_trudny_klucz")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");
        return http.build();

    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserDetailsService);
        return provider;
    }
}