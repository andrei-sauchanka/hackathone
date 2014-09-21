package com.epam.travel.util;

import com.epam.travel.exceptions.TravelException;
import com.epam.travel.model.Location;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Andrei Sauchanka
 */
public class CommonUtil {

    protected static Logger logger = Logger.getLogger(CommonUtil.class);

    private static Properties props = new Properties();

    private static final String PROPERTIES= "common.properties";

    /**
     * Loads application properties.
     * @throws java.io.IOException
     */
    private static void loadConfig() throws IOException {
        InputStream resourceAsStream =CommonUtil.class.getClassLoader().getResourceAsStream(PROPERTIES);
        props.load(resourceAsStream);
    }

    /**
     * Returns property value by key from loaded application properties
     * @param name property name (key)
     * @return property value
     * @throws TravelException
     */
    public static String getPropertyValue(String name) throws TravelException {
        if(props.isEmpty()){
            try {
                loadConfig();
            } catch (IOException e) {
                logger.error("Can't load application properties "+name);
                throw new TravelException("Can't load application properties "+name, e);
            }
        }
        if(props.containsKey(name)){
            return props.getProperty(name);
        }
        else{
            logger.error("Can't find property "+name);
            throw new TravelException("Can't find property "+name);
        }
    }

    public static double distFrom(Location startLocation, Location destination) {
        if((isLocationHasCoordinates(startLocation))&&(isLocationHasCoordinates(destination))){

            double lat1 = startLocation.getLatitude();
            double lng1 = startLocation.getLongitude();
            double lat2 = destination.getLatitude();
            double lng2 = destination.getLongitude();
            double earthRadius = 3958.75;
            double dLat = Math.toRadians(lat2-lat1);
            double dLng = Math.toRadians(lng2-lng1);
            double sindLat = Math.sin(dLat / 2);
            double sindLng = Math.sin(dLng / 2);
            double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                    * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            double dist = earthRadius * c;

            return dist;
        }

        //TODO refactor with norm exception handling
        else return Double.MAX_VALUE;


    }

    private static boolean isLocationHasCoordinates(Location location){
        return ((location.getLongitude()!=null)&&(location.getLatitude()!=null));
    }

}
