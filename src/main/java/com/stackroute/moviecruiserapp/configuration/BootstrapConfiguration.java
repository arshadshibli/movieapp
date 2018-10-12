package com.stackroute.moviecruiserapp.configuration;

import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class BootstrapConfiguration implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {

    MovieRepository movieRepository;
    @Autowired
    public BootstrapConfiguration(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Movie movie = new Movie("tt342311213", "Manmarziyaan", "2018", "Thrilling Experience", 4.5, "https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLNzA@._V1_QL50_SY1000__.jpg");
        movieRepository.save(movie);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Movie movie = new Movie("tt342311210", "Manmarziyaan", "2018", "Thrilling Experience", 4.5, "https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLNzA@._V1_QL50_SY1000__.jpg");
        movieRepository.save(movie);
        movie = new Movie("tt342311211", "Venom", "2017", "Average Movie", 4.1, "https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLNzA@._V1_QL50_SY1000__.jpg");
        movieRepository.save(movie);
        movie = new Movie("tt342311212", "Dangal", "2017", "Excellent Experience", 4.7, "https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLNzA@._V1_QL50_SY1000__.jpg");
        movieRepository.save(movie);
    }

}
