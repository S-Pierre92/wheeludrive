package com.wheeludrive2.entity.manager;


import javax.persistence.EntityManager;

import com.wheeludrive2.entity.manager.factory.EMF;
import com.wheeludrive2.exception.PropertyException;

public abstract class AbstractManager {
	

	protected  EntityManager entitymanager;
	
	
	protected void prepareEntityManager() throws PropertyException {

		entitymanager = EMF.getEM(this.getDb(), this .getPersistenceUnit());
		entitymanager.getTransaction().begin();
	}

	protected  void closeResources() {

		entitymanager.getTransaction().commit();
		entitymanager.close();
	}
	
	protected abstract String getDb();
	protected abstract String getPersistenceUnit();
}
