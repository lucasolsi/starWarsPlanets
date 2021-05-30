package com.java.b2w.lucas.starwarsplanets.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanetResponse
{
    private String name;
    private String id;
    private String terrain;
    private String climate;
    private String numberOfFilms;
}
