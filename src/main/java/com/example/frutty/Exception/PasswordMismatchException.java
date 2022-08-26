package com.example.frutty.Exception;

public class PasswordMismatchException extends RuntimeException {

    public PasswordMismatchException(String email) {
        super("Incorrect Password provided for : " + email );
    }
}
