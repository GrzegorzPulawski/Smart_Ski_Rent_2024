package com.smart_ski_rent_ver1_2.exception;

public class NoCompanyForThisLoginException extends RuntimeException{
    public NoCompanyForThisLoginException(String message) {
        super(message);
    }
}
