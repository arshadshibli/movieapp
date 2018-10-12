package com.stackroute.moviecruiserapp.services;

import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.exceptions.MovieAlreadyExistException;
import com.stackroute.moviecruiserapp.exceptions.MovieNotFoundException;

import java.util.List;

public interface MovieServices {
    public Movie addMovie(Movie movie) throws MovieAlreadyExistException;
    public List<Movie> getAllMovie();
    public Movie deleteMovie(String imdbId)throws MovieNotFoundException ;
    public Movie getMovieById(String imdbId);
    public Movie getMovieByName(String movieTitle) throws MovieNotFoundException;
    public Movie updateMovie(String movieId, String comment) throws MovieNotFoundException ;
}
