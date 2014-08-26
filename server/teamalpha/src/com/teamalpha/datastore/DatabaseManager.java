package com.teamalpha.datastore;

import java.util.*;
import com.google.appengine.api.datastore.*;
 
public class DatabaseManager {
       
	public static Patient createPatient() {
		return DatabaseManager.createPatient("");
	}
	
       public static Patient createPatient(String name) {
        	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        	Entity entity = new Entity("Employee");
        	entity.setProperty("name", name);
        	datastore.put(entity);
        	return new Patient(entity);
        }
 
        public static Patient getPatient(String keyString) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity entity = null;
        Key key = null;
       
        try {
                key = KeyFactory.stringToKey(keyString);
        } catch(IllegalArgumentException iae) {
                return null;
        }
       
        try {
                 entity = datastore.get(key);
        } catch (EntityNotFoundException enfe) {
                entity = null;
        }
 
        return new Patient(entity);
        }
 
        public static List<Patient> getAllPatients() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("Employee");
        PreparedQuery pq = datastore.prepare(query);
       
        List<Patient> patients = new ArrayList<Patient>();
       
        for (Entity entity : pq.asIterable()) {  
                patients.add(new Patient(entity));
        }
        return patients;
        }
}