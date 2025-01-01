package com.turkoglu.themovie.modules.popularmovies.mapper;

import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieRequest;
import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieResponse;
import com.turkoglu.themovie.modules.shared.entity.Genre;
import com.turkoglu.themovie.modules.popularmovies.entity.PopularMovie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PopularMovieMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    PopularMovie toEntity(PopularMovieRequest request);

    @Mapping(target = "genreNames", expression = "java(mapGenres(movie.getGenres()))")
    PopularMovieResponse toResponse(PopularMovie movie);

    default List<String> mapGenres(List<Genre> genres) {
        if (genres == null) {
            return null;
        }
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }
}