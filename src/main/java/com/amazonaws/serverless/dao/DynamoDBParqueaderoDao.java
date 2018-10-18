package com.amazonaws.serverless.dao;

import com.amazonaws.serverless.domain.Parqueadero;
import com.amazonaws.serverless.manager.DynamoDBManager;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import org.apache.log4j.Logger;

import java.util.ArrayList;
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
    public List<Parqueadero> findNearestPoints(Double coordenadaX, Double coordenadaY)
    {
        DynamoDBScanExpression dynamoDBScanExpression  = new DynamoDBScanExpression()
                .withLimit(1000);

        ScanResultPage<Parqueadero> paginatedList = mapper.scanPage(Parqueadero.class, dynamoDBScanExpression);

        List<Parqueadero> newList = new ArrayList<>();

        for (Parqueadero currentParqueadero : paginatedList.getResults())
        {
            double currentDistance = distance(coordenadaX, coordenadaY, currentParqueadero.getCoordenadaX(), currentParqueadero.getCoordenadaY());
            if (currentDistance < 3)
                newList.add(currentParqueadero);
        }

        return newList;
    }


    @Override
    public Optional<Parqueadero> findParqueaderoByKey(Double coordenadaX, Double coordenadaY)
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
    public void deleteParqueadero(Double coordenadaX, Double coordenadaY)
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

    /**
     * https://www.geodatasource.com/developers/java
     * @param lat1 first point latitude
     * @param lon1 first point longitude
     * @param lat2 second point latitude
     * @param lon2 second point longitude
     * @return distance in kilometers between two points
     */
    private Double distance(Double lat1, Double lon1, Double lat2, Double lon2)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return dist;
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::	This function converts decimal degrees to radians           :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::	This function converts radians to decimal degrees           :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad)
    {
        return (rad * 180 / Math.PI);
    }
}
