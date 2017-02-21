package com.edjab.db.load.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edjab.model.LocationName;
import com.edjab.school.HibernateApis;

@Controller
@RequestMapping(value = "/locationservice")
public class LocationController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<LocationName> getLocations(@RequestParam("locationid") String locationid, 
			HttpServletResponse response) throws IOException {
		
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");*/
		
		List<LocationName> locationNames = null;
		try {
			locationNames =  new HibernateApis().getLocations(locationid);
		}
		catch(Exception exception) {
		}
		return locationNames;
    }
}