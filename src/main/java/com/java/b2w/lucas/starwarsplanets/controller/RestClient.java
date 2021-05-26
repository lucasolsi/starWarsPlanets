package com.java.b2w.lucas.starwarsplanets.controller;

import com.google.gson.Gson;
import com.java.b2w.lucas.starwarsplanets.model.request.PlanetFromSwapi;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class RestClient
{
    private static final String SWAPI_URL = "https://swapi.dev/api/planets/1/";
    //private static final String SWAPI_URL_SEARCH = "https://swapi.dev/api/planets/?search={name}";

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args)
    {
        callGetPlanetUrl();
    }

    private static void callGetPlanetUrl()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("params", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(SWAPI_URL, HttpMethod.GET, entity, String.class);

        PlanetFromSwapi planetEntity = new Gson().fromJson(responseEntity.getBody(), PlanetFromSwapi.class);
        int filmsCount = findNumberOfFilms(planetEntity);
    }

    public static int findNumberOfFilms(PlanetFromSwapi planetEntity)
    {
        int films;
        try
        {
            films = planetEntity.getFilms().size();
        }catch (Exception e)
        {
            films = 0;
        }
        return films;
    }

}
