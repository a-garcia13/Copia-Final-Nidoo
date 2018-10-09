package com.amazonaws.serverless.dao;


import com.amazonaws.serverless.domain.Parqueadero;

import java.util.List;
import java.util.Optional;

public interface ParqueaderoDao
{

    List<Parqueadero> findNearestPoints(Float coordenadaX, Float coordenadaY);

    Optional<Parqueadero> findParqueaderoByKey(Float coordenadaX, Float coordenadaY);

    void saveOrUpdateParqueadero(Parqueadero parqueadero);

    void deleteParqueadero(Float coordenadaX, Float coordenadaY);
}