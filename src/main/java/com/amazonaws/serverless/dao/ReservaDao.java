package com.amazonaws.serverless.dao;

import com.amazonaws.serverless.domain.Reserva;

import java.util.Optional;

public interface ReservaDao
{

    String saveOrUpdateReserva(Reserva reserva);

    void deleteReserva(String uuid);

    Optional<Reserva> findReservaByKey(String uuid);
}