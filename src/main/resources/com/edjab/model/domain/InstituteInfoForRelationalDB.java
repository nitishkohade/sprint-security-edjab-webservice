package com.edjab.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

/**
 * Model class for InstituteInfo
 */
@Builder
@Entity
@Table(name="instituteinfo")
public class InstituteInfoForRelationalDB implements java.io.Serializable, Comparable<InstituteInfoForRelationalDB>{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="instituteInfoId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long instituteInfoId;

	@Column(name="instituteId", length=200, nullable=false)
	private String instituteId;
	
	@Column(name="latitude", nullable=false)
	private Double latitude;
	
	@Column(name="longitude", nullable=false)
	private Double longitude;
	
	@Column(name="city", length=50, nullable=false)
	private String city;
	
	@Column(name="state", length=50, nullable=false)
	private String state;
	
	@Column(name="zip", nullable=false)
	private Integer zip;
	
	@Column(name="averageRating", nullable=false)
	private Double averageRating;
	
	@Column(name="numberOfReviews", nullable=false)
	private Integer numberOfReviews;
	
	@Column(name="initialAverageRating", nullable=false)
	private Double initialAverageRating;
		
	public Long getInstituteInfoId() {
		return instituteInfoId;
	}

	public void setInstituteInfoId(Long instituteInfoId) {
		this.instituteInfoId = instituteInfoId;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
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

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getNumberOfReviews() {
		return numberOfReviews;
	}

	public void setNumberOfReviews(Integer numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}

	public Double getInitialAverageRating() {
		return initialAverageRating;
	}

	public void setInitialAverageRating(Double initialAverageRating) {
		this.initialAverageRating = initialAverageRating;
	}

	public int compareTo(InstituteInfoForRelationalDB instituteInfoForRelationalDB) {
		double avgrate = instituteInfoForRelationalDB.getAverageRating();
		//descending order
		if((int) (avgrate*10 - this.getAverageRating()*10) ==  0) {
			return 1;
		}
		return (int) (avgrate*10 - this.getAverageRating()*10);
	}
}
