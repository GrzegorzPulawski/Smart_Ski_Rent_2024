package com.smart_ski_rent_ver1_2.security.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{
    private final HttpStatus code;
    public AppException(String message, HttpStatus code){
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }
}
