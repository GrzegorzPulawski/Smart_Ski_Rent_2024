package com.smart_ski_rent_ver1_2.security.dto;

public class UserDetailsDto {
    private String firstName;
    private String lastName;
    private boolean calendar;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isCalendar() {
        return calendar;
    }

    public void setCalendar(boolean calendar) {
        this.calendar = calendar;
    }
}
