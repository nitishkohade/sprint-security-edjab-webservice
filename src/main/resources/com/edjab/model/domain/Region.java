package com.edjab.model.domain;

public enum Region {
	
	NORTH("North"),
	SOUTH("South");
	
	private String regionName;
		
	private Region(String name) {
		this.regionName = name;
	}

	public String getRegionName() {
		return regionName;
	}
}
