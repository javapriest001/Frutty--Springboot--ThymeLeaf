package com.example.frutty.Exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(int id) {
        super("Customer With id: " + id + " Not Found");
    }
    public CustomerNotFoundException(String email) {
        super("Customer With email: " + email + " Not Found");
    }
}
