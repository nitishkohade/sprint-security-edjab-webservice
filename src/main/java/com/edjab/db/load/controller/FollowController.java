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
import com.edjab.dbclient.table.follow.FollowClient;
import com.edjab.model.InstituteName;
import com.edjab.model.client.DynamoClient;

@Controller
@RequestMapping(value = "/followservice")
public class FollowController {
	
	
	@Autowired
	CustomPool customPool;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<InstituteName> getFollowedByUser(@RequestParam("followedby") String followedby,
			HttpServletResponse response) throws Exception {
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		List<InstituteName> instituteNames = null;
		try {
		    instituteNames = new FollowClient(bean.getAmazonDynamoDBClient()).getAllSchoolFollowedByUser(followedby);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return instituteNames;
	}
	
	@RequestMapping(value = "/istrue", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isFollowedByUser(@RequestParam("followedby") String followedby,
			@RequestParam("followedTo") String followedTo, HttpServletResponse response) throws Exception {
		
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		*/
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
		    result = new FollowClient(bean.getAmazonDynamoDBClient()).isFollowedByUser(followedby, followedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean createFollowByUser(@RequestParam("followedby") String followedby,
			@RequestParam("followedTo") String followedTo) throws Exception {
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
		    result = new FollowClient(bean.getAmazonDynamoDBClient()).createFollowedByUser(followedby, followedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean removeFollowedByUser(@RequestParam("followedby") String followedby,
			@RequestParam("followedTo") String followedTo) throws Exception {
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
		    result = new FollowClient(bean.getAmazonDynamoDBClient()).deleteFollowedByUser(followedby, followedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
}