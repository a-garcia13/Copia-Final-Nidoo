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

    private Double coordenadaX;

    private Double coordenadaY;

    public Double getCoordenadaX()
    {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX)
    {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY()
    {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY)
    {
        this.coordenadaY = coordenadaY;
    }
}
