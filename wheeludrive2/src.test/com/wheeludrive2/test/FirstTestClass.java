package com.wheeludrive2.test;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.wheeludrive2.domain.PropertiesManager;
import com.wheeludrive2.exception.PropertyException;
import com.wheeludrive2.exception.WheelUDriveException;
import com.wheeludrive2.tools.EncryptionUtils;



public class FirstTestClass {
	
	@Test
	public void testDriver() {
		
	try {
		  Class.forName("com.mysql.jdbc.Driver");
		  System.out.println("It works");
		} catch(ClassNotFoundException e) {
		  Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testLocationProperty() {
		System.out.println(System.getProperty("user.home"));
	}
	
	
	
	@Test
	public void testProperty() {
		PropertiesManager prop = null;
		try {
			 prop = new PropertiesManager();
		} catch (PropertyException e) {
			Assert.fail(e.getMessage());
		}
		
		System.out.println("server: "+prop.getServer());
		System.out.println("port: "+prop.getPort());
		System.out.println("user: "+prop.getUser());
		System.out.println("password: "+prop.getPassword());
		System.out.println("ssl:" + prop.getSSl());
	}
	
	@Test
	public void testCreateEncryptString(){
		
		PropertyConfigurator.configure("resources/META-INF/log4j.properties");
		String myPassword = "password";
		String secret = "secret";
		
		String encryptPassword;
		try {
			encryptPassword = EncryptionUtils.encrypt(myPassword, secret);
			System.out.println("encryptPassword: " + encryptPassword);
			Assertions.assertTrue(EncryptionUtils.decrypt(encryptPassword,secret).equals(myPassword));
			
			try {
				EncryptionUtils.decrypt(encryptPassword,"scrett").equals(myPassword);
				Assertions.assertTrue(false);
				
			}catch (WheelUDriveException e) {
				Assertions.assertTrue(true);
			}
			
		} catch (WheelUDriveException e) {
			Assertions.fail(e);
		}
	}
}
