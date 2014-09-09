package com.teamalpha.model;

import java.util.ArrayList;
import com.teamalpha.datastore.*;


public class CaretakerModel {
	String id;
	String name;
	String address;
	String email;
	String phoneNumber;
	String password;
	//ArrayList<String> patients = new ArrayList<String>();
	
	public CaretakerModel() {
		
	}
	
	public CaretakerModel(Caretaker caretaker) {
		this.id = caretaker.getID();
		this.name = caretaker.getName();
		this.address = caretaker.getAddress();
		this.email = caretaker.getEmail();
		this.phoneNumber = caretaker.getPhoneNumber();
		this.password = caretaker.getPassword();
	//	this.patients.addAll(caretaker.getPatients());
		
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
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String passwd) {
		this.password = passwd;
	}
	
	/*
	public ArrayList<String> getPatients() {
		return patients;
	}
	
	public void setPatients(ArrayList<String> patients) {
		this.patients = patients;
	}
	*/
	
}
