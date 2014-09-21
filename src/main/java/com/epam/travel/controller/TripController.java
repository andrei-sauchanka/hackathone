package com.epam.travel.controller;

import com.epam.travel.exceptions.TravelException;
import com.epam.travel.model.Contact;
import com.epam.travel.model.Location;
import com.epam.travel.model.Trip;
import com.epam.travel.services.PassAPackService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei Sauchanka
 */
@Controller
@RequestMapping(value = "/trip")
public class TripController {


    @Autowired
    private PassAPackService passAPackService;

    @RequestMapping(value = "/findTrip.do", method = RequestMethod.GET)
    @ResponseBody
    public String getRelevantTrips(@RequestParam String from,
                                   @RequestParam String to){
        String result = "";
        Gson gson = new Gson();
        try {
            Location fromLocation = passAPackService.getLocationsMapByCoordinates().get(from);
            Location toLocation = passAPackService.getLocationsMapByCoordinates().get(to);
            List<Trip> relevantTrips = new ArrayList<Trip>();
            relevantTrips.addAll(passAPackService.findRelevantTrips(fromLocation, toLocation).values());
            result = gson.toJson(relevantTrips);
        } catch (TravelException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/addTrip.do", method = RequestMethod.POST)
    @ResponseBody
    public void filterInbox(@RequestParam String from, String to, String contactEmail, String date) {
        try {
            Location fromLocation = passAPackService.getLocationsMapByCoordinates().get(from);
            Location toLocation = passAPackService.getLocationsMapByCoordinates().get(to);
            Contact contact = new Contact(contactEmail);
            Trip trip = new Trip();
            trip.setFromLocation(fromLocation);
            trip.setToLocation(toLocation);
            trip.setContact(contact);
            trip.setTripDate(System.currentTimeMillis());
            passAPackService.listTrips().add(trip);
        } catch (TravelException e) {
            e.printStackTrace();
        }
    }

}
