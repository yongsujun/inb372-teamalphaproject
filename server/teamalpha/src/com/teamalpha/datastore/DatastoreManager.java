package com.teamalpha.datastore;

import java.util.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import com.teamalpha.model.PatientModel;
import com.teamalpha.model.CaretakerModel;

public class DatastoreManager {

	public final static String PATIENT_MODEL_STRING = "Patient";
	public final static String CARETAKER_MODEL_STRING = "Caretaker";
	public final static String PATIENT_CARETAKER_MODEL_STRING = "PatientCaretaker";
	
	public final static int PATIENT_NOT_FOUND     = 101;
	public final static int CARETAKER_NOT_FOUND   = 102;
	
	public final static int PATIENT_CARETAKER_ADDED = 401;
	public final static int PATIENT_ALREADY_ADDED = 402;
	
	
	public static Patient createPatient() {
		return DatastoreManager.createPatient("");
	}

	/**
	 * @todo Check if creation failed
	 * @param name
	 * @return
	 */
	public static Patient createPatient(String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity entity = new Entity(DatastoreManager.PATIENT_MODEL_STRING);
		entity.setProperty("name", name);
		entity.setProperty("caretakers", new ArrayList<String>());
		datastore.put(entity);
		return new Patient(entity);
	}

	public static Patient getPatient(String keyString) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity entity = null;
		Key key = null;

		try {
			key = KeyFactory.stringToKey(keyString);
		} catch (IllegalArgumentException iae) {
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
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query(DatastoreManager.PATIENT_MODEL_STRING);
		PreparedQuery pq = datastore.prepare(query);

		List<Patient> patients = new ArrayList<Patient>();

		for (Entity entity : pq.asIterable()) {
			patients.add(new Patient(entity));
		}
		return patients;
	}

	public static List<PatientModel> getAllPatientModels() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query(DatastoreManager.PATIENT_MODEL_STRING);
		PreparedQuery pq = datastore.prepare(query);

		List<PatientModel> patients = new ArrayList<PatientModel>();

		for (Entity entity : pq.asIterable()) {
			patients.add(new Patient(entity).getModel());
		}
		return patients;
	}

	// Caretaker
	public static Caretaker createCaretaker() {
		return DatastoreManager.createCaretaker("");
	}

	/**
	 * @todo Check if creation failed
	 * @param name
	 * @return
	 */
	public static Caretaker createCaretaker(String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity entity = new Entity(DatastoreManager.CARETAKER_MODEL_STRING);
		entity.setProperty("name", name);
		entity.setProperty("patients", new ArrayList<String>());
		datastore.put(entity);
		return new Caretaker(entity);
	}

	public static Caretaker getCaretaker(String keyString) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity entity = null;
		Key key = null;

		try {
			key = KeyFactory.stringToKey(keyString);
		} catch (IllegalArgumentException iae) {
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
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query(DatastoreManager.CARETAKER_MODEL_STRING);
		PreparedQuery pq = datastore.prepare(query);

		List<Caretaker> caretakers = new ArrayList<Caretaker>();

		for (Entity entity : pq.asIterable()) {
			caretakers.add(new Caretaker(entity));
		}
		return caretakers;
	}

	public static List<CaretakerModel> getAllCaretakerModels() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query(DatastoreManager.CARETAKER_MODEL_STRING);
		PreparedQuery pq = datastore.prepare(query);

		List<CaretakerModel> caretakers = new ArrayList<CaretakerModel>();

		for (Entity entity : pq.asIterable()) {
			caretakers.add(new Caretaker(entity).getModel());
		}
		return caretakers;
	}
	
	public static Caretaker getCaretakerByEmail(String email) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter filter = new FilterPredicate("email", FilterOperator.EQUAL, email);
		Query q = new Query(DatastoreManager.CARETAKER_MODEL_STRING).setFilter(filter);
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			return new Caretaker(result);
		}
		return null;
	}

	public static Patient getPatientByEmail(String email) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter filter = new FilterPredicate("email", FilterOperator.EQUAL, email);
		Query q = new Query(DatastoreManager.PATIENT_MODEL_STRING).setFilter(filter);
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			return new Patient(result);
		}
		return null;
	}	
	

	
	// check if already added
	public static int addPatientCaretaker(String keyString, String patientEmail) {
		
		Patient patient = DatastoreManager.getPatientByEmail(patientEmail);
		if (patient == null) {
			return DatastoreManager.PATIENT_NOT_FOUND;
		}
		
		Caretaker caretaker = DatastoreManager.getCaretaker(keyString);
		if (caretaker == null) {
			return DatastoreManager.CARETAKER_NOT_FOUND;
		}
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity(DatastoreManager.PATIENT_CARETAKER_MODEL_STRING);
		entity.setProperty("patient-key", patient.getID());
		entity.setProperty("caretaker-key", caretaker.getID());
		datastore.put(entity);
		
		return DatastoreManager.PATIENT_CARETAKER_ADDED;
	}
	
	public static List<PatientModel> getPatientsByCaretaker(String caretakerKey) {
		List<PatientModel> list = new ArrayList<PatientModel>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter filter = new FilterPredicate("caretaker-key", FilterOperator.EQUAL, caretakerKey);
		Query q = new Query(DatastoreManager.PATIENT_CARETAKER_MODEL_STRING).setFilter(filter);
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			list.add(new PatientModel(DatastoreManager.getPatient(new PatientCaretaker(result).getPatientKey())));
		}
		return list;		
	}
	
}
