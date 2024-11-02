package com.smart_ski_rent_ver1_2.security.newuserrole;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    DEVEL,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
