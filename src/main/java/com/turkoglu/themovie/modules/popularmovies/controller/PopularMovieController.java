package com.turkoglu.themovie.modules.popularmovies.controller;

import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieRequest;
import com.turkoglu.themovie.modules.popularmovies.dto.PopularMovieResponse;
import com.turkoglu.themovie.modules.popularmovies.service.PopularMovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PopularMovies")
@RequiredArgsConstructor
@Tag(name = "Popular")
public class PopularMovieController {

    private final PopularMovieService popularMovieService;

    @GetMapping("/GetAll")
    public ResponseEntity<List<PopularMovieResponse>> getAllMovies() {
        List<PopularMovieResponse> responses = popularMovieService.getAllMovies();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/Add")
    public ResponseEntity<List<PopularMovieResponse>> saveMovies(@RequestBody PopularMovieRequest movieRequest) {
        popularMovieService.saveMovies(movieRequest);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<PopularMovieResponse> getMovieById(@PathVariable Long id) {
        PopularMovieResponse response = popularMovieService.getMovieById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}