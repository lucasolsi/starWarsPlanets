package com.java.b2w.lucas.starwarsplanets.controller;

import com.java.b2w.lucas.starwarsplanets.model.request.PlanetRequest;
import com.java.b2w.lucas.starwarsplanets.model.response.PlanetResponse;
import com.java.b2w.lucas.starwarsplanets.service.PlanetService;
import com.java.b2w.lucas.starwarsplanets.shared.dto.PlanetDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
    public PlanetResponse createPlanet(@RequestBody PlanetRequest planetRequest) throws Exception
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

        for (PlanetDto eachPlanet : planetDtoList)
        {
            planetResponseList.add(modelMapper.map(eachPlanet, PlanetResponse.class));
        }

        return planetResponseList;
    }
}
