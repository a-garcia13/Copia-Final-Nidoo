package com.amazonaws.serverless.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.io.Serializable;


@DynamoDBTable(tableName = "Reserva")
public class Reserva implements Serializable
{
    /**
     * Serial version UUID
     */
    private static final long serialVersionUID = -8243145429438016232L;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String uuid;

    @DynamoDBRangeKey
    private String user;

    @DynamoDBAttribute
    private Integer selectedHour;

    @DynamoDBAttribute
    private Integer selectedMinutes;

    @DynamoDBAttribute
    private Double coordenadaX;

    @DynamoDBAttribute
    private Double coordenadaY;


    public Reserva() { }

    public Reserva(String user, Integer selectedHour, Integer selectedMinutes, Double coordenadaX, Double coordenadaY)
    {
        this.user = user;
        this.selectedHour = selectedHour;
        this.selectedMinutes = selectedMinutes;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getSelectedHour() {
        return selectedHour;
    }

    public void setSelectedHour(Integer selectedHour) {
        this.selectedHour = selectedHour;
    }

    public Integer getSelectedMinutes() {
        return selectedMinutes;
    }

    public void setSelectedMinutes(Integer selectedMinutes) {
        this.selectedMinutes = selectedMinutes;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
}
