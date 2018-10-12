package com.stackroute.moviecruiserapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserapp.domain.Movie;
import com.stackroute.moviecruiserapp.exceptions.MovieAlreadyExistException;
import com.stackroute.moviecruiserapp.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiserapp.services.MovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieCruiserAppControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Movie movie,movie1;
    @MockBean
    private MovieServiceImpl movieService;
    @InjectMocks
    private MovieCruiserAppController movieCruiserAppController;
    private List<Movie> list=new ArrayList<Movie>();
    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(movieCruiserAppController).build();
        movie=new Movie();
        movie.setImdbId("1");
        movie.setMovieTitle("Odiyan");
        movie.setPosterUrl("hhhh");
        movie.setRating(4.2);
        movie.setComment("Super");
        movie.setYearOfRelease("2018");
        list.add(movie);
        movie1=new Movie();
        movie1.setImdbId("2");
        movie1.setMovieTitle("Venom");
        movie1.setPosterUrl("hhhh");
        movie1.setRating(4.2);
        movie1.setComment("Super");
        movie1.setYearOfRelease("2017");
        list.add(movie);

    }

    @Test
    public void saveMovieTest() throws Exception {
        when(movieService.addMovie(movie)).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void saveMovieTestFailure() throws Exception {
        when(movieService.addMovie(any())).thenThrow(MovieAlreadyExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getAllUserTest() throws Exception {
        when(movieService.getAllMovie()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void searchMovieTest() throws Exception {
        when(movieService.getMovieById(movie.getImdbId())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/name")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void searchMovieTestFailure() throws Exception {
        when(movieService.getMovieByName(any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/name")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteMovieTest() throws Exception {
        when(movieService.deleteMovie(movie.getImdbId())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteMovieFailureTest() throws Exception {
        when(movieService.deleteMovie(movie.getImdbId())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void updateMovieTest() throws Exception {
        when(movieService.updateMovie(movie.getImdbId(),"new comment")).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void updateMovieFailureTest() throws Exception {
        when(movieService.updateMovie(any(),any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


