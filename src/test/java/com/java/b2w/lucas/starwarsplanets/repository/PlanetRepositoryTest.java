package com.java.b2w.lucas.starwarsplanets.repository;

import com.java.b2w.lucas.starwarsplanets.model.entity.PlanetEntity;
import com.java.b2w.lucas.starwarsplanets.utils.RestClient;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlanetRepositoryTest
{
    @Autowired
    PlanetRepository planetRepository;

    static boolean recordsCreated = false;
    static String planetId;

    @BeforeTestClass
    public void configure() throws Exception
    {
        if (!recordsCreated)
        {
            createRecords();
        }
    }

    @Test
    @Order(1)
    void findPlanetByNameTest()
    {
        String planetName = "Hoth";
        PlanetEntity planetFound = planetRepository.findByName(planetName);
        planetId = planetFound.getId();
        assertNotNull(planetFound);
        assertEquals(planetFound.getName(), planetName);
    }

    @Test
    @Order(2)
    void findPlanetByIdTest()
    {
        PlanetEntity planetEntity = planetRepository.findById(planetId).orElse(null);
        assertNotNull(planetEntity);
        assertEquals(planetEntity.getId(), planetId);
    }

    @Test
    @Order(3)
    void deletePlanetTest()
    {
        PlanetEntity planetEntity1 = planetRepository.findByName("Tatooine");
        planetRepository.delete(planetEntity1);
        assertNull(planetRepository.findByName("Tatooine"));

        PlanetEntity planetEntity2 = planetRepository.findByName("Hoth");
        planetRepository.delete(planetEntity1);
        assertNull(planetRepository.findByName("Hoth"));
    }

    private void createRecords()
    {
        PlanetEntity planet1 = new PlanetEntity()
                .withName("Tatooine")
                .withClimate("arid")
                .withTerrain("desert");
        planet1.setNumberOfFilms(RestClient.findNumberOfFilms(planet1.getName()));
        planetRepository.save(planet1);

        PlanetEntity planet2 = new PlanetEntity()
                .withName("Hoth")
                .withClimate("temperate, tropical")
                .withTerrain("jungle, rainforests");
        planet2.setNumberOfFilms(RestClient.findNumberOfFilms(planet2.getName()));
        planetRepository.save(planet2);

        recordsCreated = true;
    }

}
