package com.amazonaws.serverless.dao;

import com.amazonaws.serverless.domain.Parqueadero;
import com.amazonaws.serverless.manager.DynamoDBManager;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;


public class DynamoDBParqueaderoDao implements ParqueaderoDao
{

    private static final Logger log = Logger.getLogger(DynamoDBParqueaderoDao.class);

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    private static volatile DynamoDBParqueaderoDao instance;

    /**
     * Singleton constructor
     */
    private DynamoDBParqueaderoDao() { }

    /**
     * Public instance builder
     * @return Current DAO instance
     */
    public static DynamoDBParqueaderoDao instance()
    {

        if (instance == null)
        {
            synchronized(DynamoDBParqueaderoDao.class)
            {
                if (instance == null)
                    instance = new DynamoDBParqueaderoDao();
            }
        }
        return instance;
    }

    @Override
    public List<Parqueadero> findNearestPoints(Float coordenadaX, Float coordenadaY)
    {
        return mapper.scan(Parqueadero.class, new DynamoDBScanExpression());
    }


    @Override
    public Optional<Parqueadero> findParqueaderoByKey(Float coordenadaX, Float coordenadaY)
    {

        Parqueadero parqueadero = mapper.load(Parqueadero.class, coordenadaX, coordenadaY);

        return Optional.ofNullable(parqueadero);
    }

    @Override
    public void saveOrUpdateParqueadero(Parqueadero parqueadero)
    {
        mapper.save(parqueadero);
    }

    @Override
    public void deleteParqueadero(Float coordenadaX, Float coordenadaY)
    {
        Optional<Parqueadero> oParqueadero = findParqueaderoByKey(coordenadaX, coordenadaY);
        if (oParqueadero.isPresent())
        {
            mapper.delete(oParqueadero.get());
        }
        else
        {
            log.error("No se puede borrar el parqueadero especificado. No se encontró el parqueadero con las coordenadas especificadas");
            throw new IllegalArgumentException("Proceso de borrado no exitoso. No existe el parqueadero especificado.");
        }

    }
}
