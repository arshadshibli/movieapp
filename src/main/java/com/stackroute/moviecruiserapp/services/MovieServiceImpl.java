package com.stackroute.moviecruiserapp.services;

import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.exceptions.MovieAlreadyExistException;
import com.stackroute.moviecruiserapp.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiserapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
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

    public Movie getMovieByName(String movieTitle) throws MovieNotFoundException {

        if(movieRepository.findBymovieTitle(movieTitle)==null) {
            throw new MovieNotFoundException("Movie Already Exists");
        }
        Movie movie=movieRepository.findBymovieTitle(movieTitle) ;
        return movie;
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
