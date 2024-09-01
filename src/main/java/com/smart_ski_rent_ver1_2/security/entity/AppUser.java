package com.smart_ski_rent_ver1_2.security.entity;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class AppUser {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name ="user_id")
        private Long id;
        @Column(name="user_name")
        private String userName;
        @Column(name = "password")
        private String password;

        @Enumerated(EnumType.STRING)
        @Column(name = "user_role")
        private AppUserRole role;


        public AppUser(){};

    public AppUser( String username, String password, AppUserRole role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = userName;
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
