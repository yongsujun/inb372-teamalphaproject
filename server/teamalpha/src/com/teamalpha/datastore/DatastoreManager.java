package com.teamalpha.datastore;

import java.util.*;
import com.google.appengine.api.datastore.*;
import com.teamalpha.model.PatientModel;
import com.teamalpha.model.CaretakerModel;
 
public class DatastoreManager {
      
	public final static String PATIENT_MODEL_STRING = "Patient";
	public final static String CARETAKER_MODEL_STRING = "Caretaker";
	
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
    
    public static List<PatientModel> getAllPatientModels() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query(DatastoreManager.PATIENT_MODEL_STRING);
        PreparedQuery pq = datastore.prepare(query);
       
        List<PatientModel> patients = new ArrayList<PatientModel>();
       
        for (Entity entity : pq.asIterable()) {  
                patients.add(new Patient(entity).getModel());
        }
        return patients;
    }
    
    
    //Caretaker
    	public static Caretaker createCaretaker() {
		return DatastoreManager.createCaretaker("");
	}
	
	/**
	 * @todo Check if creation failed
	 * @param name
	 * @return
	 */
    public static Caretaker createCaretaker(String name) {
    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity entity = new Entity(DatastoreManager.CARETAKER_MODEL_STRING);
        entity.setProperty("name", name);
        datastore.put(entity);
        return new Caretaker(entity);
    }
 
    public static Caretaker getCaretaker(String keyString) {
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
 
        return new Caretaker(entity);
    }
 
    public static List<Caretaker> getAllCaretakers() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query(DatastoreManager.CARETAKER_MODEL_STRING);
        PreparedQuery pq = datastore.prepare(query);
       
        List<Caretaker> caretakers = new ArrayList<Caretaker>();
       
        for (Entity entity : pq.asIterable()) {  
                caretakers.add(new Caretaker(entity));
        }
        return caretakers;
    }
    
    public static List<CaretakerModel> getAllCaretakerModels() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query(DatastoreManager.CARETAKER_MODEL_STRING);
        PreparedQuery pq = datastore.prepare(query);
       
        List<CaretakerModel> caretakers = new ArrayList<CaretakerModel>();
       
        for (Entity entity : pq.asIterable()) {  
                caretakers.add(new Caretaker(entity).getModel());
        }
        return caretakers;
    }
}
