package com.teamalpha.datastore;

import com.google.appengine.api.datastore.*;

public class PatientCaretaker extends EntityWrapper  {

	public PatientCaretaker() {
		super();
	}
	
	public PatientCaretaker(Entity entity) {
		super(entity);
	}
	
	public String getPatientKey() {
		return (String)super.get("patient-key");
	}
	
	public void setPatientKey(String key){
		super.set("patient-key", key);
	}

	public String getCaretakerKey() {
		return (String)super.get("caretaker-key");
	}
	
	public void setCaretakerKey(String key){
		super.set("caretaker-key", key);
	}	
	
}
