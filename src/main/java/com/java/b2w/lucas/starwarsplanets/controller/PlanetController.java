package com.java.b2w.lucas.starwarsplanets.controller;

import com.java.b2w.lucas.starwarsplanets.model.request.PlanetRequest;
import com.java.b2w.lucas.starwarsplanets.model.response.PlanetResponse;
import com.java.b2w.lucas.starwarsplanets.service.PlanetService;
import com.java.b2w.lucas.starwarsplanets.shared.dto.PlanetDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/planet")
public class PlanetController
{

    @Autowired
    PlanetService planetService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanetResponse createPlanet(@RequestBody PlanetRequest planetRequest)
    {
        ModelMapper modelMapper = new ModelMapper();
        PlanetDto planetDto = modelMapper.map(planetRequest, PlanetDto.class);

        PlanetDto createdPlanetDto = planetService.createPlanet(planetDto);
        return modelMapper.map(createdPlanetDto, PlanetResponse.class);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlanetResponse> getAllPlanets(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "5") int limit)
    {
        ModelMapper modelMapper = new ModelMapper();
        List<PlanetResponse> planetResponseList = new ArrayList<>();
        List<PlanetDto> planetDtoList = planetService.getAllPlanets(page, limit);

        planetDtoList.forEach(planetDto -> planetResponseList.add(modelMapper.map(planetDto, PlanetResponse.class)));

        return planetResponseList;
    }

    @GetMapping(path = "/id/{planetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanetResponse getPlanetById(@PathVariable String planetId)
    {
        PlanetDto planetDto = planetService.findPlanetById(planetId);
        return new ModelMapper().map(planetDto, PlanetResponse.class);
    }

    @GetMapping(path = "/name/{planetName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanetResponse getPlanetByName(@PathVariable String planetName)
    {
        PlanetDto planetDto = planetService.findPlanetByName(planetName);
        return new ModelMapper().map(planetDto, PlanetResponse.class);
    }

    @DeleteMapping(path = "/name/{planetName}")
    public void deletePlanetByName(@PathVariable String planetName)
    {
        planetService.deletePlanetByName(planetName);
    }

    @DeleteMapping(path = "/id/{planetId}")
    public void deletePlanetById(@PathVariable String planetId)
    {
        planetService.deletePlanetById(planetId);
    }
}
