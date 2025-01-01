package com.turkoglu.themovie.modules.popularmovies.repository;

import com.turkoglu.themovie.modules.popularmovies.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}