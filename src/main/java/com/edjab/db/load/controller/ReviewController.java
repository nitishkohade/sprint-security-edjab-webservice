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
import com.edjab.dbclient.table.review.ReviewClient;
import com.edjab.model.Review;
import com.edjab.model.ReviewParameter;
import com.edjab.model.client.DynamoClient;
import com.edjab.school.HibernateApis;

@Controller
@RequestMapping(value = "/reviewservice")
public class ReviewController {
	
	@Autowired
	CustomPool customPool;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Review> getReviewsByUser(@RequestParam("reviewedby") String reviewedby, 
			HttpServletResponse response) throws Exception {
		
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");*/
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		List<Review> reviews = null;
		try {
			reviews = new ReviewClient(bean.getAmazonDynamoDBClient()).getReviewsByUser(reviewedby);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return reviews;
    }
	
	@RequestMapping(value = "/schoolid", method = RequestMethod.GET)
	@ResponseBody
	public List<Review> getReviewsBySchool(@RequestParam("reviewedTo") String reviewedTo, 
			HttpServletResponse response) throws Exception {
		
		/*response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		*/
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		List<Review> reviews = null;
		try {
			reviews = new ReviewClient(bean.getAmazonDynamoDBClient()).getReviewsBySchool(reviewedTo);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return reviews;
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean createReviewByUser(@RequestBody final ReviewParameter reviewParameter) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = null;
		try {
			result = new ReviewClient(bean.getAmazonDynamoDBClient()).createReviewByUser(reviewParameter.getReviewedBy(), 
					reviewParameter.getReviewedTo(), reviewParameter.getReviewBody(), reviewParameter.getRatedNumber());
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@ResponseBody
	public Boolean editReview(@RequestBody final ReviewParameter reviewParameter) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			int relationalDBResultBoolean = new HibernateApis().updateAverageRating(reviewParameter.getReviewedTo(), 
					reviewParameter.getRatedNumber());
			if(relationalDBResultBoolean != 1) {
				return false;
			}
			result = new ReviewClient(bean.getAmazonDynamoDBClient()).editReview(reviewParameter.getReviewedTo(), 
					reviewParameter.getReviewedBy(), reviewParameter.getReviewBody(), reviewParameter.getRatedNumber());
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean removeReviewByUser(@RequestBody Review review) 
					throws Exception {
		
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new ReviewClient(bean.getAmazonDynamoDBClient()).deleteReview(review.getReviewedTo(), review.getReviewedBy());
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
}