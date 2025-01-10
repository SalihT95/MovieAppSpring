package com.turkoglu.themovie.modules.toprated.service;

import com.turkoglu.themovie.modules.shared.entity.Genre;
import com.turkoglu.themovie.modules.shared.service.GenreService;
import com.turkoglu.themovie.modules.toprated.dto.TopRatedMovieRequest;
import com.turkoglu.themovie.modules.toprated.dto.TopRatedMovieResponse;
import com.turkoglu.themovie.modules.toprated.entity.TopRatedMovie;
import com.turkoglu.themovie.modules.toprated.mapper.TopRatedMovieMapper;
import com.turkoglu.themovie.modules.toprated.repository.TopRatedMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopRatedMovieServiceImpl implements TopRatedMovieService {

    private final TopRatedMovieRepository topRatedMovieRepository;
    private final TopRatedMovieMapper topRatedMovieMapper;
    private final GenreService genreService;

    @Override
    public void saveMovies(TopRatedMovieRequest movieRequest) {
        List<TopRatedMovie> movies = movieRequest.getResults().stream()
                .map(movieResult -> {

                    TopRatedMovie movie = topRatedMovieMapper.toEntity(movieResult);

                    List<Genre> genres = genreService.findGenresByIds(movieResult.getGenreIds());
                    movie.setGenres(genres);

                    return movie;
                })
                .collect(Collectors.toList());

        topRatedMovieRepository.saveAll(movies);
    }

    @Override
    public TopRatedMovieResponse getMovieById(Long id) {
        TopRatedMovie movie = topRatedMovieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PopularMovie not found with id: " + id));
        return topRatedMovieMapper.toResponse(movie);
    }

    @Override
    public List<TopRatedMovieResponse> getAllMovies() {
        return topRatedMovieRepository.findAll().stream()
                .map(topRatedMovieMapper::toResponse)
                .collect(Collectors.toList());
    }

}
