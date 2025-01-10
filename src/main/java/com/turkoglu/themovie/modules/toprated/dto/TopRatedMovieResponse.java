package com.turkoglu.themovie.modules.toprated.dto;

import lombok.Data;

import java.util.List;

@Data
public class TopRatedMovieResponse {
    private Long id;
    private String title;
    private String overview;
    private double popularity;
    private double voteAverage;
    private String releaseDate;
    private String posterPath;
    private List<String> genreNames;
}