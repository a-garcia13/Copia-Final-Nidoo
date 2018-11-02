package com.amazonaws.serverless.dao;

import com.amazonaws.serverless.domain.Pago;
import com.amazonaws.serverless.manager.DynamoDBManager;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.log4j.Logger;

import java.util.Optional;


public class DynamoDBPagoDao implements PagoDao
{

    private static final Logger log = Logger.getLogger(DynamoDBPagoDao.class);

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    private static volatile DynamoDBPagoDao instance;

    /**
     * Singleton constructor
     */
    private DynamoDBPagoDao() { }

    /**
     * Public instance builder
     * @return Current DAO instance
     */
    public static DynamoDBPagoDao instance()
    {

        if (instance == null)
        {
            synchronized(DynamoDBPagoDao.class)
            {
                if (instance == null)
                    instance = new DynamoDBPagoDao();
            }
        }
        return instance;
    }



    @Override
    public void saveOrUpdatePago(Pago pago)
    {
        mapper.save(pago);
    }

    @Override
    public Optional<Pago> findPagoByKey(String tarjetaHabiente, String fecha)
    {

        Pago pago = mapper.load(Pago.class, tarjetaHabiente, fecha);

        return Optional.ofNullable(pago);
    }

    @Override
    public void deletePago(String tarjetaHabiente, String fecha)
    {
        Optional<Pago> oPago = findPagoByKey(tarjetaHabiente, fecha);
        if (oPago.isPresent())
        {
            mapper.delete(oPago.get());
        }
        else
        {
            log.error("No se puede borrar el pago especificado. No se encontró el pago con la llave dada");
            throw new IllegalArgumentException("Proceso de borrado no exitoso. No existe el pago  especificado.");
        }

    }


}
