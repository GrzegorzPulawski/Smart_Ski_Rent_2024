package com.smart_ski_rent_ver1_2.exception;

public class RentingAlreadyReturnException extends RuntimeException{
    public RentingAlreadyReturnException(String message) {
        super(message);
    }
}
