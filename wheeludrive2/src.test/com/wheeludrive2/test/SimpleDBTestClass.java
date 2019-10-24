package com.wheeludrive2.test;

import org.junit.jupiter.api.Test;

import com.wheeludrive2.entity.Utilisateur;
import com.wheeludrive2.entity.manager.TestManager;
import com.wheeludrive2.exception.PropertyException;

public class SimpleDBTestClass {
	
	
	@Test
	public void testGetUser() throws PropertyException, ClassNotFoundException {
		
//		Class.forName("org.eclipse.persistence.jpa.PersistenceProvider");
		
		Utilisateur userTest = new TestManager().getUtilisateur(1);
		System.out.println("Utilisateur is "+ userTest.getNom());
	}

}
