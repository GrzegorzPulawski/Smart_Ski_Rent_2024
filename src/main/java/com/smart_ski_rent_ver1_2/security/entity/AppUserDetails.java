package com.smart_ski_rent_ver1_2.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AppUserDetails {
   // private final AppUser user;

 //   public AppUserDetails(AppUser user) {
   //     this.user = user;
   // }

   // @Override
   // public Collection<? extends GrantedAuthority> getAuthorities() {
        // Konwersja roli na GrantedAuthority
     //   return Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()));
   // }

   // @Override
   // public String getPassword() {
     //   return user.getPassword();
   // }

   // @Override
   // public String getUsername() {
     //   return user.getUsername();
   // }


    public boolean isAccountNonExpired() {
        return true;
    }


    public boolean isAccountNonLocked() {
        return true;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }
}