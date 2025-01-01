package com.turkoglu.themovie.modules.shared.service;

import com.turkoglu.themovie.modules.shared.entity.Genre;
import com.turkoglu.themovie.modules.shared.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    public List<Genre> findGenresByIds(List<Integer> genreIds) {
        return genreRepository.findAllById(genreIds);
    }
}