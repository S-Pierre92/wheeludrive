package com.wheeludrive2.entity.manager;

import com.wheeludrive2.entity.Utilisateur;
import com.wheeludrive2.exception.PropertyException;

public class TestManager extends AbstractManager {

	public String getName(String id) {

		return null;
	}

	@Override
	protected String getDb() {
		return "test";
	}

	public Utilisateur getUtilisateur(int id) throws PropertyException {
		
		prepareEntityManager();
		Utilisateur utilisateur = entitymanager.find(Utilisateur.class, id);
		closeResources();
		return utilisateur;
	}

}
