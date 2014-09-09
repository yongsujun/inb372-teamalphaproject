package com.teamalpha.model;

import com.google.appengine.api.datastore.GeoPt;
import java.util.ArrayList;
import com.teamalpha.datastore.*;

public class PatientModel {
	String id;
	String name;
	String address;
	GeoPt location;
	String email;
	
	
	public PatientModel() {

	}

	public PatientModel(Patient patient) {
		this.id = patient.getID();
		this.name = patient.getName();
		this.address = patient.getAddress();
		this.location = patient.getLocation();
		this.email = patient.getEmail();

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

	public GeoPt getLocation() {
		return location;
	}

	public void setLocation(GeoPt location) {
		this.location = location;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	

}
