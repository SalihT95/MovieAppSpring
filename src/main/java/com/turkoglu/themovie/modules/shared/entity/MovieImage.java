package com.turkoglu.themovie.modules.shared.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String posterPath;
    private String backdropPath;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}