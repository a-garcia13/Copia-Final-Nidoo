package com.amazonaws.serverless.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.io.Serializable;


@DynamoDBTable(tableName = "Parqueadero")
public class Parqueadero implements Serializable
{

    /**
     * Serial version UUID
     */
    private static final long serialVersionUID = -8243145429438016232L;

    @DynamoDBHashKey
    private Float coordenadaX;

    @DynamoDBRangeKey
    private Float coordenadaY;

    @DynamoDBAttribute
    private Integer cupos;

    @DynamoDBAttribute
    private String descripcion;

    @DynamoDBAttribute
    private String direccion;

    @DynamoDBAttribute
    private String nombreLugar;

    @DynamoDBAttribute
    private Integer tarifa;

    @DynamoDBAttribute
    private Integer tipoParqueadero;

    @DynamoDBAttribute
    private Integer tipoTarifa;

    public Parqueadero() { }

    public Parqueadero(Float coordenadaX, Float coordenadaY)
    {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public Parqueadero(Float coordenadaX, Float coordenadaY, Integer cupos, String descripcion, String direccion, String nombreLugar, Integer tarifa, Integer tipoParqueadero, Integer tipoTarifa)
    {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.cupos = cupos;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.nombreLugar = nombreLugar;
        this.tarifa = tarifa;
        this.tipoParqueadero = tipoParqueadero;
        this.tipoTarifa = tipoTarifa;
    }

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

    public Integer getCupos()
    {
        return cupos;
    }

    public void setCupos(Integer cupos)
    {
        this.cupos = cupos;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getNombreLugar()
    {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar)
    {
        this.nombreLugar = nombreLugar;
    }

    public Integer getTarifa()
    {
        return tarifa;
    }

    public void setTarifa(Integer tarifa)
    {
        this.tarifa = tarifa;
    }

    public Integer getTipoParqueadero()
    {
        return tipoParqueadero;
    }

    public void setTipoParqueadero(Integer tipoParqueadero)
    {
        this.tipoParqueadero = tipoParqueadero;
    }

    public Integer getTipoTarifa()
    {
        return tipoTarifa;
    }

    public void setTipoTarifa(Integer tipoTarifa)
    {
        this.tipoTarifa = tipoTarifa;
    }
}
