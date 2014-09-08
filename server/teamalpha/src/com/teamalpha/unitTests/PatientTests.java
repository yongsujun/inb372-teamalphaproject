package com.teamalpha.unitTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.teamalpha.datastore.*;

public class PatientTests {
	
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	private final float MIDDLE_LATITUDE = (float) 0.0;
	private final float MIDDLE_LONGITUDE = (float) 0.0;
	
	private final String PATIENT_NAME = "Joe Bloggs";
	private final String PATIENT_ADDRESS = "123 Fake St, Fakeville";
	private final GeoPt PATIENT_LOCATION = new GeoPt(MIDDLE_LATITUDE, MIDDLE_LONGITUDE);
	private final String PATIENT_NAME_SECOND = "Bob Brown";
	
	private Caretaker caretaker;
	private Patient testInstance;
	private String testPatientKey;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		testInstance = DatastoreManager.createPatient();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}
	
	@Test
	public void test_GetID_InitialisedCorrectly() {
		testInstance.commit();
		String keyString = testInstance.getID();
		Patient stored = DatastoreManager.getPatient(keyString);
		assertEquals(keyString, stored.getID());
	}
	
	@Test
	public void test_GetName_InitiallyEmpty() {
		assertEquals("", testInstance.getName());
	}
	
	@Test
	public void test_GetAddress_InitiallyNull() {
		assertEquals(null, testInstance.getAddress());
	}
	
	@Test
	public void test_GetLocation_InitiallyNull() {
		assertEquals(null, testInstance.getLocation());
	}
	
	@Test
	public void test_GetCaretakers_InitiallyNoCaretakers() {
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(null, storedTestInstance.getCaretakers());
	}
	
	@Test
	public void test_SetNameCorrectly() {
		testInstance.setName(PATIENT_NAME);
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(PATIENT_NAME, storedTestInstance.getName());
	}
	
	@Test
	public void test_SetAddressCorrectly() {
		testInstance.setAddress(PATIENT_ADDRESS);
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(PATIENT_ADDRESS, storedTestInstance.getAddress());
	}
	
	@Test
	public void test_SetLocationCorrectly() {
		String expected = PATIENT_LOCATION.toString();
		testInstance.setLocation(PATIENT_LOCATION);
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(expected, storedTestInstance.getLocation());
	}
	
	@Test
	public void test_AddCaretaker_OneCaretaker() {
		caretaker = DatastoreManager.createCaretaker();
		testInstance.addCaretaker(caretaker);
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(1, storedTestInstance.getCaretakers().size());
	}
	
	@Test
	public void test_RemoveCaretaker_OnlyCaretakerRemoved() {
		caretaker = DatastoreManager.createCaretaker();
		caretaker.commit();
		testInstance.addCaretaker(caretaker);
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(1, storedTestInstance.getCaretakers().size());
		testInstance.removeCaretaker(caretaker);
		testInstance.commit();
		storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(null, storedTestInstance.getCaretakers());
	}
	
	// helper method to commit two caretakers to the datastore
	private List<Caretaker> addTwoCaretakers() {
		DatastoreManager.createCaretaker();
		DatastoreManager.createCaretaker();
		List<Caretaker> caretakers = DatastoreManager.getAllCaretakers();
		return caretakers;
	}
	
	@Test
	public void test_AddCaretaker_MultipleCaretakers() {
		List<Caretaker> caretakers = addTwoCaretakers();
		testInstance.addCaretaker(caretakers.get(0));
		testInstance.addCaretaker(caretakers.get(1));
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(2, storedTestInstance.getCaretakers().size());
	}
	
	@Test
	public void test_RemoveCaretaker_OneCaretakerFromTwo() {
		List<Caretaker> caretakers = addTwoCaretakers();
		testInstance.addCaretaker(caretakers.get(0));
		testInstance.addCaretaker(caretakers.get(1));
		testInstance.commit();
		testInstance.removeCaretaker(caretakers.get(0));
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(1, storedTestInstance.getCaretakers().size());
	}
	
	// helper method to commit two patients to the datastore
	private List<Patient> commitTwoPatients() {
		DatastoreManager.createPatient();
		DatastoreManager.createPatient();
		List<Patient> patients = DatastoreManager.getAllPatients();
		return patients;
	}
	
	@Test
	public void test_CreateMultiplePatients_UniqueID() {
		List<Patient> patients = commitTwoPatients();
		assertFalse(patients.get(0).getID() == patients.get(1).getID());
	}
	
	@Test
	public void test_MultiplePatients_ModificationOnOne() {
		List<Patient> patients = commitTwoPatients();
		patients.get(0).setName(PATIENT_NAME);
		patients.get(0).commit();
		assertEquals(PATIENT_NAME, patients.get(0).getName());
		// second patient added unaffected by update to first
		assertEquals("", patients.get(1).getName());
	}
	
	@Test
	public void test_ChangeNotCommitted() {
		List<Patient> patients = commitTwoPatients();
		patients.get(0).setName(PATIENT_NAME_SECOND);
		List<Patient> patientsEdit = DatastoreManager.getAllPatients();
		assertEquals("", patientsEdit.get(0).getName());
	}
	
	@Test
	public void test_PatientKey_SetUpCorrectly() {
		testInstance.commit();
		testPatientKey = testInstance.getID();
		Patient patient = DatastoreManager.getPatient(testPatientKey);
		assertEquals(testInstance.getID(), patient.getID());
	}

}