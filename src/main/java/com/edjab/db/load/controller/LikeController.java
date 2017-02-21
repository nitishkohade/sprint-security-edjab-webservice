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
import com.edjab.dbclient.table.like.LikeClient;
import com.edjab.model.InstituteName;
import com.edjab.model.client.DynamoClient;

@Controller
@RequestMapping(value = "/likeservice")
public class LikeController {
	
	
	
	@Autowired
	CustomPool customPool;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<InstituteName> getLikedByUser(@RequestParam("likedby") String likedby, 
			HttpServletResponse response) throws Exception {
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
	    
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		List<InstituteName> instituteNames = null;
		try {
		    instituteNames = new LikeClient(bean.getAmazonDynamoDBClient()).getAllSchoolLikedByUser(likedby);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return instituteNames;
	}
	
	@RequestMapping(value = "/istrue", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isLikedByUser(@RequestParam("likedby") String likedby,
			@RequestParam("likedTo") String likedTo, HttpServletResponse response) throws Exception {
		
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
		    result = new LikeClient(bean.getAmazonDynamoDBClient()).isLikedByUser(likedby, likedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean createLikeByUser(@RequestParam("likedby") String likedby,
			@RequestParam("likedTo") String likedTo) throws Exception {
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
		    result = new LikeClient(bean.getAmazonDynamoDBClient()).createLikedByUser(likedby, likedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean removeLikeedByUser(@RequestParam("likedby") String likedby,
			@RequestParam("likedTo") String likedTo) throws Exception {
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
		    result = new LikeClient(bean.getAmazonDynamoDBClient()).deleteLikedByUser(likedby, likedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
}