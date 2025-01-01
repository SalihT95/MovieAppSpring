package com.turkoglu.themovie.modules.popularmovies.repository;

import com.turkoglu.themovie.modules.popularmovies.entity.PopularMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopularMovieRepository extends JpaRepository<PopularMovie, Long> {
}