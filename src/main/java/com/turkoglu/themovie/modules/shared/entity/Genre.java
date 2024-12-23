package com.turkoglu.themovie.modules.shared.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Genre {

    @Id
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;
}