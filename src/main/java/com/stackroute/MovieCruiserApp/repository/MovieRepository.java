package com.stackroute.MovieCruiserApp.repository;

import com.stackroute.MovieCruiserApp.domain.Movie;
import org.jboss.logging.Param;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    public Movie findBymovieTitle( String name);
    public Movie findByimdbId(String name);
}
