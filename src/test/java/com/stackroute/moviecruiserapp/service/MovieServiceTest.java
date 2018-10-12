package com.stackroute.moviecruiserapp.service;

import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.exceptions.MovieAlreadyExistException;
import com.stackroute.moviecruiserapp.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiserapp.repository.MovieRepository;
import com.stackroute.moviecruiserapp.services.MovieServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    Movie movie, movie1;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> movieList = new ArrayList<>();
    List<Movie> list;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setImdbId("1");
        movie.setMovieTitle("Odiyan");
        movie.setPosterUrl("hhhh");
        movie.setRating(4.2);
        movie.setComment("Super");
        movie.setYearOfRelease("2018");
        movieList.add(movie);
        movie1 = new Movie();
        movie1.setImdbId("2");
        movie1.setMovieTitle("Venom");
        movie1.setPosterUrl("hhhh");
        movie1.setRating(4.2);
        movie1.setComment("Super");
        movie1.setYearOfRelease("2017");
        movieList.add(movie1);

    }
    @After
    public void tearDown()
    {
        movieRepository.deleteAll();
        list=null;
    }

    @Test
    public void addMovieTestSuccess() throws MovieAlreadyExistException, MethodArgumentNotValidException {
        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieService.addMovie(movie);
        Assert.assertEquals(movie, savedMovie);
        verify(movieRepository, times(1)).save(movie);
    }

    @Test(expected = MovieAlreadyExistException.class)
    public void addMovieTestFailure() throws MovieAlreadyExistException,MethodArgumentNotValidException {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedMovie = movieService.addMovie(movie);;
        Assert.assertNotEquals(movie, savedMovie);
    }
    @Test
    public void getAllMoviesTest()
    {
        movieRepository.save(movie);
        movieRepository.save(movie1);
        when(movieRepository.findAll()).thenReturn(movieList);
        list=movieService.getAllMovie();
        Assert.assertEquals(list,movieList);
    }
    @Test
    public void getAllMoviesTestFailure()
    {

        when(movieRepository.findAll()).thenReturn(movieList);
        list=movieService.getAllMovie();
        Movie movie2=new Movie("3","test","test","test",4.2,"test");
        ArrayList<Movie> test=new ArrayList<Movie>();
        test.addAll(list);
        movieList.add(movie2);
        System.out.println(test);
        System.out.println(movieList);
        Assert.assertNotEquals(test,movieList);
    }
    @Test
    public void getMovieByIdTest()
    {
        when(movieRepository.findByimdbId((movie.getImdbId()))).thenReturn(movie);
        Movie retrievedMovie=movieService.getMovieById(movie.getImdbId());
        Assert.assertEquals(movie,retrievedMovie);
    }
    @Test
    public void getMovieByIdFailureTest()
    {
        when(movieRepository.findByimdbId((movie.getImdbId()))).thenReturn(null);
        Movie retrievedMovie=movieService.getMovieById(movie.getImdbId());
        Assert.assertNotEquals(movie,retrievedMovie);
    }
    @Test
    public void getMovieByTitleTest()throws MovieNotFoundException
    {
        when(movieRepository.findBymovieTitle((movie.getMovieTitle()))).thenReturn(movie);
        Movie retrievedMovie=movieService.getMovieByName(movie.getMovieTitle());
        Assert.assertEquals(movie,retrievedMovie);
    }
    @Test(expected = MovieNotFoundException.class)
    public void getMovieByTitleFailureTest() throws MovieNotFoundException
    {
        when(movieRepository.findBymovieTitle((movie.getMovieTitle()))).thenReturn(null);
        Movie retrievedMovie=movieService.getMovieByName(movie.getMovieTitle());
        Assert.assertNotEquals(movie,retrievedMovie);
    }
    @Test
    public void updateMovieTest()throws MovieNotFoundException
    {
        when(movieRepository.save((Movie)any())).thenReturn(movie);
        when(movieRepository.findByimdbId((movie.getImdbId()))).thenReturn(movie);
        Movie updatedMovie=movieService.updateMovie(movie.getImdbId(),"hello");
        Assert.assertEquals(updatedMovie.getComment(),"hello");

    }
    @Test(expected = MovieNotFoundException.class)
    public void updateMovieFailureTest()throws MovieNotFoundException
    {
        when(movieRepository.save((Movie)any())).thenReturn(movie);
        when(movieRepository.findByimdbId((movie.getImdbId()))).thenReturn(null);
        Movie updatedMovie=movieService.updateMovie(movie.getImdbId(),"hello");
        Assert.assertEquals(updatedMovie.getComment(),"hello");

    }
    @Test
    public void deleteMovieTest()throws MovieNotFoundException
    {
        when(movieRepository.findByimdbId((movie.getImdbId()))).thenReturn(movie);
        doNothing().when(movieRepository).delete((Movie)any());
        movieService.deleteMovie(movie.getImdbId());
    }
    @Test(expected = MovieNotFoundException.class)
    public void deleteMovieTestFailure()throws MovieNotFoundException
    {
        when(movieRepository.findByimdbId((movie.getImdbId()))).thenReturn(null);
        doNothing().when(movieRepository).delete((Movie)any());
        movieService.deleteMovie(movie.getImdbId());
    }
}




