package com.turkoglu.themovie.modules.nowplaying.controller;

import com.turkoglu.themovie.modules.nowplaying.dto.NowPlayingMovieRequest;
import com.turkoglu.themovie.modules.nowplaying.dto.NowPlayingMovieResponse;
import com.turkoglu.themovie.modules.nowplaying.service.NowPlayingMovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/NowPlayingMovies")
@RequiredArgsConstructor
@Tag(name = "NowPlaying")
public class NowPlayingMovieController {

    private final NowPlayingMovieService nowPlayingMovieService;

    @GetMapping("/GetAll")
    public ResponseEntity<List<NowPlayingMovieResponse>> getAllMovies() {
        List<NowPlayingMovieResponse> allMovies = nowPlayingMovieService.getAllMovies();
        return ResponseEntity.ok(allMovies);
    }

    @PostMapping("/Add")
    public ResponseEntity<Void> saveMovies(@RequestBody NowPlayingMovieRequest movieRequest) {
        nowPlayingMovieService.saveMovies(movieRequest);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<NowPlayingMovieResponse> getMovieById(@PathVariable Long id) {
        NowPlayingMovieResponse movieResponse = nowPlayingMovieService.getMovieById(id);
        return ResponseEntity.ok(movieResponse);
    }
}

