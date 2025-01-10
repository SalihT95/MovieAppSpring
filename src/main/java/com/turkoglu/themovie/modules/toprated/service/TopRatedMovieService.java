package com.turkoglu.themovie.modules.toprated.service;

import com.turkoglu.themovie.modules.toprated.dto.TopRatedMovieRequest;
import com.turkoglu.themovie.modules.toprated.dto.TopRatedMovieResponse;

import java.util.List;

public interface TopRatedMovieService {
    void saveMovies(TopRatedMovieRequest requests);
    TopRatedMovieResponse getMovieById(Long id);
    List<TopRatedMovieResponse> getAllMovies();
}
