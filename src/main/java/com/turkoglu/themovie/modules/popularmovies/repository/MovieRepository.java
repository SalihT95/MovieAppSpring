package com.turkoglu.themovie.modules.popularmovies.repository;

import com.turkoglu.themovie.modules.popularmovies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}