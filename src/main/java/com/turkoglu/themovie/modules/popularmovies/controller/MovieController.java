package com.turkoglu.themovie.modules.popularmovies.controller;

import com.turkoglu.themovie.modules.popularmovies.dto.MovieRequest;
import com.turkoglu.themovie.modules.popularmovies.dto.MovieResponse;
import com.turkoglu.themovie.modules.popularmovies.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Tag(name = "Movies", description = "Film ile ilgili işlemler")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("GetAll")
    @Operation(summary = "Tüm filmleri getir", description = "Veritabanındaki tüm filmleri döner.")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> responses = movieService.getAllMovies();
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    @Operation(summary = "Toplu film oluştur", description = "Birden fazla film bilgisi ile toplu film oluşturur.")
    public ResponseEntity<List<MovieResponse>> bulkCreateMovies(@RequestBody List<MovieRequest> requests) {
        List<MovieResponse> responses = movieService.saveMovies(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }

    // Çoklu film kaydetme
//    @PostMapping
//    @Operation(summary = "Toplu film oluştur v2", description = "Birden fazla film bilgisi ile toplu film oluşturur.")
//    public ResponseEntity<List<MovieResponse>> saveMovies(@RequestBody List<MovieRequest> requests) {
//        List<MovieResponse> responses = movieService.saveMovies(requests);
//        return ResponseEntity.ok(responses);
//    }

    @PostMapping("/add")
    @Operation(summary = "Tek film oluştur", description = "Bir film oluşturur.")
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest request) {
        MovieResponse response = movieService.createMovie(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Id ile film getirir", description = "Bir film getirir.")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        MovieResponse response = movieService.getMovieById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}