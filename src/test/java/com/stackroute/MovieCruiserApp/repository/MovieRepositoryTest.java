package com.stackroute.MovieCruiserApp.repository;

import com.stackroute.MovieCruiserApp.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
@RunWith(SpringRunner.class )
@DataMongoTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie,movie1;


    @Before
    public void setup()
    {
        movie=new Movie();
        movie.setImdbId("1");
        movie.setMovieTitle("Odiyan");
        movie.setPosterUrl("hhhh");
        movie.setRating(4.2);
        movie.setComment("Super");
        movie.setYearOfRelease("2018");
        movie1=new Movie();
        movie1.setImdbId("2");
        movie1.setMovieTitle("Venom");
        movie1.setPosterUrl("hhhh");
        movie1.setRating(4.2);
        movie1.setComment("Super");
        movie1.setYearOfRelease("2017");
    }
    @After
    public void tearDown()
    {
        movieRepository.deleteAll();
    }
    @Test
    public void testSaveMovie()
    {
        Movie savedMovie=movieRepository.save(movie);
        Assert.assertEquals(movie,savedMovie);
    }
    @Test
    public void testSaveMovieFailure()
    {
        Movie savedMovie=movieRepository.save(movie1);
        Assert.assertNotEquals(movie,savedMovie);
    }
    @Test
    public void testGetAllMovies()
    {
        movieRepository.save(movie);
        movieRepository.save(movie1);
        List<Movie> movieList=(List)movieRepository.findAll();
        System.out.println(movieList);
        Assert.assertEquals(movieList.get(0).getImdbId(),movie.getImdbId());

    }

    @Test
    public void testGetAllMoviesFailure()
    {
        movieRepository.save(movie);
        movieRepository.save(movie1);
        List<Movie> movieList=(List)movieRepository.findAll();
        Assert.assertNotEquals(movieList.get(0).getImdbId(),movie1.getImdbId());

    }
    @Test
    public void testDeleteMovie()
    {
        movieRepository.save(movie);
        movieRepository.delete(movie);
        Movie retrievedMovie=movieRepository.findByimdbId(movie.getImdbId());
        Assert.assertNull(retrievedMovie);

    }

    @Test
    public void testDeleteMovieFailure()
    {
        movieRepository.save(movie);
        movieRepository.save(movie1);
        movieRepository.delete(movie);
        Movie retrievedMovie=movieRepository.findByimdbId(movie.getImdbId());
        Assert.assertEquals(retrievedMovie,movie);

    }

}





