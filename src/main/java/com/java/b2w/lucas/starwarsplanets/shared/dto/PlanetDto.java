package com.java.b2w.lucas.starwarsplanets.shared.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlanetDto implements Serializable
{
    private static final long serialVersionUID = -4414893148885140785L;
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private int numberOfFilms;
}
