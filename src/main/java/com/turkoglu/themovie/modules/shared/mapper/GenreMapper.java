package com.turkoglu.themovie.modules.shared.mapper;

import com.turkoglu.themovie.modules.shared.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    default String map(Genre genre) {
        return genre.getName();
    }

    default Genre map(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return genre;
    }
}
