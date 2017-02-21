package com.edjab.db.load.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.edjab.model.domain.InstituteInfoForRelationalDB;

@Repository
public class SchoolRepositoryImplementation implements SchoolRepositoryInterface{

	protected EntityManager entityManager;
	
	public SchoolRepositoryImplementation() {
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@PersistenceContext(unitName="InstituteContext")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public InstituteInfoForRelationalDB saveInstituteInRelationalDB(InstituteInfoForRelationalDB instituteInfoForRelationalDB) {
		// TODO Auto-generated method stub
		entityManager.persist(instituteInfoForRelationalDB);
	    entityManager.flush();
	    return instituteInfoForRelationalDB;
	}
}