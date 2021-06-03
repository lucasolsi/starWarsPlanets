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
    private int numberOfFilms;

    public PlanetResponse withName(String name)
    {
        this.name = name;
        return this;
    }

    public PlanetResponse withId(String id)
    {
        this.id = id;
        return this;
    }

    public PlanetResponse withTerrain(String terrain)
    {
        this.terrain = terrain;
        return this;
    }

    public PlanetResponse withClimate(String climate)
    {
        this.climate = climate;
        return this;
    }

    public PlanetResponse withNumberOfFilms(int numberOfFilms)
    {
        this.numberOfFilms = numberOfFilms;
        return this;
    }
}
