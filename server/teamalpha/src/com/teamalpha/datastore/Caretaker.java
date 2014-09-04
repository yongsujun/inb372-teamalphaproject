package com.teamalpha.datastore;

import com.google.appengine.api.datastore.*;

import java.util.ArrayList;

import com.teamalpha.model.CaretakerModel;
import com.teamalpha.datastore.Patient;

public class Caretaker extends EntityWrapper {

	private ArrayList<String> patients = new ArrayList<String>();

	public Caretaker() {
		super();
	}

	public Caretaker(Entity entity) {
		super(entity);
	}

	public String getName() {
		return (String) super.get("name");
	}

	public void setName(String value) {
		super.set("name", value);
	}

	public String getAddress() {
		return (String) super.get("address");
	}

	public void setAddress(String value) {
		super.set("address", value);
	}

	public String getID() {
		return super.keyString();
	}

	public String getPhoneNumber() {
		return (String) super.get("phone_number");
	}

	public void setPhoneNumber(String value) {
		super.set("phone_number", value);
	}

	public String getEmail() {
		return (String) super.get("email");
	}

	public void setEmail(String value) {
		super.set("email", value);
	}

	public ArrayList<String> getPatients() {
		return (ArrayList<String>) super.get("patients");
	}

	private void setPatients() {
		super.set("patients", this.patients);
	}

	public void addPatient(Patient patient) {

		this.patients = getPatients();
		if (patients == null) {
			patients = new ArrayList<String>();
		}
		if (!(patients.contains(patient.getID()))) {
			patients.add(patient.getID());
			// need to ensure data is matched for patient's caretakers
		}
		setPatients();
	}

	public void removePatient(Patient patient) {
		this.patients = getPatients();
		if (!(patients == null)) {
			patients.remove(patient.getID());
			// need ensure data is matched for patient's caretakers
		}
		setPatients();
	}

	public CaretakerModel getModel() {
		return new CaretakerModel(this);
	}

	public void copyModel(CaretakerModel model) {
		if (model.getName() != null) {
			this.setName(model.getName());
		}
		if (model.getAddress() != null) {
			this.setAddress(model.getAddress());
		}
		if (model.getPhoneNumber() != null) {
			this.setPhoneNumber(model.getPhoneNumber());
		}
		if (model.getEmail() != null) {
			this.setEmail(model.getEmail());
		}
		if (model.getPatients() != null) {
			this.patients.addAll(model.getPatients());
			this.setPatients();
		}
	}

}
