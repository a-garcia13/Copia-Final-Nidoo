package com.amazonaws.serverless.function;

import com.amazonaws.serverless.dao.DynamoDBParqueaderoDao;
import com.amazonaws.serverless.domain.Parqueadero;
import com.amazonaws.serverless.pojo.Coordenada;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;


public class ParqueaderoFunctions
{

    private static final Logger log = Logger.getLogger(ParqueaderoFunctions.class);

    private static final DynamoDBParqueaderoDao parqueaderoDao = DynamoDBParqueaderoDao.instance();

    public void saveOrUpdateParqueadero(Parqueadero parqueadero)
    {

        if (null == parqueadero)
        {
            log.error("Salvar o actualizar parqueadero ha recibido un parámetro de entrada nulo");
            throw new IllegalArgumentException("No se puede salvar un objeto nulo");
        }

        log.info("Salvando o actualizando un parqueadero con coordenada x= " + parqueadero.getCoordenadaX() + " y coordenada Y  = " + parqueadero. getCoordenadaY());
        parqueaderoDao.saveOrUpdateParqueadero(parqueadero);
        log.info("Parqueadero salvado/actualizado satisfactoriamente");
    }

    public void deleteParqueadero(Parqueadero parqueadero)
    {

        if (null == parqueadero)
        {
            log.error("Eliminar parqueadero ha recibido un parámetro de entrada nulo");
            throw new IllegalArgumentException("No se puede eliminar un objeto nulo");
        }

        log.info("Eliminando un parqueadero con coordenada x= " + parqueadero.getCoordenadaX() + " y coordenada Y  = " + parqueadero. getCoordenadaY());
        parqueaderoDao.deleteParqueadero(parqueadero.getCoordenadaX(), parqueadero.getCoordenadaY());
        log.info("Parqueadero eliminado satisfactoriamente");
    }

    /**
     * Get the nearest parking (< 2 Kms)
     * @param coordenada user coordinates
     * @return list of parking
     */
    public List<Parqueadero> getNearestPoints(Coordenada coordenada)
    {

        if (null == coordenada || coordenada.getCoordenadaX() == null || coordenada.getCoordenadaY() == null)
        {
            log.error("GetNearestPoints ha recibido coordenadas nulas");
            throw new IllegalArgumentException("Las coordenadas no pueden ser nulas o vacías");
        }

        log.info("Obtener los parqueaderos más cercanos a las coordenadas X = " + coordenada.getCoordenadaX() + " y coordenada Y: " + coordenada.getCoordenadaY());
        List<Parqueadero> parqueaderos = parqueaderoDao.findNearestPoints(coordenada.getCoordenadaX(), coordenada.getCoordenadaY());

        log.info("Encontrados " + parqueaderos.size() + " parqueaderos para coordenadas x = " + coordenada.getCoordenadaX() + " y coordenada Y: " + coordenada.getCoordenadaY());

        return parqueaderos;
    }

    public Parqueadero getParqueaderoByKey(Coordenada coordenada)
    {

        if (null == coordenada || coordenada.getCoordenadaX() == null || coordenada.getCoordenadaY() == null)
        {
            log.error("GetParqueaderoByKey ha recibido coordenadas nulas");
            throw new IllegalArgumentException("Las coordenadas no pueden ser nulas o vacías");
        }

        log.info("Obtener el parqueaderos asociado a las coordenadas X = " + coordenada.getCoordenadaX() + " y coordenada Y: " + coordenada.getCoordenadaY());
        Optional<Parqueadero> oParqueadero = parqueaderoDao.findParqueaderoByKey(coordenada.getCoordenadaX(), coordenada.getCoordenadaY());

        if (oParqueadero.isPresent())
        {
            log.info("Nombre del parqueadero recuperado = " + oParqueadero.get().getNombreLugar());
            return oParqueadero.get();
        }
        else
        {
            log.error("No se puede recuperar el parqueadero especificado. No se encontró el parqueadero con las coordenadas especificadas");
            throw new IllegalArgumentException("Proceso de búsqueda no exitoso. No existe el parqueadero especificado.");
        }
    }


}
