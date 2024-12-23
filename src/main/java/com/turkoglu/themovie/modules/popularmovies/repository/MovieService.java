package com.turkoglu.themovie.modules.popularmovies.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.turkoglu.themovie.modules.shared.entity.Genre;
import com.turkoglu.themovie.modules.shared.entity.Movie;

import java.util.List;

public interface MovieService {

    List<MoviesEntity> getMoviesFromTMDBAPI();

    List<Genre> getGenresFromJson(JsonNode genresNode);

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);
}