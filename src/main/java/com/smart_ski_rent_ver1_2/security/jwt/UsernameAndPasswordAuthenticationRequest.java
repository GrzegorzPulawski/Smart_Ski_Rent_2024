package com.smart_ski_rent_ver1_2.security.jwt;

public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;

//    public UsernameAndPasswordAuthenticationRequest(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    public UsernameAndPasswordAuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
