package com.turkoglu.themovie.modules.nowplaying.service;

import com.turkoglu.themovie.modules.nowplaying.dto.NowPlayingMovieRequest;
import com.turkoglu.themovie.modules.nowplaying.dto.NowPlayingMovieResponse;

import java.util.List;

public interface NowPlayingMovieService {
    void saveMovies(NowPlayingMovieRequest movieRequests);
    NowPlayingMovieResponse getMovieById(Long id);
    List<NowPlayingMovieResponse> getAllMovies();
}
