package com.edjab.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

/**
 * Model class for Institute Profile
 */
@Builder
@Entity
@Table(name="InstituteProfile")
public class InstituteProfile implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="instituteId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long instituteId;

	//"Unique id for institutes. Format will be Name_City_State"
	//@Column(name="instituteId", length=200, nullable=false)
	//private String instituteId;
	
	@Column(name="instituteName", length=100, nullable=false)
	private String instituteName;
	
	//"One or more relevant url for the university",
	//@Column(name="urls")
	//private List<String> urls;
	
	@Column(name="emailId", length=100)
	private String emailId;
	
	@Column(name="street", length=100)
	private String street;
	
	@Column(name="city", length=50, nullable=false)
	private String city;
	
	@Column(name="state", length=50, nullable=false)
	private String state;
	
	@Column(name="country", length=50, nullable=false)
	private String country;
	
	//"North or South"
	@Column(name="region", length=50, nullable=false)
	private String region;
	
	//"North, South, East or West"
	@Column(name="direction", length=50, nullable=false)
	private String direction;
	
	@Column(name="zip", nullable=false)
	private Integer zip;

	@Column(name="latitude", nullable=false)
	private Double latitude;
	
	@Column(name="longitude", nullable=false)
	private Double longitude;
	
	@Column(name="mission")
	private String mission;
	
	@Column(name="contactNumber")
	private String contactNumber;

	@Column(name="description")
	private String description;
	
	@Column(name="profileImageUrl", nullable=false)
	private String profileImageUrl;
	
	//@Column(name="categories", nullable=false)
	//private List<SchoolCategory> categories;
	
	//@Column(name="attendees", nullable=false)
	//private Integer attendees;
	
	//@Column(name="likes", nullable=false)
	//private Integer likes;
	
	//@Column(name="followers", nullable=false)
	//private Integer followers;
	
	//@Column(name="reviews", nullable=false)
	//private Integer reviews;
	
	@Column(name="dateOfEstablishment")
	private String dateOfEstablishment;
	
	//@Column(name="oneStarRatings", nullable=false)
	//private Integer oneStarRatings;
	
	//@Column(name="twoStarRatings", nullable=false)
	//private Integer twoStarRatings;
	
	//@Column(name="threeStarRatings", nullable=false)
	//private Integer threeStarRatings;
	
	//@Column(name="fourStarRatings", nullable=false)
	//private Integer fourStarRatings;
	
	//@Column(name="fiveStarRatings", nullable=false)
	//private Integer fiveStarRatings;
	
	//@Column(name="averageRating", nullable=false)
	//private Double averageRating;
	
	@Column(name="initialAverageRating", nullable=false)
	private Double initialAverageRating;

	public Long getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(Long instituteId) {
		this.instituteId = instituteId;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getDateOfEstablishment() {
		return dateOfEstablishment;
	}

	public void setDateOfEstablishment(String dateOfEstablishment) {
		this.dateOfEstablishment = dateOfEstablishment;
	}

	public Double getInitialAverageRating() {
		return initialAverageRating;
	}

	public void setInitialAverageRating(Double initialAverageRating) {
		this.initialAverageRating = initialAverageRating;
	}
	
	/*
	public int compareTo(InstituteProfile instituteProfile) {
		double avgrate = instituteProfile.averageRating;
		//descending order
		if((int) (avgrate*10 - this.averageRating*10) ==  0) {
			return 1;
		}
		return (int) (avgrate*10 - this.averageRating*10);
	}
	*/
}
