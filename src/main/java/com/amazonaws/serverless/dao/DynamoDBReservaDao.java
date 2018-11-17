package com.amazonaws.serverless.dao;

import com.amazonaws.serverless.domain.Reserva;
import com.amazonaws.serverless.manager.DynamoDBManager;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.log4j.Logger;

import java.util.Optional;


public class DynamoDBReservaDao implements ReservaDao
{

    private static final Logger log = Logger.getLogger(DynamoDBReservaDao.class);

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    private static volatile DynamoDBReservaDao instance;

    /**
     * Singleton constructor
     */
    private DynamoDBReservaDao() { }

    /**
     * Public instance builder
     * @return Current DAO instance
     */
    public static DynamoDBReservaDao instance()
    {

        if (instance == null)
        {
            synchronized(DynamoDBReservaDao.class)
            {
                if (instance == null)
                    instance = new DynamoDBReservaDao();
            }
        }
        return instance;
    }



    @Override
    public void saveOrUpdateReserva(Reserva reserva)
    {
        mapper.save(reserva);
    }

    @Override
    public Optional<Reserva> findReservaByKey(String uuid)
    {

        Reserva reserva = mapper.load(Reserva.class, uuid);

        return Optional.ofNullable(reserva);
    }

    @Override
    public void deleteReserva(String uuid)
    {
        Optional<Reserva> oReserva = findReservaByKey(uuid);
        if (oReserva.isPresent())
        {
            mapper.delete(oReserva.get());
        }
        else
        {
            log.error("No se puede borrar la reserva especificada. No se encontró la reserva con la llave dada: " + uuid);
            throw new IllegalArgumentException("Proceso de borrado no exitoso. No existe la reserva  especificada.");
        }

    }


}
