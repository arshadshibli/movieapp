package com.stackroute.MovieCruiserApp.exceptions;

public class MovieAlreadyExistException extends Exception {
    private String message;

    public MovieAlreadyExistException(String message) {
        super(message);
    }
}