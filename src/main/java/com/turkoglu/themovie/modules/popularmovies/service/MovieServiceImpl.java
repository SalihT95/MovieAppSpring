package com.turkoglu.themovie.modules.popularmovies.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.turkoglu.themovie.modules.popularmovies.dto.TMDBMovieResponse;
import com.turkoglu.themovie.modules.popularmovies.mapper.MovieMapper;
import com.turkoglu.themovie.modules.shared.entity.Genre;
import com.turkoglu.themovie.modules.shared.entity.Movie;
import com.turkoglu.themovie.modules.shared.repository.GenreRepository;
import com.turkoglu.themovie.modules.shared.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;

    @Value("${tmdb.api.key}")
    private String key;

    @Override
    public List<MoviesEntity> getMoviesFromTMDBAPI() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1";

        // HTTP başlıklarını oluştur
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", "Bearer " + key);

        // HTTP isteği için entity oluştur
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // GET isteği gönder
        ResponseEntity<TMDBMovieResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                TMDBMovieResponse.class
        );
        return movieMapper.mapToMoviesEntity(response.getBody().getResults());
    }

    @Override
    public List<Genre> getGenresFromJson(JsonNode genresNode) {
        List<Genre> genres = new ArrayList<>();
        genresNode.forEach(genreNode -> {
            int genreId = genreNode.intValue();
            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));
            genres.add(genre);
        });
        return genres;
    }


    /*public void saveMovies(String jsonData) throws Exception {
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
    }*/
    // Get all movies from the database
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Get movie by ID from the database
    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + id));
    }

}