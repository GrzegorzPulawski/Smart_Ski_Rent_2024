package com.smart_ski_rent_ver1_2.exception;

public class ClientNotExistsException extends RuntimeException{
    public ClientNotExistsException(String message) {
        super(message);
    }
}
