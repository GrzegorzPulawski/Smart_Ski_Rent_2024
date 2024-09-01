package com.smart_ski_rent_ver1_2.security.service;

import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.entity.AppUserDetails;
import com.smart_ski_rent_ver1_2.security.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public AppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AppUser> optionalAppUser = appUserRepository.findByUserName(username);
        if (optionalAppUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found" + username);

        }
        return new AppUserDetails(optionalAppUser.get());
    }
}