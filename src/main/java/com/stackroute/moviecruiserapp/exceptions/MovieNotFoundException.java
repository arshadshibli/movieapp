package com.stackroute.moviecruiserapp.exceptions;

public class MovieNotFoundException extends Exception {
    private String message;

    public MovieNotFoundException(String message) {
        super(message);
    }
}
