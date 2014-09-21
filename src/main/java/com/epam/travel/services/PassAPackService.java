package com.epam.travel.services;

import com.epam.travel.exceptions.TravelException;
import com.epam.travel.model.Location;
import com.epam.travel.model.Trip;
import com.epam.travel.util.CommonUtil;
import com.epam.travel.util.TempDataGenerator;
import com.google.gson.Gson;

import java.util.*;

/**
 * @author Andrei Sauchanka
 */
public class PassAPackService {


    private List<Trip> tripsList = new ArrayList<Trip>();

    private List<Location> cities = new ArrayList<Location>();

    private Map<String, Location> citiesMap = new HashMap<String, Location>();

    private Gson gson = new Gson();

    public void init(){
        getCities("");
        listTrips();
        try {
            getLocationsMapByCoordinates();
        } catch (TravelException e) {
            e.printStackTrace();
        }
    }

    public List<Trip> listTrips(){
        if (tripsList.isEmpty()){
            tripsList = readTripData();
        }
        return tripsList;
    }


    public Map<String, Location> getLocationsMapByCoordinates() throws TravelException {
        if(citiesMap.isEmpty()){
            Map<String, Location> locations = new HashMap<String, Location>();
            List<Location> cities = getCities("");
            for(Location city:cities){
//                String coordinates = ""+city.getLongitude()+","+city.getLatitude();
//                String coordinates = ""+city.getLatitude()+","+city.getLongitude()+","+city.getCity();
                String coordinates = ""+city.getLatitude()+","+city.getLongitude();
                locations.put(coordinates, city);
            }
            citiesMap = locations;
        }
        return citiesMap;
    }

    public List<Location> getCities(String filter){
        if(cities.isEmpty()){
            try {
                cities = TempDataGenerator.readCitiesData();
            } catch (TravelException e) {
                e.printStackTrace();
            }
        }
        if((filter!=null)&&(!filter.isEmpty())){
            List<Location> filteredList = new ArrayList<Location>();
            for(Location city: cities){
                String cityName = city.getCity();
                if(cityName.startsWith(filter)){
                    filteredList.add(city);
                }

            }
            return filteredList;
        }
        return cities;
    }

    public Map<Double, Trip> findRelevantTrips(Location shipFrom, Location shipTo) throws TravelException {
        Map<Double, Trip> relevancyMap = new TreeMap<Double, Trip>();
        List<Trip> availableTrips = listTrips();
        int limit = Integer.parseInt(CommonUtil.getPropertyValue("relevancy.distance.limit"));
        for(Trip trip:availableTrips){
            String shipToCoordinates = shipTo.getLatitude()+","+shipTo.getLongitude();
            String tripFromToCoordinates = trip.getFromLocation().getLatitude()
                    +","
                    +trip.getFromLocation().getLongitude();
            if(!shipToCoordinates.equals(tripFromToCoordinates)){
                Double startDelta = CommonUtil.distFrom(shipFrom, trip.getFromLocation());
                Double endDelta = CommonUtil.distFrom(shipTo, trip.getToLocation());
                if((startDelta+endDelta)<limit){
                    long timeLeft = trip.getTripDate()-System.currentTimeMillis();
                    if(timeLeft > 0){
                        int daysLeft = Math.round(timeLeft/(1000*60*60*24));
                        trip.setDaysLeft(daysLeft);
                        Double delta = startDelta+endDelta;
                        while(relevancyMap.containsKey(delta)){
                            delta++;
                        }
                        relevancyMap.put(delta, trip);
                    }
                }
            }
        }
        return relevancyMap;
    }


    private List<Trip> readTripData(){
        List<Trip> tripData = new ArrayList<Trip>();
//        try {
//            String tripJson = TempDataGenerator.readTripDataJSON();
//            tripData = gson.fromJson(tripJson, tripData.getClass());
            tripData = TempDataGenerator.generateTripList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return tripData;
    }

    public static class TripList extends ArrayList<Trip> {}


}
