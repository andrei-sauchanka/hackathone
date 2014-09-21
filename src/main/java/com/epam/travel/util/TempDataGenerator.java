package com.epam.travel.util;

import au.com.bytecode.opencsv.CSVReader;
import com.epam.travel.exceptions.TravelException;
import com.epam.travel.model.Contact;
import com.epam.travel.model.Location;
import com.epam.travel.model.Trip;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Andrei Sauchanka
 */
public class TempDataGenerator {



    public static void main (String[] args){
        try {
            List<Location> airportData = readAirportData();
            List<Trip> tripList = new ArrayList<Trip>();
            for(int i=0;i<20;i++){
                Trip trip = new Trip();
                Location startLocation = airportData.get(Integer.parseInt(""+Math.round(Math.random()*(airportData.size()/10))));
                Location destination = airportData.get(Integer.parseInt(""+Math.round(Math.random()*(airportData.size()))));
                long date = System.currentTimeMillis()+ Math.round(Math.random()*1000000000);
                trip.setFromLocation(startLocation);
                trip.setToLocation(destination);
                trip.setTripDate(date);
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                Date tripDate  = new Date(trip.getTripDate());
                trip.setTripDateStr(format.format(tripDate));
                trip.setContact(new Contact("Vasya_Pupkin@epam.com"));
                tripList.add(trip);
                StringBuilder out = new StringBuilder("Start location:");
                out.append(trip.getFromLocation().getCity())
                        .append(" (")
                        .append(trip.getFromLocation().getCode())
                        .append(")")
                        .append(" Destination:")
                        .append(trip.getToLocation().getCity())
                        .append(" (")
                        .append(trip.getToLocation().getCode())
                        .append(")")
                        .append(" Trip date:")
                        .append(format.format(tripDate))
                        .append(" Contact:").append(trip.getContact().getEmail());
                System.out.println(out.toString());
            }
            Gson gson = new Gson();
            String dummyDataJson = gson.toJson(tripList);
            System.out.println(dummyDataJson);

        } catch (TravelException e) {
            e.printStackTrace();
        }
    }

    public static String readTripDataJSON() throws IOException {
        try {
            InputStreamReader reader = new InputStreamReader(
                    TempDataGenerator.class.getClassLoader()
                            .getResourceAsStream("/data/trip_data.json"));

            BufferedReader in = new BufferedReader(reader);
            StringBuilder content = new StringBuilder();
            String str;
            while ((str = in.readLine()) != null) {
                content.append(str);
            }
            in.close();
            return content.toString();

        } catch (IOException e) {
            throw e;
        }

    }


    public static List<Trip> generateTripList(){
        List<Trip> tripList = new ArrayList<Trip>();
        Location from = new Location();
        from.setCity("Chicago");
        from.setCountry("US");
        from.setLatitude(41.85);
        from.setLongitude(-87.65);

        Location to = new Location();
        to.setCity("Gomel");
        to.setCountry("belarus");
        to.setLatitude(52.4416667);
        to.setLongitude(30.9833333);

        Trip trip = new Trip();
        long date = System.currentTimeMillis() + Math.round(Math.random() * 1000000000);
        trip.setFromLocation(from);
        trip.setToLocation(to);
        trip.setTripDate(date);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date tripDate = new Date(trip.getTripDate());
        trip.setTripDateStr(format.format(tripDate));
        trip.setContact(new Contact("Vasya_Pupkin@epam.com"));
        trip.getContact().setFirstName("Vasya");
        trip.getContact().setLastName("Pupkin");
        tripList.add(trip);

        from = new Location();
        from.setCity("New York");
        from.setCountry("US");
        from.setLatitude(40.7141667);
        from.setLongitude(-74.0063889);

        to = new Location();
        to.setCity("Minsk");
        to.setCountry("belarus");
        to.setLatitude(53.9);
        to.setLongitude(27.5666667);

        trip = new Trip();
        date = System.currentTimeMillis() + Math.round(Math.random() * 1000000000);
        trip.setFromLocation(from);
        trip.setToLocation(to);
        trip.setTripDate(date);
        tripDate = new Date(trip.getTripDate());
        trip.setTripDateStr(format.format(tripDate));
        trip.setContact(new Contact("Vasya_Pupkin@epam.com"));
        trip.getContact().setFirstName("John");
        trip.getContact().setLastName("Smith");
        tripList.add(trip);

        from = new Location();
        from.setCity("Chicago");
        from.setCountry("US");
        from.setLatitude(41.85);
        from.setLongitude(-87.65);

        to = new Location();
        to.setCity("Gomel");
        to.setCountry("by");
        to.setLatitude(52.4416667);
        to.setLongitude(30.9833333);

        trip = new Trip();
        date = System.currentTimeMillis() + Math.round(Math.random() * 1000000000);
        trip.setFromLocation(from);
        trip.setToLocation(to);
        trip.setTripDate(date);
        format = new SimpleDateFormat("MM/dd/yyyy");
        tripDate = new Date(trip.getTripDate());
        trip.setTripDateStr(format.format(tripDate));
        trip.setContact(new Contact("Vasya_Pupkin@epam.com"));
        trip.getContact().setFirstName("Peter");
        trip.getContact().setLastName("Parker");
        tripList.add(trip);

        from = new Location();
        from.setCity("New York");
        from.setCountry("US");
        from.setLatitude(40.7141667);
        from.setLongitude(-74.0063889);

        to = new Location();
        to.setCity("Minsk");
        to.setCountry("belarus");
        to.setLatitude(53.9);
        to.setLongitude(27.5666667);

        trip = new Trip();
        date = System.currentTimeMillis() + Math.round(Math.random() * 1000000000);
        trip.setFromLocation(from);
        trip.setToLocation(to);
        trip.setTripDate(date);
        tripDate = new Date(trip.getTripDate());
        trip.setTripDateStr(format.format(tripDate));
        trip.setContact(new Contact("Vasya_Pupkin@epam.com"));
        trip.getContact().setFirstName("Bruce");
        trip.getContact().setLastName("Waine");
        tripList.add(trip);

        from = new Location();
        from.setCity("New York");
        from.setCountry("US");
        from.setLatitude(40.7141667);
        from.setLongitude(-74.0063889);

        to = new Location();
        to.setCity("Moscow");
        to.setCountry("ru");
        to.setLatitude(55.752222);
        to.setLongitude(37.615556);

        trip = new Trip();
        date = System.currentTimeMillis() + Math.round(Math.random() * 1000000000);
        trip.setFromLocation(from);
        trip.setToLocation(to);
        trip.setTripDate(date);
        tripDate = new Date(trip.getTripDate());
        trip.setTripDateStr(format.format(tripDate));
        trip.setContact(new Contact("Vasya_Pupkin@epam.com"));
        trip.getContact().setFirstName("Natalia");
        trip.getContact().setLastName("Romanova");
        tripList.add(trip);

        from = new Location();
        from.setCity("New York");
        from.setCountry("US");
        from.setLatitude(40.7141667);
        from.setLongitude(-74.0063889);

        to = new Location();
        to.setCity("Gomel");
        to.setCountry("belarus");
        to.setLatitude(52.4416667);
        to.setLongitude(30.9833333);

        trip = new Trip();
        date = System.currentTimeMillis() + Math.round(Math.random() * 1000000000);
        trip.setFromLocation(from);
        trip.setToLocation(to);
        trip.setTripDate(date);
        tripDate = new Date(trip.getTripDate());
        trip.setTripDateStr(format.format(tripDate));
        trip.setContact(new Contact("Vasya_Pupkin@epam.com"));
        trip.getContact().setFirstName("Natalia");
        trip.getContact().setLastName("Romanova");
        tripList.add(trip);

        return tripList;

    }

    private static List<Location> readAirportData() throws TravelException {
        List<Location> locationData = new ArrayList<Location>();
        try {
                // duplicate full set of settings of CSV file format
            InputStreamReader in = new InputStreamReader(TempDataGenerator.class.getClassLoader().getResourceAsStream("/data/airport_input_data.csv"));
                CSVReader reader = new CSVReader(in,
                                ',', '\'', 1); // it is not clear what arguments means
                try {

                    String[] values = reader.readNext();
                    while ( values != null ) {
                        locationData.add(processLocationData(values));
                        values = reader.readNext();
                    }
                } finally {
                        reader.close();
                }
        } catch (IOException e) {
                // we have to process exceptions when it is not required
                e.printStackTrace();
        }
        locationData = updateCoordinates(locationData);
        return locationData;
    }


    /**
     * Implementation specific to csv airport data from http://www.airportcodes.org/#international
     * @param inputData
     * @return
     */
    private static Location processLocationData(String[] inputData){
        Location location = new Location();
        if(inputData.length == 2){
            location.setCity(inputData[0]);
            String data = inputData[1];
            String code = data.substring(data.indexOf('(')+1, data.lastIndexOf(')'));
            location.setCode(code);
            String country = data.substring(0, data.indexOf('(')).trim();
            location.setCountry(country);
        }
        return location;
    }

    public static List<Location> readCitiesData() throws TravelException {

        List<Location> cities = new ArrayList<Location>();
        try {
                // duplicate full set of settings of CSV file format
                InputStreamReader in = new InputStreamReader(TempDataGenerator.class.getClassLoader().getResourceAsStream("/data/cities.csv"));
                CSVReader reader = new CSVReader(in,
                                ',', '\'', 0);
                try {

                    String[] values = reader.readNext();
                    while ( values != null ) {
                        if(values.length>3){
                            String country = values[0];
                            String city = values[1];
                            Double latitude  = Double.parseDouble(values[2]);
                            Double longitude = Double.parseDouble(values[3]);
                            Location location = new Location();
                            location.setCountry(country);
                            location.setCity(city);
                            location.setLatitude(latitude);
                            location.setLongitude(longitude);
                            cities.add(location);
                        }
                        values = reader.readNext();
                    }
                } finally {
                        reader.close();
                }

        } catch (IOException e) {
                // we have to process exceptions when it is not required
                e.printStackTrace();
        }
        return cities;
    }

    private static List<Location> updateCoordinates(List<Location> locationsList) throws TravelException {
        Map<String, Location> locationsMap = new HashMap<String, Location>();
        for(Location location:locationsList){
            if(location.getCity()!=null){
                locationsMap.put(location.getCity().toLowerCase(), location);
            }
        }
        List<Location> cities = readCitiesData();
        for(Location city:cities){
            String cityName = city.getCity();
            if(locationsMap.containsKey(cityName)){
                locationsMap.get(cityName).setLatitude(city.getLatitude());
                locationsMap.get(cityName).setLongitude(city.getLongitude());
            }
        }
        return new ArrayList<Location>(locationsMap.values());
    }

}
