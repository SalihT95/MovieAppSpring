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

    private final PopularMovieRepository popularMovieRepository;
    private final PopularMovieMapper popularMovieMapper;
    private final GenreService genreService;

    @Override
    public void saveMovies(PopularMovieRequest movieRequest) {
        List<PopularMovie> movies = movieRequest.getResults().stream()
                .map(movieResult -> {

                    PopularMovie movie = popularMovieMapper.toEntity(movieResult);

                    List<Genre> genres = genreService.findGenresByIds(movieResult.getGenreIds());
                    movie.setGenres(genres);

                    return movie;
                })
                .collect(Collectors.toList());

        popularMovieRepository.saveAll(movies);
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