package com.edjab.db.load.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edjab.db.load.pool.CustomPool;
import com.edjab.dbclient.table.userprofile.UserProfileClient;
import com.edjab.model.ChangePassword;
import com.edjab.model.EditableUserProfile;
import com.edjab.model.UpdateFrequency;
import com.edjab.model.UpdatePassword;
import com.edjab.model.UserProfile;
import com.edjab.model.UserValidator;
import com.edjab.model.VisibleUserProfile;
import com.edjab.model.client.DynamoClient;

@Controller
@RequestMapping(value = "/userprofileservice")
public class UserController {
	
	@Autowired
	CustomPool customPool;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public VisibleUserProfile getUser(@RequestParam("userId") String userId) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		VisibleUserProfile visibleUserProfile = null;
		try {
			visibleUserProfile = new UserProfileClient(bean.getAmazonDynamoDBClient()).getUserProfile(userId);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return visibleUserProfile;
    }
	
	@RequestMapping(value = "/isvaliduser", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isValidUser(@RequestParam("userId") String userId, @RequestParam("password") String password) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).IsValidUserProfile(userId, password);
			customPool.releaseTarget(bean);
			
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
	
	@RequestMapping(value = "/isvalid", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isValidUserUserId(@RequestParam("userId") String userId) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).IsValidUserProfile(userId);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
	
	@RequestMapping(value = "/matchingpasswordresettoken", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isMatchingResetPasswordToken(@RequestParam("userId") String userId, @RequestParam("tokenid") String tokenid) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).IsMatchingPasswordResetToken(userId, tokenid);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
	
	@RequestMapping(value = "/matchingregistrationtoken", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isMatchingRegistrationToken(@RequestParam("userId") String userId, @RequestParam("tokenid") String tokenid) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).IsMatchingRegistrationToken(userId, tokenid);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
    }
	
	

	@RequestMapping(value = "/useridavailability", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isUserIdAvailable(@RequestParam("userId") String userId) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).isUserIdAvailable(userId);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}

			
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean createUserProfile(@RequestBody UserProfile userProfile) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).createUserProfile(userProfile.getUserId(), 
					userProfile.getPassword(), userProfile.getRegisteredUser(), 
					userProfile.getFacebookUser(), userProfile.getGoogleUser());
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}

	
	
	
	@RequestMapping(value = "/createregistrationtoken", method = RequestMethod.GET)
	@ResponseBody
	public String createRegistrationToken(@RequestParam("userId") String userId) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		String registrationToken = null;
		try {
			registrationToken = new UserProfileClient(bean.getAmazonDynamoDBClient()).createRegistrationToken(userId);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return registrationToken;
	}
		
	@RequestMapping(value = "/createpasswordresettoken", method = RequestMethod.PUT)
	@ResponseBody
	public String createpasswordresettoken(@RequestParam("userId") String userId) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		String registrationToken = null;
		try {
			registrationToken = new UserProfileClient(bean.getAmazonDynamoDBClient()).createPasswordResetToken(userId);;
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return registrationToken;
	}	
		
	@RequestMapping(value = "/validate", method = RequestMethod.PUT)
	@ResponseBody
	public Boolean validateUserProfile(@RequestBody UserValidator userValidator) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).validateUserProfile(userValidator.getUserId(), 
					userValidator.getRegistrationToken());
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
	@ResponseBody
	public Boolean updateUserPassword(@RequestBody UpdatePassword updatedPassword) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			/*result = new UserProfileClient(bean.getAmazonDynamoDBClient()).updateUserPassword(userID,
					currentPassword, updatedPassword);*/
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).updateUserPassword(updatedPassword.getUserId()
					, updatedPassword.getCurrentPassword(), updatedPassword.getUpdatedPassword());
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.PUT)
	@ResponseBody
	public Boolean changeUserPassword(@RequestBody ChangePassword changePassword) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).changeUserPassword(changePassword.getUserId()
					, changePassword.getResetPasswordToken(), changePassword.getUpdatedPassword());
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/updateSubscriptionFrequency", method = RequestMethod.PUT)
	@ResponseBody
	public Boolean updateSubscriptionFrequency(@RequestBody UpdateFrequency frequency) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient())
					.updateSubscriptionFrequency(frequency.getUserId(), frequency.getFrequency());
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@ResponseBody
	public Boolean updateUserProfileInfo(@RequestBody EditableUserProfile editableUserProfile) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).updateUserProfileInfo(editableUserProfile);
			customPool.releaseTarget(bean);
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
		
	@RequestMapping(value = "/deactivate", method = RequestMethod.PUT)
	@ResponseBody
	public Boolean deactivateUserProfile(@RequestBody UserProfile user) throws Exception {
		System.out.println("custompool :"+customPool);
		DynamoClient bean = (DynamoClient) customPool.getTarget();
		System.out.println("pool size " + customPool.getActiveCount());
		Boolean result = false;
		try {
			result = new UserProfileClient(bean.getAmazonDynamoDBClient()).deactivateUserProfile(user.getUserId());
			}
			catch(Exception exc) {
				customPool.releaseTarget(bean);
			}
		return result;
	}
}