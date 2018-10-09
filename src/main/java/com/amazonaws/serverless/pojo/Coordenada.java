package com.amazonaws.serverless.pojo;

/**
 *
 * This simple POJO is for use with Amazon API Gateway so the
 * Lambda functions can receive JSON objects from API Gateway,
 * rather than simple Strings.
 *
 */
public class Coordenada
{

    private Float coordenadaX;

    private Float coordenadaY;

    public Float getCoordenadaX()
    {
        return coordenadaX;
    }

    public void setCoordenadaX(Float coordenadaX)
    {
        this.coordenadaX = coordenadaX;
    }

    public Float getCoordenadaY()
    {
        return coordenadaY;
    }

    public void setCoordenadaY(Float coordenadaY)
    {
        this.coordenadaY = coordenadaY;
    }
}
