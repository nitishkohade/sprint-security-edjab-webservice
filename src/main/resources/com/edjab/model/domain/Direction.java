package com.edjab.model.domain;

public enum Direction {
	
	NORTH("India"),
	SOUTH("South"),
	EAST("East"),
	WEST("West");
	
	private String directionName;
		
	private Direction(String name) {
		this.directionName = name;
	}

	public String getDirectionName() {
		return directionName;
	}
}
