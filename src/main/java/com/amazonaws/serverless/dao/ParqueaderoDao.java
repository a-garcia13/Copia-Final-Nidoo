package com.amazonaws.serverless.dao;


import com.amazonaws.serverless.domain.Parqueadero;

import java.util.List;
import java.util.Optional;

public interface ParqueaderoDao
{

    List<Parqueadero> findNearestPoints(Double coordenadaX, Double coordenadaY);

    Optional<Parqueadero> findParqueaderoByKey(Double coordenadaX, Double coordenadaY);

    void saveOrUpdateParqueadero(Parqueadero parqueadero);

    void deleteParqueadero(Double coordenadaX, Double coordenadaY);
}