package com.java.b2w.lucas.starwarsplanets.exceptions;

public class PlanetAlreadyExistsException extends RuntimeException
{
    private static final long serialVersionUID = 7757975715138765118L;

    public PlanetAlreadyExistsException(String message)
    {
        super(message);
    }
}
