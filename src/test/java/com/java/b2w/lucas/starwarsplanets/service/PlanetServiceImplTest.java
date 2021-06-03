package com.java.b2w.lucas.starwarsplanets.service;

import com.java.b2w.lucas.starwarsplanets.exceptions.PlanetAlreadyExistsException;
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

import java.util.Optional;

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

    PlanetDto planetDto;

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

        planetDto = new PlanetDto();
        planetDto.setName(planetName);
        planetDto.setClimate(climate);
        planetDto.setTerrain(terrain);
        planetDto.setNumberOfFilms(1);
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

        PlanetDto storedPlanet = planetService.createPlanet(planetDto);

        Assertions.assertNotNull(storedPlanet);
        Assertions.assertEquals(planetEntity.getName(), storedPlanet.getName());
        Assertions.assertNotNull(storedPlanet.getId());

        verify(planetRepo, times(1)).save(any(PlanetEntity.class));
    }

    @Test
    void createPlanet_alreadyExistsTest()
    {
        when(planetRepo.findByName(anyString())).thenReturn(planetEntity);

        Assertions.assertThrows(PlanetAlreadyExistsException.class,
                () -> planetService.createPlanet(planetDto));
    }

    @Test
    void deletePlanetByIdTest()
    {
        when(planetRepo.findById(planetId)).thenReturn(java.util.Optional.ofNullable(planetEntity));
        planetService.deletePlanetById(planetId);
        verify(planetRepo).delete(planetEntity);
    }

    @Test
    void deletePlanetByName_notFoundTest()
    {
        when(planetRepo.findByName(anyString())).thenReturn(null);
        Assertions.assertThrows(PlanetNotFoundException.class, () ->
                planetService.deletePlanetByName(planetName));
    }

    @Test
    void findPlanetByIdTest()
    {
        when(planetRepo.findById(anyString())).thenReturn(java.util.Optional.ofNullable(planetEntity));
        PlanetDto planetDto = planetService.findPlanetById(planetId);
        Assertions.assertEquals(planetEntity.getName(), planetDto.getName());
    }

    @Test
    void deletePlanetById()
    {
        when(planetRepo.findById(anyString())).thenReturn(Optional.of(planetEntity));
        planetService.deletePlanetById(planetId);
        verify(planetRepo).delete(planetEntity);
    }

    @Test
    void deletePlanetByName()
    {
        when(planetRepo.findByName(anyString())).thenReturn(planetEntity);
        planetService.deletePlanetByName(anyString());
        verify(planetRepo).delete(any());
    }

    @Test
    void deletePlanetById_notFoundExceptionTest()
    {
        when(planetRepo.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(PlanetNotFoundException.class, () -> planetService.deletePlanetById(anyString()));
    }
}
