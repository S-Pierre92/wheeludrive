package com.wheeludrive2.entity.manager;

import com.wheeludrive2.entity.User;
import com.wheeludrive2.exception.PropertyException;

public class TestManager extends AbstractManager {

	@Override
	protected String getDb() {
		return "test";
	}
	
	@Override
	protected String getPersistenceUnit() {
		return "testUnit";
	}
	

	public User getUser(int id) throws PropertyException {
		
		prepareEntityManager();
		User user = entitymanager.find(User.class, id);
		closeResources();
		return user;
	}
	
	public void setUser(String firstname, String name, String pseudo, String password) throws PropertyException {

		prepareEntityManager();
		User user = new User();
		
		user.setFirstname(firstname);
		user.setName(name);
		user.setPassword(password);
		user.setPseudo(pseudo);
		
		this.entitymanager.persist(user);
		closeResources();
	}
}
