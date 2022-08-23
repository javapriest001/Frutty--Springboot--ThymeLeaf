package com.example.frutty.Exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Product With id: " + id + " Not Found");
    }
}
