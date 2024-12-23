package com.turkoglu.themovie.modules.popularmovies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TMDBMovieResponse {
    @JsonProperty("page")
    private int page;
    @JsonProperty("results")
    private List<Movies> results;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("total_results")
    private int totalResults;

    public List<Movies> getResults() {
        return results;
    }
}