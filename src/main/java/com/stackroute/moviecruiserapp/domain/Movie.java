package com.stackroute.moviecruiserapp.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import javax.persistence.Entity;
//import javax.persistence.Id;

@Document
public class Movie {
    @Id
    @ApiModelProperty(notes = "The Movie specific imdbID")
    String imdbId;
    @NotNull
    @ApiModelProperty(notes = "The Movie specific Title")
    String movieTitle;
    @NotNull
    @Size(min = 4,max = 4)
    @ApiModelProperty(notes = "The Movie specific year of release")
    String yearOfRelease;
    @Max(5)
    @ApiModelProperty(notes = "The Rating given for Movie")
    double rating;
    @ApiModelProperty(notes = "The Movie specific Comment")
    String comment;
    @NotNull
    @ApiModelProperty(notes = "The poster Url of Movie")
    String posterUrl;

    public Movie() {
    }

    public Movie(String imdbId, String movieTitle, String yearOfRelease, String comment,double rating, String posterUrl) {
        this.imdbId = imdbId;
        this.movieTitle= movieTitle;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
        this.comment = comment;
        this.posterUrl =posterUrl;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
