package com.turkoglu.themovie.modules.shared.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MovieGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer genreId;
    private String genreName;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}