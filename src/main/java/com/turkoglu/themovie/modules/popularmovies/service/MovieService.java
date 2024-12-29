package com.turkoglu.themovie.modules.popularmovies.service;

import com.turkoglu.themovie.modules.popularmovies.dto.MovieRequest;
import com.turkoglu.themovie.modules.popularmovies.dto.MovieResponse;

import java.util.List;


public interface MovieService {
    MovieResponse createMovie(MovieRequest request);
    MovieResponse getMovieById(Long id);
    List<MovieResponse> getAllMovies();
    List<MovieResponse> saveMovies(List<MovieRequest> requests);
}
