package com.teamalpha.endpoint;

import com.teamalpha.datastore.*;
import com.teamalpha.responsemodel.*;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.NotFoundException;

@Api(name="patient", version="v1")
public class PatientServiceAPI {
	
	@ApiMethod(name="add")
	public AddResourceResponseModel addPatient(@Named("name") String name, @Named("address") String address,
			@Named("caretakersName") String caretakersName, @Named("caretakersPhone") String caretakersPhone) {
		Patient p = DatastoreManager.createPatient();
		p.setName(name);
		p.setAddress(address);
		p.setCaretakersName(caretakersName);
		p.setCaretakersPhone(caretakersPhone);
		p.commit();
		return new AddResourceResponseModel(true, p.getID());
	}
	
	@ApiMethod(name="get")
	public PatientResponseModel getPatient(@Named("id") String id) throws NotFoundException {
		Patient p = DatastoreManager.getPatient(id);
		if (p == null) {
			throw new NotFoundException("Patient not found");
		} else {
			return p.getResponseModel();
		}
	}
	

}
