package com.java.b2w.lucas.starwarsplanets.exceptions;

public class PlanetNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 3871845281961720713L;

    public PlanetNotFoundException(String msg)
    {
        super(msg);
    }
}
