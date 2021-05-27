package com.java.b2w.lucas.starwarsplanets.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
public class PlanetEntity
{

    @MongoId
    private Long id;
    private String name;
    private String climate;
    private String terrain;
    private int numberOfFilms;

}
