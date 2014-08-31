package com.teamalpha.unitTests;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import com.teamalpha.datastore.*;

public class PatientTests {
	
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private final String PATIENT_NAME = "Joe Bloggs";
	private final String PATIENT_ADDRESS = "123 Fake St, Fakeville";
	private final GeoPt PATIENT_LOCATION = new GeoPt(0, 0);
	private final String PATIENT_CARETAKER_NAME = "Alice Caretaker";
	private final String PATIENT_CARETAKER_PHONE = "0723584256";
	
	private final String PATIENT_NAME_SECOND = "Bob Brown";
	
	private final String testKeyActual = "agR0ZXN0cg0LEgdwYXRpZW50GAEM";
	
	private Patient testPatient;
	private String testPatientKey;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();();
		testPatient = new Patient(new Entity("patient"));
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}
	
	private void doTest_State() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		assertEquals(0, ds.prepare(new Query("patient")).countEntities(withLimit(10)));
		testPatient.commit();
		ds.put(new Entity("patient"));
		assertEquals(2, ds.prepare(new Query("patient")).countEntities(withLimit(10)));
	}

	@Test
	public void test_InitialTest() {
		doTest_State();
	}
	
	@Test
	public void test_UnaffectedByStateOfPreviousTest() {
		doTest_State();
	}
	
	@Test
	public void test_GetID_InitialisedCorrectly() {
		testPatient.commit();
		String key = testPatient.getID();
		assertEquals(testKeyActual, key);
	}
	
	@Test
	public void test_GetName_InitiallyNull() {
		assertEquals(null, testPatient.getName());
	}
	
	@Test
	public void test_GetAddress_InitiallyNull() {
		assertEquals(null, testPatient.getAddress());
	}
	
	@Test
	public void test_GetLocation_InitiallyNull() {
		assertEquals(null, testPatient.getLocation());
	}
	
	@Test
	public void test_GetCaretakersName_InitiallyNull() {
		assertEquals(null, testPatient.getCaretakersName());
	}
	
	@Test
	public void test_GetCaretakersPhone_InitiallyNull() {
		assertEquals(null, testPatient.getCaretakersPhone());
	}
	
	@Test
	public void test_SetNameCorrectly() {
		testPatient.setName(PATIENT_NAME);
		assertEquals(PATIENT_NAME, testPatient.getName());
	}
	
	@Test
	public void test_SetAddressCorrectly() {
		testPatient.setAddress(PATIENT_ADDRESS);
		assertEquals(PATIENT_ADDRESS, testPatient.getAddress());
	}
	
	@Test
	public void test_SetLocationCorrectly() {
		String expected = PATIENT_LOCATION.toString();
		testPatient.setLocation(PATIENT_LOCATION);
		assertEquals(expected, testPatient.getLocation());
	}
	
	@Test
	public void test_SetCaretakersNameCorrectly() {
		testPatient.setCaretakersName(PATIENT_CARETAKER_NAME);
		assertEquals(PATIENT_CARETAKER_NAME, testPatient.getCaretakersName());
	}
	
	@Test
	public void test_SetCaretakersPhoneCorrectly() {
		testPatient.setCaretakersPhone(PATIENT_CARETAKER_PHONE);
		assertEquals(PATIENT_CARETAKER_PHONE, testPatient.getCaretakersPhone());
	}
	
	private List<Patient> commitTwoPatients() {
		DatastoreManager.createPatient();
		DatastoreManager.createPatient();
		List<Patient> patients = DatastoreManager.getAllPatients();
		return patients;
	}
	
	@Test
	public void test_CreateMultiplePatients() {
		List<Patient> patients = commitTwoPatients();
		assertEquals(2, patients.size());
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
		testPatient.commit();
		testPatientKey = testPatient.getID();
		Patient patient = DatastoreManager.getPatient(testPatientKey);
		assertEquals(testPatient.getID(), patient.getID());
	}

}
