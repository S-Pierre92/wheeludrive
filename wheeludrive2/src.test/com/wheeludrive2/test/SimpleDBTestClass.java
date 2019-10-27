package com.wheeludrive2.test;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import com.wheeludrive2.entity.User;
import com.wheeludrive2.entity.manager.TestManager;
import com.wheeludrive2.exception.PropertyException;

public class SimpleDBTestClass {
	
	
	@Test
	public void testGetUser() throws PropertyException, ClassNotFoundException {
		
		int index = 1 ;
		TestManager manager = new TestManager();
		
		User user = manager.getUser(index);
		if(Objects.isNull(user)) {
			manager.setUser("John", "Snow","John", "stark");
			user = manager.getUser(index);	
		}
		
		System.out.println("User is "+ user.getFirstname() + " " + user.getName());
	}

}
