package com.amazonaws.serverless.pojo;

import java.util.Date;

public class Pago {

    /**
     *
     * This simple POJO is for use with Amazon API Gateway so the
     * Lambda functions can receive JSON objects from API Gateway,
     * rather than simple Strings.
     */

    private String tarjetaHabiente;
    private int codigoSeguridad;
    private Long numeroTarjeta;
    private Float valor;
    private Date fecha;
    private String correo;

    public String getTarjetaHabiente() {
        return tarjetaHabiente;
    }

    public void setTarjetaHabiente(String tarjetaHabiente) {
        this.tarjetaHabiente = tarjetaHabiente;
    }

    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

}
