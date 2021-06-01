package com.java.b2w.lucas.starwarsplanets.utils;

import com.google.gson.Gson;
import com.java.b2w.lucas.starwarsplanets.model.request.PlanetFromSwapi;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RestClient
{
    private static final String SWAPI_URL_SEARCH = "https://swapi.dev/api/planets/?search=";

    static RestTemplate restTemplate = new RestTemplate();

    public static int findNumberOfFilms(String planetName)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("params", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(SWAPI_URL_SEARCH.concat(planetName), HttpMethod.GET, entity, String.class);

        Map<String, Object> map = new GsonJsonParser().parseMap(responseEntity.getBody());
        List<Object> results = (List<Object>) map.get("results");

        List<PlanetFromSwapi> planetFromSwapiList = new ArrayList<>();
        if (!results.isEmpty())
        {
            for (Object eachResult : results)
            {
                planetFromSwapiList.add(new Gson().fromJson(new Gson().toJson(eachResult), PlanetFromSwapi.class));
            }

            PlanetFromSwapi planetMatch = planetFromSwapiList.stream()
                    .filter(planetFromSwapi -> planetFromSwapi.getName().equals(planetName))
                    .findFirst().orElse(null);
            return planetMatch == null ? 0 : planetMatch.getFilms().size();
        }
        else
        {
            return 0;
        }

    }

}
