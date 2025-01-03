package com.turkoglu.themovie.modules.shared.repository;

import com.turkoglu.themovie.modules.shared.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}