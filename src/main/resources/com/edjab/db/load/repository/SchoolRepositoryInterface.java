package com.edjab.db.load.repository;

import com.edjab.model.domain.InstituteInfoForRelationalDB;

public interface SchoolRepositoryInterface {
	public InstituteInfoForRelationalDB saveInstituteInRelationalDB(InstituteInfoForRelationalDB instituteInfoForRelationalDB);
	//public void saveSchoolInNosqlDB(SchoolInfo schoolInfo);
}
