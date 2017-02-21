package com.edjab.db.load.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edjab.db.load.pool.CustomPool;
import com.edjab.dbclient.table.image.ImageClient;
import com.edjab.model.Image;
import com.edjab.model.ImageParameter;
import com.edjab.model.client.DynamoClient;

@Controller
@RequestMapping(value = "/imageservice")
public class ImageController {
	
	@Autowired
	CustomPool customPool;
	
	@RequestMapping(value = "/schoolid", method = RequestMethod.GET)
	@ResponseBody
	public List<Image> getImagesBySchool(@RequestParam("uploadedTo") String uploadedTo,
			HttpServletResponse response) throws Exception {
		
	/*	response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		List<Image> images = null;
		try {
			images = new ImageClient(bean.getAmazonDynamoDBClient()).getImagesBySchool(uploadedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return images;
    }
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Boolean uploadImageByUser(@RequestBody final ImageParameter imageParameter, 
			HttpServletResponse response) throws Exception {
		
	/*	response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new ImageClient(bean.getAmazonDynamoDBClient()).uploadImageByUser(imageParameter.getUploadedBy(), 
					imageParameter.getUploadedTo(), imageParameter.getImageUrl(), imageParameter.getDescriptiveName());
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean removeImage(@RequestParam("schoolnameid") String schoolnameid, 
			@RequestParam("imageId") String imageId, @RequestParam("uploadedBy") String uploadedBy, 
			HttpServletResponse response) 
					throws Exception {
		
	/*	response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new ImageClient(bean.getAmazonDynamoDBClient()).deleteImage(schoolnameid, imageId, uploadedBy);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }	
}