package com.smart_ski_rent_ver1_2.security.service;

import com.smart_ski_rent_ver1_2.security.entity.AppUser;
import com.smart_ski_rent_ver1_2.security.entity.AppUserDetails;
import com.smart_ski_rent_ver1_2.security.repositories.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public AppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user name: "+ username);
       AppUser appUser =appUserRepository.findByAppUserName(username)
               .orElseThrow(()-> new UsernameNotFoundException("Usernot found: "+ username));
        log.info("Found user: " + appUser.getAppUserName());
        log.info("Found password: " + appUser.getPassword());
        log.info("Found role: "+ appUser.getRole());
        return new AppUserDetails(appUser);
    }
}
