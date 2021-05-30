package com.java.b2w.lucas.starwarsplanets.service.impl;

import com.java.b2w.lucas.starwarsplanets.model.entity.PlanetEntity;
import com.java.b2w.lucas.starwarsplanets.model.response.PlanetResponse;
import com.java.b2w.lucas.starwarsplanets.repository.PlanetRepository;
import com.java.b2w.lucas.starwarsplanets.service.PlanetService;
import com.java.b2w.lucas.starwarsplanets.shared.dto.PlanetDto;
import com.java.b2w.lucas.starwarsplanets.utils.RestClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService
{

    @Autowired
    PlanetRepository planetRepository;

    @Override
    public PlanetResponse createPlanet(PlanetDto planet) throws Exception
    {
        if (planetRepository.findByName(planet.getName()) != null) throw new RuntimeException("Planet with name "
                + planet.getName() + "already exists");

        ModelMapper modelMapper = new ModelMapper();
        PlanetEntity planetEntity = modelMapper.map(planet, PlanetEntity.class);

        int numberOfFilmsFromApi = new RestClient().findNumberOfFilms(planet.getName());

        return null;
    }

    @Override
    public List<PlanetDto> getAllPlanets()
    {
        return null;
    }

    @Override
    public PlanetDto findPlanetByName(String name)
    {
        return null;
    }

    @Override
    public PlanetDto findPlanetById(String planetId)
    {
        return null;
    }

    @Override
    public void deletePlanet(String planetId)
    {

    }
}
