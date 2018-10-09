package com.stackroute.MovieCruiserApp.controller;

import com.stackroute.MovieCruiserApp.domain.Movie;
import com.stackroute.MovieCruiserApp.exceptions.MovieAlreadyExistException;
import com.stackroute.MovieCruiserApp.exceptions.MovieNotFoundException;
import com.stackroute.MovieCruiserApp.services.MovieServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Api(value="movieapplication", description="Operations pertaining to a movie application")
public class MovieCruiserAppController {
    @Autowired
    @Qualifier("movieServiceImpl")
    private MovieServices movieService;

    @ApiOperation(value = "Save a Movie in database", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Saved Movie"),
            @ApiResponse(code = 401, message = "You are not authorized to save the movie"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping()
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try {
            Movie savedMovie = movieService.addMovie(movie);
            responseEntity = new ResponseEntity<Movie>(savedMovie, HttpStatus.OK);
        }
        catch (MovieAlreadyExistException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get all movies from database", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Retrieved Movielist"),
            @ApiResponse(code = 401, message = "You are not authorized to retrieve the movie list"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping()
    public ResponseEntity<?> getAllMovie(){
        List<Movie> movieList;
        movieList = movieService.getAllMovie();
        ResponseEntity responseEntity = new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
        return  responseEntity;
    }

    @ApiOperation(value = "Delete a movie from database", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted Movie"),
            @ApiResponse(code = 401, message = "You are not authorized to delete the movie"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @DeleteMapping(value ="/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable String movieId){
        ResponseEntity responseEntity;
        try {
            Movie deletedMovie = movieService.deleteMovie(movieId);
            responseEntity = new ResponseEntity<Movie>(deletedMovie, HttpStatus.OK);
        }
        catch (MovieNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "Update a Movie in database", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Updated Movie"),
            @ApiResponse(code = 401, message = "You are not authorized to update the movie"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PutMapping(value ="/{movieId}")
    public ResponseEntity<?> updateMovie(@PathVariable String movieId,@RequestBody String comment){
        ResponseEntity responseEntity;
        try {
            Movie updatedMovie = movieService.updateMovie(movieId, comment);
            responseEntity = new ResponseEntity<Movie>(updatedMovie, HttpStatus.OK);
        }catch(MovieNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "Search a Movie in database", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Searched for Movie"),
            @ApiResponse(code = 401, message = "You are not authorized to search the movie"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(value="/{movieName}")
    public ResponseEntity<?> searcheMovie(@PathVariable String movieName){
        ResponseEntity responseEntity;
        try {
            Movie searchMovie = movieService.getMovieByName(movieName);
            responseEntity = new ResponseEntity<Movie>(searchMovie, HttpStatus.OK);
        }
        catch ( Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

}
