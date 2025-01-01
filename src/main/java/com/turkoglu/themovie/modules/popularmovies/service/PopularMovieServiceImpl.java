package com.turkoglu.themovie.modules.popularmovies.service;

import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieResponse;
import com.turkoglu.themovie.modules.popularmovies.entity.PopularMovie;
import com.turkoglu.themovie.modules.shared.entity.Genre;
import com.turkoglu.themovie.modules.popularmovies.mapper.PopularMovieMapper;
import com.turkoglu.themovie.modules.popularmovies.repository.PopularMovieRepository;
import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieRequest;
import com.turkoglu.themovie.modules.shared.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PopularMovieServiceImpl implements PopularMovieService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final PopularMovieRepository popularMovieRepository;
    private final PopularMovieMapper popularMovieMapper;
    private final GenreService genreService;

    @Override
    public List<PopularMovieResponse> saveMovies(List<PopularMovieRequest> requests) {
        List<PopularMovie> movies = requests.stream()
                .map(request -> {
                    PopularMovie movie = popularMovieMapper.toEntity(request);
                    List<Genre> genres = genreService.findGenresByIds(request.getGenreIds());
                    movie.setGenres(genres);
                    return movie;
                })
                .collect(Collectors.toList());

        List<PopularMovie> savedMovies = popularMovieRepository.saveAll(movies);

        return savedMovies.stream()
                .map(popularMovieMapper::toResponse)
                .collect(Collectors.toList());
    }


    @Override
    public PopularMovieResponse saveMovie(PopularMovieRequest request) {
        PopularMovie movie = popularMovieMapper.toEntity(request);
        List<Genre> genres = genreService.findGenresByIds(request.getGenreIds());
        movie.setGenres(genres);
        PopularMovie savedMovie = popularMovieRepository.save(movie);
        return popularMovieMapper.toResponse(savedMovie);
    }

    @Override
    public PopularMovieResponse getMovieById(Long id) {
        PopularMovie movie = popularMovieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PopularMovie not found with id: " + id));
        return popularMovieMapper.toResponse(movie);
    }

    @Override
    public List<PopularMovieResponse> getAllMovies() {
        return popularMovieRepository.findAll().stream()
                .map(popularMovieMapper::toResponse)
                .collect(Collectors.toList());
    }

}