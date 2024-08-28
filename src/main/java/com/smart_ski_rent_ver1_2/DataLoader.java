package com.smart_ski_rent_ver1_2;

import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.entity.AppUserRole;
import com.smart_ski_rent_ver1_2.security.repositories.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner commandLineRunner(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            appUserRepository.save(new AppUser(
                    "admin",
                    passwordEncoder.encode("adminpass"),
                    AppUserRole.ADMIN
            ));

            appUserRepository.save(new AppUser(
                    "user",
                    passwordEncoder.encode("userpass"),
                    AppUserRole.USER
            ));
        };
    }
}
