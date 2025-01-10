package com.turkoglu.themovie.modules.toprated.controller;

import com.turkoglu.themovie.modules.toprated.dto.TopRatedMovieRequest;
import com.turkoglu.themovie.modules.toprated.dto.TopRatedMovieResponse;
import com.turkoglu.themovie.modules.toprated.service.TopRatedMovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TopRatedMovies")
@RequiredArgsConstructor
@Tag(name = "TopRated")
public class TopRatedMovieController {

    private final TopRatedMovieService topRatedMovieService;

    @GetMapping("/GetAll")
    public ResponseEntity<List<TopRatedMovieResponse>> getAllMovies() {
        List<TopRatedMovieResponse> responses = topRatedMovieService.getAllMovies();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/Add")
    public ResponseEntity<List<TopRatedMovieResponse>> saveMovies(@RequestBody TopRatedMovieRequest movieRequest) {
        topRatedMovieService.saveMovies(movieRequest);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TopRatedMovieResponse> getMovieById(@PathVariable Long id) {
        TopRatedMovieResponse response = topRatedMovieService.getMovieById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}