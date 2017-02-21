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
import com.edjab.dbclient.table.video.VideoClient;
import com.edjab.model.Video;
import com.edjab.model.VideoParameter;
import com.edjab.model.client.DynamoClient;

@Controller
@RequestMapping(value = "/videoservice")
public class VideoController {
	
	@Autowired
	CustomPool customPool;
	
	@RequestMapping(value = "/schoolid", method = RequestMethod.GET)
	@ResponseBody
	public List<Video> getVideosBySchool(@RequestParam("uploadedTo") String uploadedTo, 
			HttpServletResponse response) throws Exception {
		
	/*	response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		*/
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		List<Video> videos = null;
		try {
			videos = new VideoClient(bean.getAmazonDynamoDBClient()).getVideosBySchool(uploadedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return videos;
    }
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Boolean uploadVideoByUser(@RequestBody final VideoParameter videoParameter, 
			HttpServletResponse response) throws Exception {
		
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new VideoClient(bean.getAmazonDynamoDBClient()).uploadVideoByUser(videoParameter.getUploadedBy(), 
					videoParameter.getUploadedTo(), videoParameter.getVideoUrl(), videoParameter.getDescriptiveName());
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean removeVideo(@RequestParam("schoolnameid") String schoolnameid, 
			@RequestParam("videoId") String videoId, @RequestParam("uploadedBy") String uploadedBy, 
			HttpServletResponse response) 
					throws Exception {
		
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new VideoClient(bean.getAmazonDynamoDBClient()).deleteVideo(schoolnameid, videoId, uploadedBy);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }	
}