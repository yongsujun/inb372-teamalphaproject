package com.teamalpha.unitTests;

import static org.junit.Assert.*;
import java.util.List;

import com.teamalpha.datastore.*;
import com.teamalpha.model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.teamalpha.datastore.DatastoreManager;

public class DatastoreManagerTests {
	

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	
	private final String INVALID_KEY_STRING = "";
	
	// patient properties
	private final String PATIENT_NAME = "Joe Bloggs";
	private final String PATIENT_ADDRESS = "123 Fake St, Fakeville";
	private final GeoPt PATIENT_LOCATION = new GeoPt(0, 0);
	private final String PATIENT_EMAIL = "joe.bloggs@email.com";
	
	// caretaker properties
	private final String CARETAKER_NAME = "Alice Caretaker";
	private final String CARETAKER_ADDRESS = "123 Fake St.";
	private final String CARETAKER_PHONENUMBER = "12345678";
	private final String CARETAKER_EMAIL = "alice.caretaker@email.com";
	private final String CARETAKER_PASSWORD = "AwesomePassword1";
	
	private final String UNASSIGNED_PATIENT_EMAIL = "not.a.patient@email.com";
	private final String UNASSIGNED_CARETAKER_EMAIL = "not.a.caretaker@email.com";
	private final String UNASSIGNED_CARETAKER_KEYSTRING = "notakeyforcaretaker";
	
	private final int PATIENT_NOT_FOUND_ERROR = DatastoreManager.PATIENT_NOT_FOUND;
	private final int CARETAKER_NOT_FOUND_ERROR = DatastoreManager.CARETAKER_NOT_FOUND;
	private final int PATIENT_CARETAKER_ADD_SUCCESS = DatastoreManager.PATIENT_CARETAKER_ADDED;
	
	private Caretaker caretaker;
	private Patient patient;

	@Before
	public void setUp() throws Exception {
		helper.setUp();
		patient = DatastoreManager.createPatient();
		caretaker = DatastoreManager.createCaretaker();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}
	
	// helper method to set patient properties
	private void setPatientProperties() {
		patient.setName(PATIENT_NAME);
		patient.setAddress(PATIENT_ADDRESS);
		patient.setLocation(PATIENT_LOCATION);
		patient.setEmail(PATIENT_EMAIL);
		patient.commit();
	}
	
	// helper method to set caretaker properties
	private void setCaretakerProperties() {
		caretaker.setName(CARETAKER_NAME);
		caretaker.setAddress(CARETAKER_ADDRESS);
		caretaker.setPhoneNumber(CARETAKER_PHONENUMBER);
		caretaker.setEmail(CARETAKER_EMAIL);
		caretaker.setPassword(CARETAKER_PASSWORD);
		caretaker.commit();
	}

	@Test
	public void test_GetPatient_InvalidKey() {
		assertEquals(null, DatastoreManager.getPatient(INVALID_KEY_STRING));
	}
	
	@Test
	public void test_GetCaretaker_InvalidKey() {
		assertEquals(null, DatastoreManager.getCaretaker(INVALID_KEY_STRING));
	}
	
	@Test
	public void test_GetCaretakerByEmail_NoCaretaker() {
		setCaretakerProperties();
		assertEquals(null, DatastoreManager.getCaretakerByEmail(UNASSIGNED_CARETAKER_EMAIL));
	}
	
	@Test
	public void test_GetCaretakerByEmail_ExistingCaretaker() {
		setCaretakerProperties();
		assertEquals(caretaker.getID(), DatastoreManager.getCaretakerByEmail(CARETAKER_EMAIL).getID());
	}
	
	@Test
	public void test_GetPatientByEmail_NoPatient() {
		setPatientProperties();
		assertEquals(null, DatastoreManager.getPatientByEmail(UNASSIGNED_PATIENT_EMAIL));
	}
	
	@Test
	public void test_GetPatientByEmail_ExistingPatient() {
		setPatientProperties();
		assertEquals(patient.getID(), DatastoreManager.getPatientByEmail(PATIENT_EMAIL).getID());
	}
	
	@Test
	public void test_AddPatientCaretaker_PatientNotFound() {
		setPatientProperties();
		setCaretakerProperties();
		assertEquals(PATIENT_NOT_FOUND_ERROR, DatastoreManager.addPatientCaretaker(caretaker.getID(), UNASSIGNED_PATIENT_EMAIL));
	}
	
	@Test
	public void test_AddPatientCaretaker_CaretakerNotFound() {
		setPatientProperties();
		setCaretakerProperties();
		assertEquals(CARETAKER_NOT_FOUND_ERROR, DatastoreManager.addPatientCaretaker(UNASSIGNED_CARETAKER_KEYSTRING, PATIENT_EMAIL));
	}
	
	@Test
	public void test_AddPatientCaretaker_SuccessfulSet() {
		setPatientProperties();
		setCaretakerProperties();
		int success = DatastoreManager.addPatientCaretaker(caretaker.getID(), PATIENT_EMAIL);
		assertEquals(PATIENT_CARETAKER_ADD_SUCCESS, success);
	}
	
	@Test
	public void test_AddPatientCaretaker_CorrectlySet() {
		setPatientProperties();
		setCaretakerProperties();
		int success = DatastoreManager.addPatientCaretaker(caretaker.getID(), PATIENT_EMAIL);
		List<PatientModel> caretakersPatients = DatastoreManager.getPatientsByCaretaker(caretaker.getID());
		assertEquals(PATIENT_CARETAKER_ADD_SUCCESS, success);
		assertEquals(1, caretakersPatients.size());
		assertEquals(patient.getID(), caretakersPatients.get(0).getId());
	}

}
