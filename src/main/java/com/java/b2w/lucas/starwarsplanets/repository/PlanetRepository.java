package com.java.b2w.lucas.starwarsplanets.repository;

import com.java.b2w.lucas.starwarsplanets.model.entity.PlanetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<PlanetEntity, Long>
{
}
