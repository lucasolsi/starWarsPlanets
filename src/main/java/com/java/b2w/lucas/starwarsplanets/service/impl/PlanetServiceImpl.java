package com.java.b2w.lucas.starwarsplanets.service.impl;

import com.java.b2w.lucas.starwarsplanets.model.entity.PlanetEntity;
import com.java.b2w.lucas.starwarsplanets.repository.PlanetRepository;
import com.java.b2w.lucas.starwarsplanets.service.PlanetService;
import com.java.b2w.lucas.starwarsplanets.shared.dto.PlanetDto;
import com.java.b2w.lucas.starwarsplanets.utils.RestClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService
{

    @Autowired
    PlanetRepository planetRepository;

    @Override
    public PlanetDto createPlanet(PlanetDto planet) throws Exception
    {
        if (planetRepository.findByName(planet.getName()) != null) throw new RuntimeException("Planet with name "
                + planet.getName() + "already exists");

        ModelMapper modelMapper = new ModelMapper();
        PlanetEntity planetEntity = modelMapper.map(planet, PlanetEntity.class);

        int numberOfFilmsFromApi = RestClient.findNumberOfFilms(planet.getName());
        planetEntity.setNumberOfFilms(numberOfFilmsFromApi);

        PlanetEntity planetCreated = planetRepository.save(planetEntity);

        return modelMapper.map(planetCreated, PlanetDto.class);
    }

    @Override
    public List<PlanetDto> getAllPlanets(int page, int limit)
    {
        List<PlanetDto> planetDtoList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);

        Page<PlanetEntity> planetEntityPage = planetRepository.findAll(pageable);
        List<PlanetEntity> planets = planetEntityPage.getContent();

        ModelMapper modelMapper = new ModelMapper();
        for (PlanetEntity eachPlanet : planets)
        {
            PlanetDto planetDto = modelMapper.map(eachPlanet, PlanetDto.class);
            planetDtoList.add(planetDto);
        }

        return planetDtoList;
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
