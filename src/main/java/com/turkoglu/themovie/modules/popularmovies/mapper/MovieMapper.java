package com.turkoglu.themovie.modules.popularmovies.mapper;

import com.turkoglu.themovie.modules.popularmovies.dto.MovieDto;
import com.turkoglu.themovie.modules.shared.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    // MovieDto -> Movie dönüşümü
    @Mapping(target = "genres", ignore = true) // genres'ı manuel işleyeceğiz
    Movie toMovie(MovieDto movieDto);

    // Movie -> MovieDto dönüşümü
    MovieDto toMovieDto(Movie movie);

    // MovieDto listesi -> Movie listesi dönüşümü
    List<Movie> toMovieList(List<MovieDto> movieDtos);

    // Movie listesi -> MovieDto listesi dönüşümü
    List<MovieDto> toMovieDtoList(List<Movie> movies);
}
