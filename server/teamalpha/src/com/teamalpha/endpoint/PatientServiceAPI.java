package com.teamalpha.endpoint;

import com.teamalpha.datastore.*;
import com.teamalpha.model.*;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.NotFoundException;

@Api(name="patient", version="v1")
public class PatientServiceAPI {
	
	@ApiMethod(name="add")
	public AddResourceModel addPatient(@Named("name") String name, @Named("address") String address,
			@Named("caretakersName") String caretakersName, @Named("caretakersPhone") String caretakersPhone) {
		Patient p = DatastoreManager.createPatient();
		p.setName(name);
		p.setAddress(address);
		p.setCaretakersName(caretakersName);
		p.setCaretakersPhone(caretakersPhone);
		p.commit();
		return new AddResourceModel(true, p.getID());
	}
	
	@ApiMethod(name="get")
	public PatientModel getPatient(@Named("id") String id) throws NotFoundException {
		Patient p = DatastoreManager.getPatient(id);
		if (p == null) {
			throw new NotFoundException("Patient not found");
		} else {
			return p.getResponseModel();
		}
	}
	/*
	@ApiMethod(name="update")
	public PatientResponseModel updatePatient() {
		
	} */
	

}
