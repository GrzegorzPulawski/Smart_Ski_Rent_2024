package com.smart_ski_rent_ver1_2.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AppUserDetails implements UserDetails {
    private final AppUser appUser;
    public AppUserDetails(AppUser appUser) {
        this.appUser = appUser;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Konwersja roli na GrantedAuthority
        return Collections.singleton(new SimpleGrantedAuthority(appUser.getRole().getRoleName()));
    }
   @Override
    public String getPassword() {
      return appUser.getPassword();
    }

   @Override
    public String getUsername() {
        return appUser.getAppUserName();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
