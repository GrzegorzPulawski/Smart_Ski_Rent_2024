package com.smart_ski_rent_ver1_2.security.dbauth;

import com.smart_ski_rent_ver1_2.security.entity.AppUserEntity;
import com.smart_ski_rent_ver1_2.security.dbauth.AppUser;
import com.smart_ski_rent_ver1_2.security.repositories.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public AppUserDetailsService(@Qualifier("fake") UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getApplicationUserBy(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found",username)));
    }
}
