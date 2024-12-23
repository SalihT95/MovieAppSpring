package com.turkoglu.themovie.modules.shared.repository;

import com.turkoglu.themovie.modules.shared.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}