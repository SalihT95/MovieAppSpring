package com.turkoglu.themovie.modules.toprated.mapper;

import com.turkoglu.themovie.modules.shared.entity.Genre;
import com.turkoglu.themovie.modules.toprated.dto.TopRatedMovieRequest;
import com.turkoglu.themovie.modules.toprated.dto.TopRatedMovieResponse;
import com.turkoglu.themovie.modules.toprated.entity.TopRatedMovie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TopRatedMovieMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    TopRatedMovie toEntity(TopRatedMovieRequest.TopRatedMovieResult request);

    @Mapping(target = "genreNames", source = "genres", qualifiedByName = "mapGenresToNames")
    TopRatedMovieResponse toResponse(TopRatedMovie movie);

    @Named("mapGenresToNames")
    default List<String> mapGenresToNames(List<Genre> genres) {
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }
}