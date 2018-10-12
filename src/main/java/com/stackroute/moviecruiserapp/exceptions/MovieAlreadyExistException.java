package com.stackroute.moviecruiserapp.exceptions;

public class MovieAlreadyExistException extends Exception {
    private String message;

    public MovieAlreadyExistException(String message) {
        super(message);
    }
}