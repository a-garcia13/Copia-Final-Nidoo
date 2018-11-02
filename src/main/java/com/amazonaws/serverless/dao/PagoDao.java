package com.amazonaws.serverless.dao;

import com.amazonaws.serverless.domain.Pago;

import java.util.Optional;

public interface PagoDao
{

    void saveOrUpdatePago(Pago pago);

    void deletePago(String tarjetaHabiente, String fecha);

    Optional<Pago> findPagoByKey(String tarjetaHabiente, String fecha);
}