package com.wheeludrive2.entity.manager.factory;

import static org.eclipse.persistence.config.PersistenceUnitProperties.*;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.wheeludrive2.domain.PropertiesManager;
import com.wheeludrive2.exception.PropertyException;



/**
* Class to get a connection to the database
*
* @author Renaud DIANA & Simon-Pierre NKIZAMACUMU (but Diana mainly)
*/
public final class EMF {
	
//	 private static String JDBC_URL = "javax.persistence.jdbc.url";
//	 private static String JDBC_USER = "javax.persistence.jdbc.user";
//	 private static String JDBC_PASSWORD = "javax.persistence.jdbc.password";

    private static EntityManagerFactory emfInstance;

    private EMF() {
    }

    private static EntityManagerFactory getInstanceFactory(String database) throws PropertyException {

        if (emfInstance == null) {

            Map<String, String> map = new HashMap<>();
            PropertiesManager prop = new PropertiesManager();
            
            String url = "jdbc:mysql://"+prop.getServer()+":"+prop.getPort()+"/" + database;

			map.put(JDBC_URL , url);
			map.put(JDBC_USER , prop.getUser());
			map.put(JDBC_PASSWORD , prop.getPassword());

            emfInstance = Persistence.createEntityManagerFactory(database,map);

            return emfInstance;
        }

        return emfInstance;
    }

    public static EntityManagerFactory getEMF(String database) throws PropertyException {
        return getInstanceFactory(database);
    }

    public static EntityManager getEM(String database) throws PropertyException {

        return getInstanceFactory(database).createEntityManager();
    }

    /*
     * Create EntityManager in others classes EntityManager em =
     * EMF.get().createEntityManager(); try { // ... do stuff with em ... } finally
     * { em.close(); }
     */
}