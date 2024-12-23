package com.turkoglu.themovie.modules.popularmovies.controller;

import com.turkoglu.themovie.modules.popularmovies.service.MovieServiceImpl;
import com.turkoglu.themovie.modules.shared.entity.Movie;
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
    public String importMovies(@RequestBody String jsonData) {
        try {
            movieServiceImpl.saveMovies(jsonData);
            return "Movies imported successfully";
        } catch (Exception e) {
            return "Error importing movies: " + e.getMessage();
        }
    }

    // Get all movies endpoint
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieServiceImpl.getAllMovies();
    }
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieServiceImpl.getMovieById(id); // Eğer film bulunamazsa, IllegalArgumentException fırlatılabilir.
    }

}