package com.turkoglu.themovie.modules.shared.entity;

import com.turkoglu.themovie.modules.popularmovies.entity.PopularMovie;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<PopularMovie> movies;
}