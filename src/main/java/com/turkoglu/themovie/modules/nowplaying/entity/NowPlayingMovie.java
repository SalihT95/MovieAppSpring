package com.turkoglu.themovie.modules.nowplaying.entity;

import com.turkoglu.themovie.modules.shared.entity.Genre;
import lombok.Data;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "now_playing_movies")
public class NowPlayingMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "vote_average")
    private Double voteAverage;

    @Column(name = "popularity")
    private Double popularity;

    @Column(name = "original_language")
    private String originalLanguage;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "now_playing_movie_genre",
            joinColumns = @JoinColumn(name = "now_playing_movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    @Column(name = "date_range_maximum")
    private String dateRangeMaximum;

    @Column(name = "date_range_minimum")
    private String dateRangeMinimum;
}
