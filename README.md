# Star Wars Planets

A long time ago in a galaxy far, far away...

A group of developers is developing a game with Star Wars information.

In the middle of the process, one very special developer was summoned to help.
His role was to implement an API containing information about Star Wars planets.
- [Star Wars Planets](#star-wars-planets)
  - [API Doc](#api-doc)
  - [Planet characteristics](#planet-characteristics)
  - [Technologies involved](#technologies-involved)
  - [Utilities](#utilities)
  - [Operations](#operations)
    - [Create Planet](#create-planet)
    - [List planets](#list-planets)
    - [Find planet](#find-planet)
    - [Remove planet](#remove-planet)
## API Doc
A Swagger document for this API can be found in:

    http://localhost:8080/starwars/swagger-ui/index.html

## Planet characteristics

* Name
* Climate
* Terrain

That wasn't all! One more thing was necessary: **retrieve information from an external API**. He was requested to retrieve the number of films where each created planet appeared, from [SWAPI](https://swapi.dev).

## Technologies involved

* Spring Boot
* Spring Data MongoDB
* Lombok
* Gson
* ModelMapper
* Swagger
* JUnit
* Mockito

## Utilities

In order to help you, there are included a Postman collection and environment.

## Operations

### Create Planet

Operation sent with HTTP method **POST** to http://{{api-host}}:{{port}}/starwars/planet and the request body should follow this pattern:

```json
{
    "name": "Tatooine",
    "climate": "arid",
    "terrain": "desert"
}
```

As response, the planet, including the number of films should be returned.

```json
 {
    "name": "Tatooine",
    "id": "60b9438baed1fb508f5c12b1",
    "terrain": "desert",
    "climate": "arid",
    "numberOfFilms": 5
}
```

### List planets

Operation sent with HTTP method **GET** to http://{{api-host}}:{{port}}/starwars/planet?page=0&limit=5. Since this is a paginated request, sending it exactly as shown above, it will return the first 5 planets created. Removing the page and limit parameters, by default the API returns the first page and 5 results.

Example response:

```json
[
    {
        "name": "Tatooine",
        "id": "60b9529c2d56f544ec69d93e",
        "terrain": "desert",
        "climate": "arid",
        "numberOfFilms": 5
    },
    {
        "name": "Hoth",
        "id": "60b9529c2d56f544ec69d93f",
        "terrain": "jungle, rainforests",
        "climate": "temperate, tropical",
        "numberOfFilms": 1
    }
]
```

### Find planet

A planet can be found by two different attributes: Name or Id.

- By name

    HTTP method: GET

        http://{{api-host}}:{{port}}/starwars/planet/name/{{planetName}}

- By id

    HTTP method: GET

        http://{{api-host}}:{{port}}/starwars/planet/id/{{planetId}}

### Remove planet

As well as find a planet, is possible to delete a planet by the same two attributes: Name or Id.

- By name

    HTTP method: DELETE

        http://{{api-host}}:{{port}}/starwars/planet/name/{{planetName}}

- By id

    HTTP method: DELETE

        http://{{api-host}}:{{port}}/starwars/planet/id/{{planetId}}
