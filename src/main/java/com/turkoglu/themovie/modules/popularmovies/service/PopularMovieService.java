package com.turkoglu.themovie.modules.popularmovies.service;

import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieRequest;
import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieResponse;

import java.util.List;


public interface PopularMovieService {
    PopularMovieResponse saveMovie(PopularMovieRequest request);
    List<PopularMovieResponse> saveMovies(List<PopularMovieRequest> requests);
    PopularMovieResponse getMovieById(Long id);
    List<PopularMovieResponse> getAllMovies();

}
