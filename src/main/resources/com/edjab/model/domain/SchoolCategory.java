package com.edjab.model.domain;

public enum SchoolCategory {
	
    ENGINEERING("Engineering"), 
    MEDICAL("Medical"), 
    LAW("Law"), 
    ARCHITECTURE("Architecture"), 
    IIT_COACHING("IIT Coaching");
	
	private String categoryName;
	
	private SchoolCategory(String catName) {
		this.categoryName = catName;
	}

	public String getCategoryName() {
		return categoryName;
	}
}
