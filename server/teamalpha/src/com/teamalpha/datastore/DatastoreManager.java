package com.teamalpha.datastore;

import java.util.*;
import com.google.appengine.api.datastore.*;
 
public class DatastoreManager {
      
	public final static String PATIENT_MODEL_STRING = "Patient";
	
	public static Patient createPatient() {
		return DatastoreManager.createPatient("");
	}
	
	/**
	 * @todo Check if creation failed
	 * @param name
	 * @return
	 */
    public static Patient createPatient(String name) {
    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity entity = new Entity(DatastoreManager.PATIENT_MODEL_STRING);
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
        Query query = new Query(DatastoreManager.PATIENT_MODEL_STRING);
        PreparedQuery pq = datastore.prepare(query);
       
        List<Patient> patients = new ArrayList<Patient>();
       
        for (Entity entity : pq.asIterable()) {  
                patients.add(new Patient(entity));
        }
        return patients;
        }
}