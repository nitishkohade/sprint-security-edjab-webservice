package com.edjab.db.load.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edjab.db.load.pool.CustomPool;
import com.edjab.dbclient.table.userprofile.UserToVideoClient;
import com.edjab.model.client.DynamoClient;

@Controller
@RequestMapping(value = "/usertovideoservice")
public class UserToVideoController {
	

	@Autowired
	CustomPool customPool;
	
	@RequestMapping(value = "/isaccessed", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isVideoAccessedByUser(@RequestParam("userid") String userid, 
			@RequestParam("videoid") String videoid) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new UserToVideoClient(bean.getAmazonDynamoDBClient()).isVideoAccessedByUser(userid, videoid);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean putVideoByUser(@RequestParam("userid") String userid, 
			@RequestParam("videoid") String videoid, @RequestParam("videourl") String videourl) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new UserToVideoClient(bean.getAmazonDynamoDBClient()).putVideoByUser(userid, videoid, videourl);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean deleteUserToVideoItem(@RequestParam("userid") String userid, 
			@RequestParam("videoId") String videoId) 
					throws Exception {
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new UserToVideoClient(bean.getAmazonDynamoDBClient()).deleteUserToVideoItem(userid, videoId);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
}