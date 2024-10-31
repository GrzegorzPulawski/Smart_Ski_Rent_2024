package com.smart_ski_rent_ver1_2.security.entity;

public enum AppUserPermission {
    COMPANY_WRITE("company:write"),
    COMPANY_READ("company:read");
    private final String permission;
    AppUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
