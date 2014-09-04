package com.teamalpha.datastore;

import com.google.appengine.api.datastore.*;

import java.util.ArrayList;

import com.teamalpha.model.PatientModel;
import com.teamalpha.datastore.Caretaker;

public class Patient extends EntityWrapper {

	private ArrayList<String> caretakers = new ArrayList<String>();;

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

	public String getLocation() {
		return (String) super.get("location");
	}

	public void setLocation(GeoPt value) {
		super.set("location", value.toString());
	}

	public ArrayList<String> getCaretakers() {
		return (ArrayList<String>) super.get("caretakers");
	}

	private void setCaretakers() {
		super.set("caretakers", this.caretakers);
	}

	public void addCaretaker(Caretaker carer) {
		this.caretakers = getCaretakers();
		if (caretakers == null) {
			caretakers = new ArrayList<String>();
		}
		if (!(caretakers.contains(carer.getID()))) {
			caretakers.add(carer.getID());
			// need a way to ensure that data matches caretaker's patients
		}
		setCaretakers();
	}

	public void removeCaretaker(Caretaker carer) {
		this.caretakers = getCaretakers();
		if (!(caretakers == null)) {
			caretakers.remove(carer.getID());
			// need a way to ensure that data matches caretaker's patients
		}

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
		if (model.getCaretakers() != null) {
			this.caretakers.addAll(model.getCaretakers());
			this.setCaretakers();
		}
	}

}
