package com.smart_ski_rent_ver1_2.exception;

public class CompanyNotExistsException extends RuntimeException{
    public CompanyNotExistsException (String message) {
        super(message);
    }
}