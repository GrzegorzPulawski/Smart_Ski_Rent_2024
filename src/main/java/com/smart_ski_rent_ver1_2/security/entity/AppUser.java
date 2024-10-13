package com.smart_ski_rent_ver1_2.security.entity;

import jakarta.persistence.*;
import javax.management.relation.Role;

@Entity
@Table(name="user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "app_user_name")
    private String appUserName;
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    public AppUserRole role;

    public AppUser() {
    }

    public AppUser(Long userId, String appUserName, String password, AppUserRole role) {
        this.userId = userId;
        this.appUserName = appUserName;
        this.password = password;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserRole getRole() {
        return role;
    }

    public void setRole(AppUserRole role) {
        this.role = role;
    }
}


