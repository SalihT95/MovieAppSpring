package com.turkoglu.themovie.modules.popularmovies.service;

import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieRequest;
import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieResponse;

import java.util.List;

public interface PopularMovieService {
    void saveMovies(PopularMovieRequest requests);
    PopularMovieResponse getMovieById(Long id);
    List<PopularMovieResponse> getAllMovies();
}