package com.teamalpha.unitTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.teamalpha.datastore.*;

public class CaretakerTests {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	private final String CARETAKER_NAME = "Alice Caretaker";
	private final String CARETAKER_ADDRESS = "123 Fake St.";
	private final String CARETAKER_PHONENUMBER = "12345678";
	private final String CARETAKER_EMAIL = "alice.caretaker@email.com";

	private Caretaker testInstance;
	private Patient testPatient;

	@Before
	public void setUp() throws Exception {
		helper.setUp();
		testInstance = DatastoreManager.createCaretaker();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void test_GetID_InitialisedCorrectly() {
		testInstance.commit();
		String keyString = testInstance.getID();
		Caretaker stored = DatastoreManager.getCaretaker(keyString);
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
	public void test_GetPhoneNumber_InitiallyNull() {
		assertEquals(null, testInstance.getPhoneNumber());
	}

	@Test
	public void test_GetEmail_InitiallyNull() {
		assertEquals(null, testInstance.getEmail());
	}

	@Test
	public void test_GetPatients_InitiallyNoPatients() {
		assertEquals(0, testInstance.getPatients().size());
	}

	@Test
	public void test_SetName_CorrectlySet() {
		testInstance.setName(CARETAKER_NAME);
		testInstance.commit();
		Caretaker storedTestInstance = DatastoreManager
				.getCaretaker(testInstance.getID());
		assertEquals(CARETAKER_NAME, storedTestInstance.getName());
	}

	@Test
	public void test_SetAddress_CorrectlySet() {
		testInstance.setAddress(CARETAKER_ADDRESS);
		testInstance.commit();
		Caretaker storedTestInstance = DatastoreManager
				.getCaretaker(testInstance.getID());
		assertEquals(CARETAKER_ADDRESS, storedTestInstance.getAddress());
	}

	@Test
	public void test_SetPhoneNumber_CorrectlySet() {
		testInstance.setPhoneNumber(CARETAKER_PHONENUMBER);
		testInstance.commit();
		Caretaker storedTestInstance = DatastoreManager
				.getCaretaker(testInstance.getID());
		assertEquals(CARETAKER_PHONENUMBER, storedTestInstance.getPhoneNumber());
	}

	@Test
	public void test_SetEmail_CorrectlySet() {
		testInstance.setEmail(CARETAKER_EMAIL);
		testInstance.commit();
		Caretaker storedTestInstance = DatastoreManager
				.getCaretaker(testInstance.getID());
		assertEquals(CARETAKER_EMAIL, storedTestInstance.getEmail());
	}







	// helper method to commit two patients to the datastore
	private List<Patient> addTwoPatients() {
		DatastoreManager.createPatient();
		DatastoreManager.createPatient();
		List<Patient> patients = DatastoreManager.getAllPatients();
		return patients;
	}





	// helper method to commit two caretakers to the datastore
	private List<Caretaker> commitTwoCaretakers() {
		DatastoreManager.createCaretaker();
		DatastoreManager.createCaretaker();
		List<Caretaker> caretakers = DatastoreManager.getAllCaretakers();
		return caretakers;
	}

	@Test
	public void test_CreateMultipleCaretakers_UniqueID() {
		List<Caretaker> caretakers = commitTwoCaretakers();
		assertFalse(caretakers.get(0).getID() == caretakers.get(1).getID());
	}

	@Test
	public void test_MultipleCaretakers_ModificationOnOne() {
		List<Caretaker> caretakers = commitTwoCaretakers();
		caretakers.get(0).setName(CARETAKER_NAME);
		caretakers.get(0).commit();
		Caretaker storedCaretaker = DatastoreManager.getCaretaker(caretakers
				.get(0).getID());
		assertEquals(CARETAKER_NAME, storedCaretaker.getName());
		// second caretaker added unaffected by change to first
		caretakers.get(1).commit();
		assertEquals("",
				DatastoreManager.getCaretaker(caretakers.get(1).getID())
						.getName());
	}

	@Test
	public void test_ChangeNotCommitted() {
		testInstance.commit();
		testInstance.setName(CARETAKER_NAME);
		Caretaker storedTestInstance = DatastoreManager
				.getCaretaker(testInstance.getID());
		assertEquals("", storedTestInstance.getName());
	}

}
