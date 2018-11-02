package com.amazonaws.serverless.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;


@DynamoDBTable(tableName = "Pago")
public class Pago implements Serializable
{
    /**
     * Serial version UUID
     */
    private static final long serialVersionUID = -8243145429438016232L;

    @DynamoDBHashKey
    private String tarjetaHabiente;

    @DynamoDBRangeKey
    private String fecha;

    @DynamoDBAttribute
    private Double valor;

    @DynamoDBAttribute
    private String correo;

    @DynamoDBAttribute
    private String codigoSeguridad;

    @DynamoDBAttribute
    private String numeroTarjeta;


    public Pago() { }

    public Pago(String tarjetaHabiente, String fecha, Double valor, String correo, String codigoSeguridad, String numeroTarjeta)
    {
        this.tarjetaHabiente = tarjetaHabiente;
        this.fecha = fecha;
        this.valor = valor;
        this.correo = correo;
        this.codigoSeguridad = codigoSeguridad;
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTarjetaHabiente() {
        return tarjetaHabiente;
    }

    public void setTarjetaHabiente(String tarjetaHabiente) {
        this.tarjetaHabiente = tarjetaHabiente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
}
