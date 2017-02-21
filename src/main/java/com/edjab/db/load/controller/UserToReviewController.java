package com.edjab.db.load.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edjab.db.load.pool.CustomPool;
import com.edjab.dbclient.table.userprofile.UserToReviewClient;
import com.edjab.model.client.DynamoClient;

@Controller
@RequestMapping(value = "/usertoreviewservice")
public class UserToReviewController {
	
	@Autowired
	CustomPool customPool;
	
	@RequestMapping(value = "/isaccessed", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isReviewAccessedByUser(@RequestParam("userid") String userid, 
			@RequestParam("reviewid") String reviewid) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new UserToReviewClient(bean.getAmazonDynamoDBClient()).isReviewAccessedByUser(userid, reviewid);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
	//, @RequestParam("reviewurl") String reviewurl
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean putReviewByUser(@RequestParam("userid") String userid, 
			@RequestParam("reviewid") String reviewid) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new UserToReviewClient(bean.getAmazonDynamoDBClient()).putReviewByUser(userid, reviewid);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean deleteUserToReviewItem(@RequestParam("userid") String userid, 
			@RequestParam("reviewId") String reviewId) 
					throws Exception {
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new UserToReviewClient(bean.getAmazonDynamoDBClient()).deleteUerToReviewItem(userid, reviewId);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
}