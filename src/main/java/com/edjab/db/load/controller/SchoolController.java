package com.edjab.db.load.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edjab.db.load.pool.CustomPool;
import com.edjab.dbclient.table.schoolprofile.SchoolProfileClient;
import com.edjab.model.InstituteIntroProfile;
import com.edjab.model.InstituteName;
import com.edjab.model.VisibleInstituteProfile;
import com.edjab.model.client.DynamoClient;
import com.edjab.school.HibernateApis;


@Controller
@RequestMapping(value = "/schoolprofileservice")
public class SchoolController {
	
	
	@Autowired
	CustomPool customPool; 
	
	@RequestMapping(value = "/gettopratedschools", method = RequestMethod.GET)
	@ResponseBody
	public List<InstituteIntroProfile> getTopRatedSchools(HttpServletResponse response) throws Exception {
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		List<InstituteIntroProfile> instituteNames = null;
		try {
		    instituteNames = new HibernateApis().getTopRatedSchools();
			}
			catch(Exception exc) {
			}
		return instituteNames;
	}
	
	
	@RequestMapping(value = "/prefix", method = RequestMethod.GET)
	@ResponseBody
	public List<InstituteName> getSchoolByPrefix(@RequestParam("schoolNameId") String schoolNameId, 
			HttpServletResponse response) throws Exception {
				
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
	    
	  /* response.setHeader("Authorization", "Basic dmFydW4ua29oYWRlOkhFTWxhdGEyNQ==");*/
		List<InstituteName> instituteNames = null;
		try {
		    instituteNames = new HibernateApis().getSchoolsByPrefix(schoolNameId);
			}
			catch(Exception exc) {
			}
		return instituteNames;
	}
	
	@RequestMapping(value = "/nearbytoprated", method = RequestMethod.GET)
	@ResponseBody
	public List<InstituteIntroProfile> getNearByTopRatedSchools(@RequestParam("radius") String radius, 
			@RequestParam("userLatitude") String userLatitude, 
			@RequestParam("userLongitude") String userLongitude, HttpServletResponse response) throws Exception {
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		List<InstituteIntroProfile> instituteNames = null;
		try {
		    instituteNames = new HibernateApis().getNearByTopRatedSchools(Integer.parseInt(radius), 
					Double.parseDouble(userLatitude), Double.parseDouble(userLongitude));
			}
			catch(Exception exc) {
			}
		return instituteNames;
	}
	
	@RequestMapping(value = "/nearby", method = RequestMethod.GET)
	@ResponseBody
	public List<InstituteIntroProfile> getNearByTopRatedSchoolsByCategory(@RequestParam("radius") String radius, 
			@RequestParam("userLatitude") String userLatitude, 
			@RequestParam("userLongitude") String userLongitude,
			@RequestParam("category") String category, HttpServletResponse response) throws Exception {
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		List<InstituteIntroProfile> instituteNames = null;
		try {
		    instituteNames = new HibernateApis().getNearByTopRatedSchoolsByCategory(Integer.parseInt(radius), 
					Double.parseDouble(userLatitude), Double.parseDouble(userLongitude), category);
			}
			catch(Exception exc) {
			}
		return instituteNames;
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	@ResponseBody
	public List<InstituteIntroProfile> getSchoolByCategory(@RequestParam("categoryId") String categoryId, 
			HttpServletResponse response) throws Exception {
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		List<InstituteIntroProfile> instituteNames = null;
		try {
		    instituteNames = new HibernateApis().getSchoolByCategory(categoryId);
			}
			catch(Exception exc) {
			}
		return instituteNames;
	}
	
	@RequestMapping(value = "/categorylocationcomposite", method = RequestMethod.GET)
	@ResponseBody
	public List<InstituteIntroProfile> getSchoolByCategoryAndLocation(@RequestParam("location") String location, 
			@RequestParam("category") String category, HttpServletResponse response) throws Exception {
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		List<InstituteIntroProfile> instituteNames = null;
		try {
		    instituteNames = new HibernateApis()
					.getSchoolByCategoryAndLocation(category, location);
			}
			catch(Exception exc) {
			}
		return instituteNames;
	}
	
	@RequestMapping(value = "/location", method = RequestMethod.GET)
	@ResponseBody
	public List<InstituteIntroProfile> getSchoolBylocation(@RequestParam("locationId") String locationId,
			HttpServletResponse response) throws Exception {
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		List<InstituteIntroProfile> instituteNames = null;
		try {
		    instituteNames = new HibernateApis().getSchoolBylocation(locationId);
			}
			catch(Exception exc) {
			}
		return instituteNames;
	}
	
	@RequestMapping(value = "/averagerating", method = RequestMethod.GET)
	@ResponseBody
	public Float getAverageRating(@RequestParam("schoolId") String schoolId, 
			HttpServletResponse response) throws Exception {
		
		//CustomPool customPool = ObjectFilter.getCustomPool();
	/*	response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Float averageRating = 0.0f;
		try {
			averageRating = new SchoolProfileClient(bean.getAmazonDynamoDBClient()).getAverageRating(schoolId);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return averageRating;
	}
	
	@RequestMapping(value = "/school", method = RequestMethod.GET)
	@ResponseBody
	public VisibleInstituteProfile getSchool(@RequestParam("schoolId") String schoolId, 
			HttpServletResponse response) throws Exception {
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		//CustomPool customPool = ObjectFilter.getCustomPool();
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		VisibleInstituteProfile visibleInstituteProfile = null;
		try {
		visibleInstituteProfile = new SchoolProfileClient(bean.getAmazonDynamoDBClient()).getSchoolProfile(schoolId);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return visibleInstituteProfile;
	}
}