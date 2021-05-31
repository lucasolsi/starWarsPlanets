package com.java.b2w.lucas.starwarsplanets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlanetNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 3871845281961720713L;

    public PlanetNotFoundException(String msg)
    {
        super(msg);
    }
}
