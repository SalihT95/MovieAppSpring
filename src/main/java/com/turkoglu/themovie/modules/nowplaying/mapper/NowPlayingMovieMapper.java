package com.turkoglu.themovie.modules.nowplaying.mapper;

import com.turkoglu.themovie.modules.nowplaying.dto.NowPlayingMovieRequest;
import com.turkoglu.themovie.modules.nowplaying.dto.NowPlayingMovieResponse;
import com.turkoglu.themovie.modules.nowplaying.entity.NowPlayingMovie;
import com.turkoglu.themovie.modules.shared.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface NowPlayingMovieMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true), // ID'nin otomatik üretilmesi için ignore
            @Mapping(target = "dateRangeMaximum", ignore = true), // Manuel set edilecek
            @Mapping(target = "dateRangeMinimum", ignore = true)  // Manuel set edilecek
    })
    NowPlayingMovie toEntity(NowPlayingMovieRequest.NowPlayingMovieResult movieResult);

    @Mappings({
            @Mapping(target = "dateRange.maximum", source = "dateRangeMaximum"),
            @Mapping(target = "dateRange.minimum", source = "dateRangeMinimum"),
            @Mapping(target = "genreNames", source = "genres", qualifiedByName = "mapGenresToNames")
    })
    NowPlayingMovieResponse toResponse(NowPlayingMovie movie);

    @Named("mapGenresToNames")
    default List<String> mapGenresToNames(List<Genre> genres) {
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }
}
