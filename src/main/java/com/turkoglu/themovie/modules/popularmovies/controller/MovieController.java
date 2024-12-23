package com.turkoglu.themovie.modules.popularmovies.controller;

import com.turkoglu.themovie.modules.popularmovies.service.MovieServiceImpl;
import com.turkoglu.themovie.modules.shared.entity.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieServiceImpl movieServiceImpl;

    public MovieController(MovieServiceImpl movieServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
    }

    @PostMapping("/import")
    public ResponseEntity<?> importMovies() {
        return ResponseEntity.ok(movieServiceImpl.getMoviesFromTMDBAPI());
    }

    // Get all movies endpoint
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieServiceImpl.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieServiceImpl.getMovieById(id));
    }
}