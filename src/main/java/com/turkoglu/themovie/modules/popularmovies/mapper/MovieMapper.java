package com.turkoglu.themovie.modules.popularmovies.mapper;

import com.turkoglu.themovie.modules.popularmovies.dto.MovieRequest;
import com.turkoglu.themovie.modules.popularmovies.dto.MovieResponse;
import com.turkoglu.themovie.modules.popularmovies.entity.Genre;
import com.turkoglu.themovie.modules.popularmovies.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)  // ID'yi ignore et, çünkü veritabanında otomatik oluşturulacak
    })
    Movie toEntity(MovieRequest request);

    @Mapping(target = "genreNames", expression = "java(mapGenres(movie.getGenres()))")
    MovieResponse toResponse(Movie movie);

    List<MovieResponse> toResponseList(List<Movie> movies);

    default List<String> mapGenres(List<Genre> genres) {
        if (genres == null) {
            return null;
        }
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }
}
