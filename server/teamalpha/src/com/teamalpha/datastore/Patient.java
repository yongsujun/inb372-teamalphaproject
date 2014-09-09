package com.teamalpha.datastore;

import com.google.appengine.api.datastore.*;

import java.util.ArrayList;
import com.teamalpha.model.PatientModel;
import com.teamalpha.datastore.Caretaker;

public class Patient extends EntityWrapper {

	public Patient() {
		super();
	}

	public Patient(Entity entity) {
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

	public GeoPt getLocation() {
		return (GeoPt) super.get("location");
	}

	public void setLocation(GeoPt value) {
		super.set("location", value);
	}
	
	public void setEmail(String email) {
		super.set("email", email);
	}
	
	public String getEmail() {
		return (String) super.get("email");
	}

	public GeoPt getPointOne() {
		return (GeoPt) super.get("Point One");
	}
	
	public void setPointOne(GeoPt value) {
		super.set("Point One", value);
	}
	
	public GeoPt getPointTwo() {
		return (GeoPt) super.get("Point Two");
	}
	
	public void setPointTwo(GeoPt value) {
		super.set("Point Two", value);
	}
	
	public GeoPt getPointThree() {
		return (GeoPt) super.get("Point Three");
	}
	
	public void setPointThree(GeoPt value) {
		super.set("Point Three", value);
	}
	
	public GeoPt getPointFour() {
		return (GeoPt) super.get("Point Four");
	}
	
	public void setPointFour(GeoPt value) {
		super.set("Point Four", value);
	}

	public PatientModel getModel() {
		return new PatientModel(this);
	}

	public void copyModel(PatientModel model) {
		if (model.getName() != null) {
			this.setName(model.getName());
		}
		if (model.getAddress() != null) {
			this.setAddress(model.getAddress());
		}
		if (model.getEmail() != null) {
			this.setEmail(model.getEmail());
		}
		
	}

}
