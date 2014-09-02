package com.teamalpha.datastore;

import com.google.appengine.api.datastore.*;
import com.teamalpha.model.CaretakerModel;

public class Patient extends EntityWrapper {

        public Caretaker() {
        super();
        }
        
        public Caretaker(Entity entity) {
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
    
    public String getPhoneNumber() {
        return (String)super.get("phone_number");
    }
    
    public void setPhoneNumber(String value) {
        super.set("phone_number", value);
    }
    
    public String getEmail() {
        return (String)super.get("email");
    }
    
    public void setEmail(String value) {
        super.set("email", value);
    }
    
    public List<Patient> getPatients() {
        return null;
    }
 
    public void addPatient(Patient patient) {
        
    }
   
    public void removePatient(Patient patient) {
        
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
    }

}
