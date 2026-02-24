package com.phonebook.models;

public class Contact {

    // Private Fields
    private String name;
    private String phoneNumber;
    private String email;

    // Constructor & Setters
    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // Methods
    public String toCsvString() {
        return name + "," + phoneNumber + "," + email;
    }

}
