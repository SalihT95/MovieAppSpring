package com.turkoglu.themovie.modules.nowplaying.service;

import com.turkoglu.themovie.modules.nowplaying.dto.NowPlayingMovieRequest;
import com.turkoglu.themovie.modules.nowplaying.dto.NowPlayingMovieResponse;
import com.turkoglu.themovie.modules.nowplaying.entity.NowPlayingMovie;
import com.turkoglu.themovie.modules.nowplaying.mapper.NowPlayingMovieMapper;
import com.turkoglu.themovie.modules.nowplaying.repository.NowPlayingMovieRepository;
import com.turkoglu.themovie.modules.shared.entity.Genre;
import com.turkoglu.themovie.modules.shared.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NowPlayingMovieServiceImpl implements NowPlayingMovieService {

    private final NowPlayingMovieRepository movieRepository;
    @Qualifier("nowPlayingMovieMapper")
    private final NowPlayingMovieMapper movieMapper;
    private final GenreService genreService;



    @Override
    public NowPlayingMovieResponse getMovieById(Long id) {
        NowPlayingMovie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        return movieMapper.toResponse(movie);
    }

    @Override
    public List<NowPlayingMovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(movieMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void saveMovies(NowPlayingMovieRequest movieRequest) {
        List<NowPlayingMovie> movies = movieRequest.getResults().stream()
                .map(movieResult -> {

                    NowPlayingMovie movie = movieMapper.toEntity(movieResult);

                    List<Genre> genres = genreService.findGenresByIds(movieResult.getGenreIds());
                    movie.setGenres(genres);

                    movie.setDateRangeMaximum(movieRequest.getDateRange().getMaximum());
                    movie.setDateRangeMinimum(movieRequest.getDateRange().getMinimum());

                    return movie;
                })
                .collect(Collectors.toList());

        movieRepository.saveAll(movies);
    }



}
