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
        PlanetEntity planet1 = new PlanetEntity();
        planet1.setName("Tatooine");
        planet1.setClimate("clima");
        planet1.setTerrain("terreno");
        planet1.setNumberOfFilms(RestClient.findNumberOfFilms(planet1.getName()));

        PlanetEntity planet2 = new PlanetEntity();
        planet2.setName("Novo planeta");
        planet2.setClimate("clima diferente");
        planet2.setTerrain("outro terreno");
        planet2.setNumberOfFilms(RestClient.findNumberOfFilms(planet2.getName()));
        planetRepository.save(planet1);
        planetRepository.save(planet2);

        for (PlanetEntity eachPlanet : planetRepository.findAll())
        {
            System.out.println(eachPlanet);
        }
    }
}
