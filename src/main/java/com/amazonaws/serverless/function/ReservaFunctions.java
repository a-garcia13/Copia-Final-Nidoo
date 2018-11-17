package com.amazonaws.serverless.function;

import com.amazonaws.serverless.dao.DynamoDBReservaDao;
import com.amazonaws.serverless.domain.Reserva;
import org.apache.log4j.Logger;


public class ReservaFunctions {

    private static final Logger log = Logger.getLogger(ReservaFunctions.class);
    private static final DynamoDBReservaDao reservaDao = DynamoDBReservaDao.instance();

    public void saveOrUpdateReserva(Reserva reserva)
    {

        if (null == reserva)
        {
            log.error("Salvar o actualizar reserva ha recibido un parámetro de entrada nulo");
            throw new IllegalArgumentException("No se puede salvar un objeto nulo");
        }

        log.info("Salvando o actualizando una reserva con coordenada x= " + reserva.getCoordenadaX() + " y coordenada Y  = " + reserva. getCoordenadaY());
        reservaDao.saveOrUpdateReserva(reserva);
        log.info("Parqueadero salvado/actualizado satisfactoriamente");
    }
}
