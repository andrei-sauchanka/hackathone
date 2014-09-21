package com.epam.travel.controller;

import com.epam.travel.model.Location;
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
@RequestMapping(value = "/city")
public class CitiController {

    @Autowired
    private PassAPackService passAPackService;

    @RequestMapping(value = "/listCities.do", method = RequestMethod.GET)
    @ResponseBody
    public String filterCities(@RequestParam String filter) {
        List<Location> cities = new ArrayList<Location>();
        String result = "";
        cities = passAPackService.getCities(filter);
        Gson gson = new Gson();
        result = gson.toJson(cities);
        return result;
    }
}
