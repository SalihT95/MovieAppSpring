package com.turkoglu.themovie.modules.nowplaying.dto;

import lombok.Data;
import java.util.List;

@Data
public class NowPlayingMovieResponse {
    private Long id;
    private String title;
    private String overview;
    private double popularity;
    private double voteAverage;
    private String releaseDate;
    private String posterPath;
    private List<String> genreNames;
    private DateRange dateRange;

    @Data
    public static class DateRange {
        private String maximum;
        private String minimum;
    }
}
