package com.turkoglu.themovie.modules.nowplaying.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class NowPlayingMovieRequest {
    @JsonProperty("dates")
    private DateRange dateRange;

    @JsonProperty("page")
    private int page;

    @JsonProperty("results")
    private List<NowPlayingMovieResult> results;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_results")
    private int totalResults;

    @Data
    public static class DateRange {
        @JsonProperty("maximum")
        private String maximum;

        @JsonProperty("minimum")
        private String minimum;
    }

    @Data
    public static class NowPlayingMovieResult {
        @JsonProperty("id")
        private int id;

        @JsonProperty("adult")
        private boolean adult;

        @JsonProperty("backdrop_path")
        private String backdropPath;

        @JsonProperty("genre_ids")
        private List<Integer> genreIds;

        @JsonProperty("original_language")
        private String originalLanguage;

        @JsonProperty("original_title")
        private String originalTitle;

        @JsonProperty("overview")
        private String overview;

        @JsonProperty("popularity")
        private double popularity;

        @JsonProperty("poster_path")
        private String posterPath;

        @JsonProperty("release_date")
        private String releaseDate;

        @JsonProperty("title")
        private String title;

        @JsonProperty("video")
        private boolean video;

        @JsonProperty("vote_average")
        private double voteAverage;

        @JsonProperty("vote_count")
        private int voteCount;
    }
}
