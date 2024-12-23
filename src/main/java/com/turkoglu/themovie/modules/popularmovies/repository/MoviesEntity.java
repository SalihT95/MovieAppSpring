package com.turkoglu.themovie.modules.popularmovies.service;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "moviesEntity")
public class MoviesEntity {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "adult", nullable = false)
    private boolean adult;

    @Column(name = "backdrop_path")
    private String backdropPath;

    @Column(name = "genre_ids")
    private String genreIds; // JSON formatında saklanabilir (örneğin, "[28,14,35]")

    @Column(name = "original_language")
    private String originalLanguage;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "overview", length = 1000) // Uzun açıklamalar için genişletildi.
    private String overview;

    @Column(name = "popularity")
    private double popularity;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "title")
    private String title;

    @Column(name = "video", nullable = false)
    private boolean video;

    @Column(name = "vote_average")
    private double voteAverage;

    @Column(name = "vote_count")
    private int voteCount;
}