package com.edjab.model.domain;

public enum IndianState {
	 
	ANDHRA_PRADESH("Andhra Pradesh", "AP"),
	ARUNACHAL_PRADESH("Arunachal Pradesh", "AR"),
	ASSAM("Assam", "AS"),
	BIHAR("Bihar", "BR"),
	CHHATTISGARH("Chhattisgarh", "CT"),
	GOA("Goa", "GA"),
	GUJARAT("Gujarat", "GJ"),
	HARYANA("Haryana", "HR"),
	HIMACHAL_PRADESH("Himachal Pradesh", "HP"),
	JAMMU_AND_KASHMIR("Jammu And Kashmir", "JK"),
	JHARKHAND("Jharkhand", "JH"),
	KARNATAKA("Karnataka", "KA"),
	KERALA("Kerala", "KL"),
	MADHYA_PRADESH("Madhya Pradesh", "MP"),
	MAHARASHTRA("Maharashtra", "MH"),
	MANIPUR("Manipur", "MN"),
	MEGHALAYA("Meghalaya", "ML"),
	MIZORAM("Mizoram", "MZ"),
	NAGALAND("Nagaland", "NL"),
	ODISHA("Odisha", "OR"),
	PUNJAB("Punjab", "PB"),
	RAJASTHAN("Rajasthan", "RJ"),
	SIKKIM("Sikkim", "SK"),
	TAMIL_NADU("Tamil Nadu", "TN"),
	TELANGANA("Telangana", "TG"),
	TRIPURA("Tripura", "TR"),
	UTTAR_PRADESH("Uttar Pradesh", "UP"),
	UTTARAKHAND("Uttarakhand", "UT"),
	WEST_BENGAL("West Bengal", "WB");
		
	private String stateName;
	private String isoCode;
		
	private IndianState(String stateName, String isoCode) {
		this.stateName = stateName;
		this.isoCode = isoCode;
	}

	public String getStateName() {
		return stateName;
	}
	
	public String getIsoCode() {
		return isoCode;
	}
}
