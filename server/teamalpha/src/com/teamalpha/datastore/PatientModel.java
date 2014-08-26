package com.teamalpha.datastore;

public class PatientModel {
	String id;
	String name;
	
	public PatientModel(String id) {
		this.id = id;
	}
	
	public PatientModel(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
}
