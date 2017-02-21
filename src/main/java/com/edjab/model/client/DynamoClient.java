package com.edjab.model.client;

import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class DynamoClient {
	
private AmazonDynamoDBClient amazonDynamoDBClient = null;
	
	public DynamoClient() {
	AWSCredentials credentials;
	try {
	       credentials= new PropertiesCredentials(
	    		   DynamoClient.class
					.getResourceAsStream("/AwsCredentials.properties"));
	       if(credentials.getAWSAccessKeyId().isEmpty()) {
                System.err.println("No credentials supplied in AwsCredentials.properties");
            }
	}
	catch (IOException e) {
        System.err.println("Could not load credentials from file.");
        throw new RuntimeException(e);
    }
	
	amazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
   }
	
	public AmazonDynamoDBClient getAmazonDynamoDBClient() {
		return this.amazonDynamoDBClient;
	}
}