package com.turkoglu.themovie.modules.popularmovies.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkoglu.themovie.modules.popularmovies.dto.MovieDto;
import com.turkoglu.themovie.modules.popularmovies.mapper.MovieMapper;
import com.turkoglu.themovie.modules.shared.entity.Movie;
import com.turkoglu.themovie.modules.shared.entity.Genre;
import com.turkoglu.themovie.modules.shared.repository.GenreRepository;
import com.turkoglu.themovie.modules.shared.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper; // Mapper'ı enjekte ettik

    @Override
    public void saveMovies(String jsonData) throws Exception {
        JsonNode jsonNode = new ObjectMapper().readTree(jsonData);
        Iterator<JsonNode> results = jsonNode.get("results").elements();
        while (results.hasNext()) {
            JsonNode movieNode = results.next();

            // MovieDto'yu manuel olarak mapliyoruz
            MovieDto movieDto = new MovieDto();
            movieDto.setTitle(movieNode.get("title").asText());
            movieDto.setOriginalTitle(movieNode.get("original_title").asText());
            movieDto.setOverview(movieNode.get("overview").asText());
            movieDto.setReleaseDate(movieNode.get("release_date").asText());
            movieDto.setPopularity(movieNode.get("popularity").asDouble());
            movieDto.setVoteAverage(movieNode.get("vote_average").asDouble());
            movieDto.setVoteCount(movieNode.get("vote_count").asInt());
            movieDto.setAdult(movieNode.get("adult").asBoolean());
            movieDto.setVideo(movieNode.get("video").asBoolean());

            // Genre'leri işliyoruz
            List<Genre> genres = getGenresFromJson(movieNode.get("genre_ids"));
            movieDto.setGenres(genres);

            // MapStruct ile Movie'yi oluşturuyoruz
            Movie movie = movieMapper.toMovie(movieDto);
            movieRepository.save(movie);
        }
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