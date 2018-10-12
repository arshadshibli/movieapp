package com.stackroute.moviecruiserapp.services;

import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.exceptions.MovieAlreadyExistException;
import com.stackroute.moviecruiserapp.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiserapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieServiceImpl2 {
    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(Movie movie) throws MovieAlreadyExistException {
        System.out.println(movie.getMovieTitle());
        return movie;
    }

    public List<Movie> getAllMovie(){
        System.out.println("Movies listing to console from Aws");
        return null;
    }

    public Movie deleteMovie(int imdbId) {
        System.out.println("Movie deleted");
        return null;
    }

    public Movie getMovieById(int imdbId) {
        System.out.println(imdbId);
        return null;
    }

    public Movie getMovieByName(String movieTitle) throws MovieNotFoundException {
        System.out.println(movieTitle);
        return null;
    }

    public Movie updateMovie(String imdbId,String comment) {
        System.out.println(comment);
        return null;
    }


}
