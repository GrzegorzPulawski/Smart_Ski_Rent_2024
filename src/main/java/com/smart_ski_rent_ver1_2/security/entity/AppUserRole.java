package com.smart_ski_rent_ver1_2.security.entity;

public enum AppUserRole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_DEVEL("ROLE_DEVEL");

    private final String roleName;

    AppUserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
