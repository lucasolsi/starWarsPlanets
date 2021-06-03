package com.java.b2w.lucas.starwarsplanets.model.request;

import lombok.Data;

@Data
public class PlanetRequest
{
    private String name;
    private String terrain;
    private String climate;

    public PlanetRequest withName(String name)
    {
        this.name = name;
        return this;
    }

    public PlanetRequest withTerrain(String terrain)
    {
        this.terrain = terrain;
        return this;
    }

    public PlanetRequest withClimate(String climate)
    {
        this.climate = climate;
        return this;
    }
}
