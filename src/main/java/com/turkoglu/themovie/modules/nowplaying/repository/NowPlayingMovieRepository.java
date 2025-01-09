package com.turkoglu.themovie.modules.nowplaying.repository;

import com.turkoglu.themovie.modules.nowplaying.entity.NowPlayingMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NowPlayingMovieRepository extends JpaRepository<NowPlayingMovie, Long> {
}
