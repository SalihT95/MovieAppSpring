package com.turkoglu.themovie.modules.popularmovies.controller;

import com.turkoglu.themovie.modules.popularmovies.service.MovieService;
import com.turkoglu.themovie.modules.shared.entity.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/import")
    public String importMovies(@RequestBody String jsonData) {
        try {
            movieService.saveMovies(jsonData);
            return "Movies imported successfully";
        } catch (Exception e) {
            return "Error importing movies: " + e.getMessage();
        }
    }

    // Get all movies endpoint
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}