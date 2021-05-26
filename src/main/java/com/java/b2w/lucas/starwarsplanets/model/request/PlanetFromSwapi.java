package com.java.b2w.lucas.starwarsplanets.model.request;

import lombok.Data;

import java.util.List;

@Data
public class PlanetFromSwapi
{
    String diameter;
    String climate;
    String surface_water;
    String name;
    String url;
    String created;
    String rotation_period;
    String edited;
    String terrain;
    String gravity;
    String orbital_period;
    List<String> films;
    List<String> residents;
    String population;
}
