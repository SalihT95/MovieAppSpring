package com.turkoglu.themovie.modules.popularmovies.dto;

import com.turkoglu.themovie.modules.shared.entity.Genre;
import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
    private String title;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private Double popularity;
    private Double voteAverage;
    private Integer voteCount;
    private Boolean adult;
    private Boolean video;
    private String posterPath;
    private String backdropPath;
    private List<Genre> genres;
}