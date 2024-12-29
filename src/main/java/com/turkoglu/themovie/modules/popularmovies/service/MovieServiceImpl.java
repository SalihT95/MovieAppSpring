package com.turkoglu.themovie.modules.popularmovies.service;

import com.turkoglu.themovie.modules.popularmovies.dto.MovieResponse;
import com.turkoglu.themovie.modules.popularmovies.entity.Genre;
import com.turkoglu.themovie.modules.popularmovies.entity.Movie;
import com.turkoglu.themovie.modules.popularmovies.mapper.MovieMapper;
import com.turkoglu.themovie.modules.popularmovies.repository.GenreRepository;
import com.turkoglu.themovie.modules.popularmovies.repository.MovieRepository;
import com.turkoglu.themovie.modules.popularmovies.dto.MovieRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;
    private final GenreService genreService;

    // Çoklu film kaydetme
//    @Override
//    public List<MovieResponse> saveMovies(List<MovieRequest> requests) {
//        // MovieRequest'leri Movie entity'lerine dönüştür
//        List<Movie> movies = requests.stream()
//                .map(movieMapper::toEntity)  // MapStruct kullanarak dönüştür
//                .collect(Collectors.toList());
//
//        // Filmleri kaydet
//        List<Movie> savedMovies = movieRepository.saveAll(movies);
//
//        // Kaydedilen filmleri MovieResponse formatına dönüştür
//        return movieMapper.toResponseList(savedMovies);  // MapStruct kullanarak dönüşüm
//    }

    @Override
    public List<MovieResponse> saveMovies(List<MovieRequest> requests) {
        // DTO'ları entity'lere dönüştür
        List<Movie> movies = requests.stream()
                .map(request -> {
                    Movie movie = movieMapper.toEntity(request);
                    List<Genre> genres = genreService.findGenresByIds(request.getGenreIds());
                    movie.setGenres(genres);
                    return movie;
                })
                .collect(Collectors.toList());

        // Veritabanına kaydet
        List<Movie> savedMovies = movieRepository.saveAll(movies);

        // Entity'leri DTO'lara dönüştür ve döndür
        return savedMovies.stream()
                .map(movieMapper::toResponse)
                .collect(Collectors.toList());
    }


    @Override
    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = movieMapper.toEntity(request);
        List<Genre> genres = genreService.findGenresByIds(request.getGenreIds());
        movie.setGenres(genres);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toResponse(savedMovie);
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        return movieMapper.toResponse(movie);
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(movieMapper::toResponse)
                .collect(Collectors.toList());
    }

}