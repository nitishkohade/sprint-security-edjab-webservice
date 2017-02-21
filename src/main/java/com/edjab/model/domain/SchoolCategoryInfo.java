package com.edjab.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model class for SchoolCategoryInfo
 */
@Entity
@Table( name = "schoolcategoryinfo" )
public class SchoolCategoryInfo implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="schoolCategoryId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long schoolCategoryId;
	
	@Column(name="schoolId", length=100, nullable=false)
	private String schoolId;
	
	@Column(name="categoryName", length=50, nullable=false)
	private String categoryName;

	public SchoolCategoryInfo() {
	}

	public SchoolCategoryInfo(String school, String catName) {
		this.schoolId = school;
		this.categoryName = catName;
	}
	
	public Long getSchoolCategoryId() {
		return this.schoolCategoryId;
	}

	public void setSchoolCategoryId(Long schoolCategory) {
		this.schoolCategoryId = schoolCategory;
	}

	public String getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(String school) {
		this.schoolId = school;
	}
	
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String catName) {
		this.categoryName = catName;
	}
}