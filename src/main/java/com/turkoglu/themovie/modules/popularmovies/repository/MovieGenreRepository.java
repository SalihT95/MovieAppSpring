package com.turkoglu.themovie.modules.popularmovies.repository;

import com.turkoglu.themovie.modules.shared.entity.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
}