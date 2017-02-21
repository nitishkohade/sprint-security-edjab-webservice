package com.edjab.db.load.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edjab.db.load.repository.SchoolRepositoryInterface;
import com.edjab.model.domain.InstituteInfoForRelationalDB;

@Service
@Transactional
public class SchoolServiceImplementation implements SchoolServiceInterface{

	@Autowired
	protected SchoolRepositoryInterface schoolRepositoryInterface;
	
	@Override
	public InstituteInfoForRelationalDB saveInstituteInRelationalDB(InstituteInfoForRelationalDB instituteInfoForRelationalDB) {
		return schoolRepositoryInterface.saveInstituteInRelationalDB(instituteInfoForRelationalDB);
	}
}
