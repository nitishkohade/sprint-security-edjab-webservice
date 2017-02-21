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
import com.edjab.dbclient.table.attend.AttendClient;
import com.edjab.model.InstituteName;
import com.edjab.model.client.DynamoClient;

@Controller
@RequestMapping(value = "/attendservice")
public class AttendController {
	
	@Autowired
	CustomPool customPool;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<InstituteName> getAttendedByUser(@RequestParam("attendedby") String attendedby, 
			HttpServletResponse response) throws Exception {
		
	/*	response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		List<InstituteName> instituteNames = null;
		try {
		    instituteNames = new AttendClient(bean.getAmazonDynamoDBClient()).getAllSchoolAttendedByUser(attendedby);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return instituteNames;
	}
	
	@RequestMapping(value = "/istrue", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isAttendedByUser(@RequestParam("attendedby") String attendedby,
			@RequestParam("attendedTo") String attendedTo, HttpServletResponse response) throws Exception {
		
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		*/
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
		    result = new AttendClient(bean.getAmazonDynamoDBClient()).isAttendedByUser(attendedby, attendedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean createAttendByUser(@RequestParam("attendedby") String attendedby,
			@RequestParam("attendedTo") String attendedTo) throws Exception {
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
		    result = new AttendClient(bean.getAmazonDynamoDBClient()).createAttendedByUser(attendedby, attendedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean removeAttendedByUser(@RequestParam("attendedby") String attendedby,
			@RequestParam("attendedTo") String attendedTo) throws Exception {
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
		    result = new AttendClient(bean.getAmazonDynamoDBClient()).deleteAttendedByUser(attendedby, attendedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
}