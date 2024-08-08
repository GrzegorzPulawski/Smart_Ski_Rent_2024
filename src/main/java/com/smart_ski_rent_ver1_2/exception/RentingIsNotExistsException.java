package com.smart_ski_rent_ver1_2.exception;

public class RentingIsNotExistsException extends RuntimeException{
    public RentingIsNotExistsException(String message) {
        super(message);
    }
}
