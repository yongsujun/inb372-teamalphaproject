package com.teamalpha.datastore;

import com.google.appengine.api.datastore.*;
import com.teamalpha.model.PatientModel;

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
   
    public String getCaretakersName() {
        return (String)super.get("caretakers_name");
    }
 
    public void setCaretakersName(String value) {
        super.set("caretakers_name", value);
    }
   
    public String getCaretakersPhone() {
        return (String)super.get("caretakers_phone");
    }
 
    public void setCaretakersPhone(String value) {
        super.set("caretakers_phone", value);
    }
 
    @Override
    public String xml() {
        String xml = "<patient>";
        xml += "<key>" + super.keyString() + "</key>";
        xml += "<name>" + super.get("name") + "</name>";
        xml += "<address>" + super.get("address") + "</address>";
        xml += "<location>" + super.get("location") + "</location>";
        xml += "<caretakers_name>" + super.get("caretakers_name") + "</caretakers_name>";
        xml += "<caretakers_phone>" + super.get("caretakers_phone") + "</caretakers_phone>";
        xml += "</patient>";
        return xml;
    }
        
    public PatientModel getResponseModel() {
    	return new PatientModel(this);
    }
 
}