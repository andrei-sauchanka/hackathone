package com.epam.travel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Andrei Sauchanka
 */
public class Test {

    public static void main (String[] args){

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date date = format.parse("09/24/2014");
            System.out.println(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        List<Trip> trips = TempDataGenerator.generateTripList();
//        Gson gson = new Gson();
//        String outJSON = gson.toJson(trips);
//        System.out.println(outJSON);

//        Location shipFrom = new Location();
//        shipFrom.setCity("Chicago");
//        shipFrom.setCountry("US");
//        shipFrom.setLatitude(41.8500000);
//        shipFrom.setLongitude(-87.6500000);
//
//        Location shipTo = new Location();
//        shipTo.setCity("gomel");
//        shipTo.setCountry("belarus");
//        shipTo.setLatitude(52.4453);
//        shipTo.setLongitude(30.9842);

//        System.out.println("From             To               Date   Contact");

//        try {
//            Map<Double, Trip> trips = PassAPackService.getInstance().findRelevantTrips(shipFrom, shipTo);
//            Set<Double> keySet = trips.keySet();
//            for(Double key:keySet){
//                Trip trip = trips.get(key);
//                StringBuilder out = new StringBuilder();
//                out.append(trip.getFromLocation().getCity())
//                        .append(" ")
//                        .append(trip.getToLocation().getCity())
//                        .append(" ")
//                        .append(trip.getTripDateStr())
//                        .append(" ")
//                        .append(trip.getContact().getEmail());
//                System.out.println(out.toString());
//            }
//        } catch (TravelException e) {
//            e.printStackTrace();
//        }

    }
}
