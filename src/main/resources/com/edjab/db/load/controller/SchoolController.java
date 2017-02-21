package com.edjab.db.load.controller;

import java.io.IOException;
import java.util.Collections;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edjab.db.load.pool.CustomPool;
import com.edjab.db.load.service.SchoolServiceInterface;
import com.edjab.dbclient.table.schoolprofile.SchoolProfileClient;
import com.edjab.model.VisibleInstituteProfile;
import com.edjab.model.client.DynamoClient;
import com.edjab.model.domain.Country;
import com.edjab.model.domain.InstituteInfoForRelationalDB;
import com.edjab.model.domain.InstituteProfile;
import com.edjab.model.domain.SchoolCategory;

@Controller
public class SchoolController {
	
	@Autowired
	SchoolServiceInterface schoolServiceInterface;
	
	@Autowired
	CustomPool customPool; 
	
	@RequestMapping(value = "/savepage", method = RequestMethod.GET)
	public String savePage(Model model) {
		//model.addAttribute("instituteProfile", new InstituteInfoForRelationalDB());
		model.addAttribute("instituteProfile", new InstituteProfile());
		return "savepage";
	}
	
	@RequestMapping(value = "/school", method = RequestMethod.GET)
	@ResponseBody
	public VisibleInstituteProfile getSchool(@RequestParam("schoolId") String schoolId) throws Exception {
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
	
	
	
	@RequestMapping(value = "/instituteProfile/save", method = RequestMethod.POST)
	public String saveSchool(@ModelAttribute("instituteProfile") InstituteProfile instituteProfile,
			final RedirectAttributes redirectAttributes) throws Exception {
		
		//Storing in Relational DB
		InstituteInfoForRelationalDB instituteInfoForRelationalDB = new InstituteInfoForRelationalDB();
		instituteInfoForRelationalDB.setAverageRating(0.0);
		instituteInfoForRelationalDB.setCity(instituteProfile.getCity().toUpperCase());
		instituteInfoForRelationalDB.setInitialAverageRating(instituteProfile.getInitialAverageRating());
		//IndianState state = instituteProfile.getState();
		String instituteName = instituteProfile.getInstituteName();
		String instituteId = instituteName
				.trim()
				.toUpperCase()
				.replace(" ", "_")
				.concat("_")
				.concat(instituteProfile.getCity().trim().toUpperCase())
				.concat("_")
				.concat(instituteProfile.getState().trim().toUpperCase());
		instituteInfoForRelationalDB.setInstituteId(instituteId);
		instituteInfoForRelationalDB.setLatitude(instituteProfile.getLatitude());
		instituteInfoForRelationalDB.setLongitude(instituteProfile.getLongitude());
		instituteInfoForRelationalDB.setNumberOfReviews(0);
		instituteInfoForRelationalDB.setState(instituteProfile.getState());
		instituteInfoForRelationalDB.setZip(instituteProfile.getZip());
		
		if(schoolServiceInterface.saveInstituteInRelationalDB(instituteInfoForRelationalDB) !=null) {
			redirectAttributes.addFlashAttribute("saveSchool", "success");
		} else {
			redirectAttributes.addFlashAttribute("saveSchool", "unsuccess");
		}
		
		//Storing in NoSql DB
		
		DynamoClient bean = (DynamoClient) customPool.getTarget();
			System.out.println("pool size " + customPool.getActiveCount());
			/*
			com.edjab.model.InstituteProfile instituteProfile2 = com.edjab.model.InstituteProfile.newBuilder()
					.setAttendees(0)
					.setAverageRating(0.0f)
					.setCity(instituteProfile.getCity())
					.setContactNumber(instituteProfile.getContactNumber())
					.setCountry(Country.INDIA.getCountryName())
					.setDateOfEstablishment(instituteProfile.getDateOfEstablishment())
					.setDescription(instituteProfile.getDescription())
					.setDirection(instituteProfile.getDirection())
					.setEmailId(instituteProfile.getEmailId())
					.setFiveStarRatings(0)
					.setFollowers(0)
					.setFourStarRatings(0)
					.setInitialAverageRating(instituteProfile.getInitialAverageRating().floatValue())
					.setInstituteId(instituteId)
					.setLatitude(instituteProfile.getLatitude().floatValue())
					.setLongitude(instituteProfile.getLongitude().floatValue())
					.setMission(instituteProfile.getMission())
					.setName(instituteProfile.getInstituteName())
					.setOneStarRatings(0)
					.setProfileImageUrl(instituteProfile.getProfileImageUrl())
					.setRegion(instituteProfile.getRegion())
					.setState(instituteProfile.getState())
					.setStreet(instituteProfile.getStreet())
					.setThreeStarRatings(0)
					.setTwoStarRatings(0)
					.setZip(instituteProfile.getZip().toString())
					.setCategories(Collections.singletonList(categoryTypes.ENGINEERING))
					.setUrls(Collections.<CharSequence>singletonList("url"))
					.setLikes(0)
					.setReviews(0)
					.build();
					*/
			try {
			//new SchoolProfileClient(bean.getAmazonDynamoDBClient()).putItems(instituteProfile2);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		
		return "redirect:/savepage";
	}
}