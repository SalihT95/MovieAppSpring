package com.turkoglu.themovie.modules.popularmovies.mapper;

import com.turkoglu.themovie.modules.popularmovies.dto.MovieDto;
import com.turkoglu.themovie.modules.popularmovies.dto.Movies;
import com.turkoglu.themovie.modules.popularmovies.dto.TMDBMovieResponse;
import com.turkoglu.themovie.modules.popularmovies.service.MoviesEntity;
import com.turkoglu.themovie.modules.shared.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "genres", ignore = true)
    Movie toMovie(MovieDto movieDto);

    MovieDto toMovieDto(Movie movie);

    List<Movie> toMovieList(List<MovieDto> movieDtos);

    List<MovieDto> toMovieDtoList(List<Movie> movies);

    List<MoviesEntity> mapToMoviesEntity(List<Movies> results);
}