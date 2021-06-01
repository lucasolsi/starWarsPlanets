package com.java.b2w.lucas.starwarsplanets.service;

import com.java.b2w.lucas.starwarsplanets.exceptions.PlanetNotFoundException;
import com.java.b2w.lucas.starwarsplanets.model.entity.PlanetEntity;
import com.java.b2w.lucas.starwarsplanets.repository.PlanetRepository;
import com.java.b2w.lucas.starwarsplanets.service.impl.PlanetServiceImpl;
import com.java.b2w.lucas.starwarsplanets.shared.dto.PlanetDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class PlanetServiceImplTest
{
    @InjectMocks
    PlanetServiceImpl planetService;

    @Mock
    PlanetRepository planetRepo;

    PlanetEntity planetEntity;

    String planetName = "Sahara";
    String planetId = "1uh271h1120ahoihhq";
    String climate = "arid";
    String terrain = "desert";

    @BeforeEach
    void setUp() throws Exception
    {
        MockitoAnnotations.openMocks(this);
        planetEntity = new PlanetEntity()
                .withId(planetId)
                .withClimate(climate)
                .withTerrain(terrain)
                .withName(planetName)
                .withNumberOfFilms(1);
    }

    @Test
    void getPlanetByNameTest()
    {
        when(planetRepo.findByName(anyString())).thenReturn(planetEntity);
        PlanetDto planetDto = planetService.findPlanetByName(planetName);
        Assertions.assertNotNull(planetDto);
        Assertions.assertEquals(planetName, planetDto.getName());
    }

    @Test
    final void getPlanetByNameNotFoundException()
    {
        when(planetRepo.findByName(anyString())).thenReturn(null);
        Assertions.assertThrows(PlanetNotFoundException.class, () -> {
            planetService.findPlanetByName(planetName);
        });
    }

    @Test
    void createPlanetTest() throws Exception
    {
        when(planetRepo.findByName(anyString())).thenReturn(null);
        when(planetRepo.save(any(PlanetEntity.class))).thenReturn(planetEntity);

        PlanetDto planetDto = new PlanetDto();
        planetDto.setName(planetName);
        planetDto.setClimate(climate);
        planetDto.setTerrain(terrain);
        planetDto.setNumberOfFilms(1);

        PlanetDto storedPlanet = planetService.createPlanet(planetDto);

        Assertions.assertNotNull(storedPlanet);
        Assertions.assertEquals(planetEntity.getName(), storedPlanet.getName());
        Assertions.assertNotNull(storedPlanet.getId());

        verify(planetRepo, times(1)).save(any(PlanetEntity.class));
    }
}
