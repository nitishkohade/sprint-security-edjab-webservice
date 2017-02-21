package com.edjab.db.load.service;

import com.edjab.model.domain.InstituteInfoForRelationalDB;

public interface SchoolServiceInterface {
	
	public InstituteInfoForRelationalDB saveInstituteInRelationalDB(InstituteInfoForRelationalDB instituteInfoForRelationalDB);
	//public void saveSchoolInNosqlDB(SchoolInfo schoolInfo);
}
