package com.turkoglu.themovie.modules.popularmovies.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkoglu.themovie.modules.shared.entity.Movie;
import com.turkoglu.themovie.modules.shared.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveMovies(String jsonData) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(jsonData);

        Iterator<JsonNode> results = jsonNode.get("results").elements();
        while (results.hasNext()) {
            JsonNode movieNode = results.next();

            Movie movie = new Movie();
            movie.setTitle(movieNode.get("title").asText());
            movie.setOriginalTitle(movieNode.get("original_title").asText());
            movie.setOverview(movieNode.get("overview").asText());
            movie.setReleaseDate(movieNode.get("release_date").asText());
            movie.setPopularity(movieNode.get("popularity").asDouble());
            movie.setVoteAverage(movieNode.get("vote_average").asDouble());
            movie.setVoteCount(movieNode.get("vote_count").asInt());
            movie.setAdult(movieNode.get("adult").asBoolean());
            movie.setVideo(movieNode.get("video").asBoolean());

            movieRepository.save(movie);
        }
    }
    // Get all movies from the database
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

}