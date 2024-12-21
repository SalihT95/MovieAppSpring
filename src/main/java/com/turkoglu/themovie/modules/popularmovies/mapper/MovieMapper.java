package com.turkoglu.themovie.modules.popularmovies.mapper;

import com.turkoglu.themovie.modules.popularmovies.dto.MovieDto;
import com.turkoglu.themovie.modules.shared.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    Movie toEntity(MovieDto dto);
}