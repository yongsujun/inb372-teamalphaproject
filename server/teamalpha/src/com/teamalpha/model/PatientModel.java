package com.teamalpha.model;

import com.teamalpha.datastore.*;


public class PatientModel {
	String id;
	String name;
	String address;
	String careTakersName;
	String careTakersPhone;
	
	public PatientModel() {
		
	}
	
	public PatientModel(Patient patient) {
		this.id = patient.getID();
		this.name = patient.getName();
		this.address = patient.getAddress();
		this.careTakersName = patient.getCaretakersName();
		this.careTakersPhone = patient.getCaretakersPhone();
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

	public String getCareTakersName() {
		return careTakersName;
	}

	public void setCareTakersName(String careTakersName) {
		this.careTakersName = careTakersName;
	}

	public String getCareTakersPhone() {
		return careTakersPhone;
	}

	public void setCareTakersPhone(String careTakersPhone) {
		this.careTakersPhone = careTakersPhone;
	}
	
	
}
