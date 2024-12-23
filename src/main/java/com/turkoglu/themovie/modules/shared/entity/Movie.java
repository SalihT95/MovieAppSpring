package com.turkoglu.themovie.modules.shared.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String originalTitle;
    @Column(length = 500)  // Örneğin, 500 karakterle sınırlandırabilirsiniz
    private String overview;
    private String releaseDate;
    private Double popularity;
    private Double voteAverage;
    private Integer voteCount;
    private Boolean adult;
    private Boolean video;
    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;
}