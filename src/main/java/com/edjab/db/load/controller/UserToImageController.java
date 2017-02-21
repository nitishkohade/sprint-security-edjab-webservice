package com.edjab.db.load.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edjab.db.load.pool.CustomPool;
import com.edjab.dbclient.table.userprofile.UserToImageClient;
import com.edjab.model.client.DynamoClient;

@Controller
@RequestMapping(value = "/usertoimageservice")
public class UserToImageController {
	
	@Autowired
	CustomPool customPool;
	
	@RequestMapping(value = "/isaccessed", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isImageAccessedByUser(@RequestParam("userid") String userid, 
			@RequestParam("imageid") String imageid) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new UserToImageClient(bean.getAmazonDynamoDBClient()).isImageAccessedByUser(userid, imageid);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean putImageByUser(@RequestParam("userid") String userid, 
			@RequestParam("imageid") String imageid, @RequestParam("imageurl") String imageurl) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new UserToImageClient(bean.getAmazonDynamoDBClient()).putImageByUser(userid, imageid, imageurl);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean deleteUserToImageItem(@RequestParam("userid") String userid, 
			@RequestParam("imageId") String imageId) 
					throws Exception {
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new UserToImageClient(bean.getAmazonDynamoDBClient()).deleteUserToImageItem(userid, imageId);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
}