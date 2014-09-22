package com.epam.travel.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.util.List;

/**
 * @author Andrei Sauchanka
 */

@Controller
public class LoginController {

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    @ResponseBody
    public String authentication(@RequestParam String login,
                                  @RequestParam String password) throws Exception {
        boolean result = false;
        try {
            ProcessBuilder pb = new ProcessBuilder("curl","-u", login+":"+password, "https://e3s.epam.com/rest/e3s-eco-scripting-impl/0.1.0/data/searchFts?type=com.epam.e3s.app.people.api.data.EmployeeEntity&query=\\{\\\"statements\\\":\\[\\{\\\"query\\\":\\\"mongodb\\\"\\}\\],\\\"limit\\\":10\\}\"");

            pb.redirectErrorStream(true);
            Process p = pb.start();

            InputStream is = p.getInputStream();
            List<String> data = IOUtils.readLines(is);
            System.out.println(data);
            is.close();
            for (String s : data) {
                if (s.contains("manager")) {
                    result = true;
                    break;
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return ""+result;
    }

}
