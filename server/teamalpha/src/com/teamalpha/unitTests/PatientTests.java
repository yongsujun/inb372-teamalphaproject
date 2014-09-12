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
	
	private final float MAX_ABSOLUTE_LATITUDE = (float) 90;
	private final float MAX_ABSOLUTE_LONGITUDE = (float) 180;
	
	private final float SMALL_INCREASE = (float) 0.01;
	
	private final float MIDDLE_LATITUDE = (float) 0.0;
	private final float MIDDLE_LONGITUDE = (float) 0.0;
	
	private final String PATIENT_NAME = "Joe Bloggs";
	private final String PATIENT_ADDRESS = "123 Fake St, Fakeville";
	private final GeoPt PATIENT_LOCATION = new GeoPt(MIDDLE_LATITUDE, MIDDLE_LONGITUDE);
	private final String PATIENT_EMAIL = "joe.bloggs@email.com";
	private final String PATIENT_NAME_SECOND = "Bob Brown";
	
	private final GeoPt VF_POINT_ONE = new GeoPt(-45, 90);
	private final GeoPt VF_POINT_TWO = new GeoPt(45, 90);
	private final GeoPt VF_POINT_THREE = new GeoPt(45, -90);
	private final GeoPt VF_POINT_FOUR = new GeoPt(-45, -90);
	
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
	public void test_GetEmail_InitiallyNull() {
		assertEquals(null, testInstance.getEmail());
	}
	
	// tests for points of virtual fence
	@Test
	public void test_GetPoint_AllInitiallyNull() {
		assertEquals(null, testInstance.getPointOne());
		assertEquals(null, testInstance.getPointTwo());
		assertEquals(null, testInstance.getPointThree());
		assertEquals(null, testInstance.getPointFour());
	}
	
	// note tests below for setLocation test boundary constraints on GeoPt so not needed to test here as well
	@Test
	public void test_SetPointOne_CorrectlySet() {
		testInstance.setPointOne(VF_POINT_ONE);
		testInstance.commit();
		assertEquals(VF_POINT_ONE, testInstance.getPointOne());
		assertEquals(null, testInstance.getPointTwo());
		assertEquals(null, testInstance.getPointThree());
		assertEquals(null, testInstance.getPointFour());
	}
	
	@Test
	public void test_SetPointTwo_CorrectlySet() {
		testInstance.setPointTwo(VF_POINT_TWO);
		testInstance.commit();
		assertEquals(null, testInstance.getPointOne());
		assertEquals(VF_POINT_TWO, testInstance.getPointTwo());
		assertEquals(null, testInstance.getPointThree());
		assertEquals(null, testInstance.getPointFour());
	}
	
	@Test
	public void test_SetPointThree_CorrectlySet() {
		testInstance.setPointThree(VF_POINT_THREE);
		testInstance.commit();
		assertEquals(null, testInstance.getPointOne());
		assertEquals(null, testInstance.getPointTwo());
		assertEquals(VF_POINT_THREE, testInstance.getPointThree());
		assertEquals(null, testInstance.getPointFour());
	}
	
	@Test
	public void test_SetPointFour_CorrectlySet() {
		testInstance.setPointFour(VF_POINT_FOUR);
		testInstance.commit();
		assertEquals(null, testInstance.getPointOne());
		assertEquals(null, testInstance.getPointTwo());
		assertEquals(null, testInstance.getPointThree());
		assertEquals(VF_POINT_FOUR, testInstance.getPointFour());
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
	
	@Test(expected = IllegalArgumentException.class)
	public void test_SetLocation_LatitudeAboveMax() {
		testInstance.setLocation(new GeoPt(MAX_ABSOLUTE_LATITUDE + SMALL_INCREASE, MIDDLE_LONGITUDE));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_SetLocation_LatitudeBelowMin() {
		testInstance.setLocation(new GeoPt(-(MAX_ABSOLUTE_LATITUDE + SMALL_INCREASE), MIDDLE_LONGITUDE));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_SetLocation_LongitudeAboveMax() {
		testInstance.setLocation(new GeoPt(MIDDLE_LATITUDE, MAX_ABSOLUTE_LONGITUDE + SMALL_INCREASE));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_SetLocation_LongitudeBelowMin() {
		testInstance.setLocation(new GeoPt(MIDDLE_LATITUDE, -(MAX_ABSOLUTE_LONGITUDE + SMALL_INCREASE)));
	}
	
	@Test
	public void test_SetLocation_MaxLatitudeLongitude() {
		GeoPt maxPoint = new GeoPt(MAX_ABSOLUTE_LATITUDE, MAX_ABSOLUTE_LONGITUDE);
		testInstance.setLocation(maxPoint);
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(maxPoint, storedTestInstance.getLocation());
	}
	
	@Test
	public void test_SetLocation_MinLatitudeLongitude() {
		GeoPt minPoint = new GeoPt(-MAX_ABSOLUTE_LATITUDE, -MAX_ABSOLUTE_LONGITUDE);
		testInstance.setLocation(minPoint);
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(minPoint, storedTestInstance.getLocation());	
	}
	
	@Test
	public void test_SetLocationCorrectly() {
		testInstance.setLocation(PATIENT_LOCATION);
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(PATIENT_LOCATION, storedTestInstance.getLocation());
	}
	
	@Test
	public void test_SetEmail_CorrectlySet() {
		testInstance.setEmail(PATIENT_EMAIL);
		testInstance.commit();
		Patient storedTestInstance = DatastoreManager.getPatient(testInstance.getID());
		assertEquals(PATIENT_EMAIL, storedTestInstance.getEmail());
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
