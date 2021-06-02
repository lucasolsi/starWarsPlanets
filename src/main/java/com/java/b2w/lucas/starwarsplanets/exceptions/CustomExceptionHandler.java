package com.java.b2w.lucas.starwarsplanets.exceptions;

import com.java.b2w.lucas.starwarsplanets.model.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler
{
    @ExceptionHandler(value = {PlanetNotFoundException.class})
    public ResponseEntity<Object> handlePlanetNotFoundException(PlanetNotFoundException ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {PlanetAlreadyExistsException.class})
    public ResponseEntity<Object> handlePlanetAlreadyExistsException(PlanetAlreadyExistsException existsException, WebRequest request)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), existsException.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
    }
}
