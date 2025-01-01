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
@Tag(name = "Movies", description = "Film ile ilgili işlemler")
public class PopularMovieController {

    private final PopularMovieService popularMovieService;

    @GetMapping("GetAll")
    @Operation(summary = "Tüm filmleri getir", description = "Veritabanındaki tüm filmleri döner.")
    public ResponseEntity<List<PopularMovieResponse>> getAllMovies() {
        List<PopularMovieResponse> responses = popularMovieService.getAllMovies();
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    @Operation(summary = "Toplu film oluştur", description = "Birden fazla film bilgisi ile toplu film oluşturur.")
    public ResponseEntity<List<PopularMovieResponse>> bulkCreateMovies(@RequestBody List<PopularMovieRequest> requests) {
        List<PopularMovieResponse> responses = popularMovieService.saveMovies(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }

    @PostMapping("/add")
    @Operation(summary = "Tek film oluştur", description = "Bir film oluşturur.")
    public ResponseEntity<PopularMovieResponse> createMovie(@RequestBody PopularMovieRequest request) {
        PopularMovieResponse response = popularMovieService.saveMovie(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Id ile film getirir", description = "Bir film getirir.")
    public ResponseEntity<PopularMovieResponse> getMovieById(@PathVariable Long id) {
        PopularMovieResponse response = popularMovieService.getMovieById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}