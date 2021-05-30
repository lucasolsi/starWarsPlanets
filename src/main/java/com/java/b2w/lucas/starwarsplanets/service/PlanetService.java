package com.java.b2w.lucas.starwarsplanets.service;

import com.java.b2w.lucas.starwarsplanets.model.response.PlanetResponse;
import com.java.b2w.lucas.starwarsplanets.shared.dto.PlanetDto;

import java.util.List;

public interface PlanetService
{
    PlanetResponse createPlanet(PlanetDto planetDto) throws Exception;
    List<PlanetDto> getAllPlanets();
    PlanetDto findPlanetByName(String name);
    PlanetDto findPlanetById(String planetId);
    void deletePlanet(String planetId);

}
