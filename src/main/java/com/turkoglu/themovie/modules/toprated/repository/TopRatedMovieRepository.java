package com.turkoglu.themovie.modules.toprated.repository;

import com.turkoglu.themovie.modules.toprated.entity.TopRatedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopRatedMovieRepository extends JpaRepository<TopRatedMovie,Long> {
}
