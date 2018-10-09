package com.stackroute.MovieCruiserApp.services;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieCruiserApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieServices{
    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(Movie movie) throws MovieAlreadyExistException {
        if(getMovieById(movie.getImdbId())!=null) {
            throw new MovieAlreadyExistException("Movie Already Exists");
        }
        Movie savedMovie = movieRepository.save(movie);
        if(savedMovie == null)
        {
            throw new MovieAlreadyExistException("Movie Already Exists");
        }
        return savedMovie;
}

    public List<Movie> getAllMovie(){
        return (List<Movie>)movieRepository.findAll();
    }

    public Movie deleteMovie(String imdbId) throws MovieNotFoundException  {
        if(getMovieById(imdbId)==null) {
            throw new MovieNotFoundException("Movie Not Found");
        }
        else {
            Movie deletedMovie = getMovieById(imdbId);
            movieRepository.delete(deletedMovie);
            return deletedMovie;
        }
    }

    public Movie getMovieById(String imdbId) {
                return movieRepository.findByimdbId(imdbId);
    }

    public Movie getMovieByName(String movieTitle)  {
            return movieRepository.findBymovieTitle(movieTitle) ;

    }

    public Movie updateMovie(String imdbId,String comment) throws MovieNotFoundException{
        if(getMovieById(imdbId)==null) {
            throw new MovieNotFoundException("Movie Not Found");
        }
        else {
            Movie updatedMovie = getMovieById(imdbId);
            updatedMovie.setComment(comment.trim());
            movieRepository.save(updatedMovie);
            return updatedMovie;
        }
    }


}
