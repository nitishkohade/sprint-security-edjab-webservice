package com.edjab.model.domain;

public enum Country {
	
	INDIA("India", "IN");
		
	private String countryName;
	private String isoCode;
		
	private Country(String name, String code) {
		this.countryName = name;
		this.isoCode = code;
	}

	public String getCountryName() {
		return countryName;
	}
	
	public String getIsoCode() {
		return isoCode;
	}
}
