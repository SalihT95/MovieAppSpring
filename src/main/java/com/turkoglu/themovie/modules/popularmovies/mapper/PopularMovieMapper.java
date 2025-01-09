package com.turkoglu.themovie.modules.popularmovies.mapper;

import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieRequest;
import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieResponse;
import com.turkoglu.themovie.modules.shared.entity.Genre;
import com.turkoglu.themovie.modules.popularmovies.entity.PopularMovie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PopularMovieMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    PopularMovie toEntity(PopularMovieRequest.PopularMovieResult request);

    @Mapping(target = "genreNames", source = "genres", qualifiedByName = "mapGenresToNames")
    PopularMovieResponse toResponse(PopularMovie movie);

    @Named("mapGenresToNames")
    default List<String> mapGenresToNames(List<Genre> genres) {
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }
}