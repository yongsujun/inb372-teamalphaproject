package com.teamalpha.model;

import com.google.appengine.api.datastore.GeoPt;
import java.util.ArrayList;
import com.teamalpha.datastore.*;

public class PatientModel {
	String id;
	String name;
	String address;
	String location;
	ArrayList<String> caretakers = new ArrayList<String>();

	public PatientModel() {

	}

	public PatientModel(Patient patient) {
		this.id = patient.getID();
		this.name = patient.getName();
		this.address = patient.getAddress();
		this.location = patient.getLocation();
		this.caretakers.addAll(patient.getCaretakers());
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(GeoPt location) {
		this.location = location.toString();
	}

	public ArrayList<String> getCaretakers() {
		return caretakers;
	}

	public void setCaretakers(ArrayList<String> caretakers) {
		this.caretakers = caretakers;
	}

}
