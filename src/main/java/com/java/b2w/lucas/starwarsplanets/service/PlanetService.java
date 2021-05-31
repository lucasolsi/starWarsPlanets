package com.java.b2w.lucas.starwarsplanets.service;

import com.java.b2w.lucas.starwarsplanets.shared.dto.PlanetDto;

import java.util.List;

public interface PlanetService
{
    PlanetDto createPlanet(PlanetDto planetDto) throws Exception;
    List<PlanetDto> getAllPlanets(int page, int limit);
    PlanetDto findPlanetByName(String name);
    PlanetDto findPlanetById(String planetId);
    void deletePlanetById(String planetId);
    void deletePlanetByName(String planetName);

}
