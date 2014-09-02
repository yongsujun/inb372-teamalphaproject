package com.teamalpha.datastore;

import com.google.appengine.api.datastore.*;
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
        return (String)super.get("name");
    }
 
    public void setName(String value) {
        super.set("name", value);
    }
   
    public String getAddress() {
        return (String)super.get("address");
    }
 
    public void setAddress(String value) {
        super.set("address", value);
    }
   
    public String getID() {
        return super.keyString();
    }
   
    public String getLocation() {
        return (String)super.get("location");
    }
 
    public void setLocation(GeoPt value) {
        super.set("location", value.toString());
    }
   
    public List<Caretaker> getCaretakers() {
        return null;
    }
 
    public void addCaretaker(Caretaker carer) {
        
    }
   
    public void removeCaretaker(Caretaker carer) {
        
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
    	if (model.getCareTakersName() != null) {
    		this.setCaretakersName(model.getCareTakersName());
    	}
    	if (model.getCareTakersPhone() != null) {
    		this.setCaretakersPhone(model.getCareTakersPhone());
    	}
    }
 
}
