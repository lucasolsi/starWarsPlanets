package com.java.b2w.lucas.starwarsplanets.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PlanetEntity
{

    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private int numberOfFilms;

}
