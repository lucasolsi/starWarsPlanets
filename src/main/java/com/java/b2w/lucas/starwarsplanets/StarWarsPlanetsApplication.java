package com.java.b2w.lucas.starwarsplanets;

import com.java.b2w.lucas.starwarsplanets.model.entity.PlanetEntity;
import com.java.b2w.lucas.starwarsplanets.repository.PlanetRepository;
import com.java.b2w.lucas.starwarsplanets.utils.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarWarsPlanetsApplication implements CommandLineRunner
{

    @Autowired
    PlanetRepository planetRepository;

    public static void main(String[] args)
    {
        SpringApplication.run(StarWarsPlanetsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        planetRepository.deleteAll();
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
    }
}
