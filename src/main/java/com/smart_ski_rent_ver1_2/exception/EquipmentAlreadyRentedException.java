package com.smart_ski_rent_ver1_2.exception;

public class EquipmentAlreadyRentedException extends RuntimeException{
    public EquipmentAlreadyRentedException(String message) {
        super(message);
    }
}
