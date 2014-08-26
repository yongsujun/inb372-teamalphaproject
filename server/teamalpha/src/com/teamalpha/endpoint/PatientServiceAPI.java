package com.teamalpha.endpoint;

import java.util.List;

import com.teamalpha.datastore.*;
import com.teamalpha.model.*;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;

@Api(name="patient", version="v1")
public class PatientServiceAPI {
	
	@ApiMethod(name="add", path="add", httpMethod = HttpMethod.POST)
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
	
	@ApiMethod(name="get", path="get/{id}")
	public PatientModel getPatient(@Named("id") String id) throws NotFoundException {
		Patient p = DatastoreManager.getPatient(id);
		if (p == null) {
			throw new NotFoundException("Patient not found");
		} else {
			return p.getModel();
		}
	}
	
	@ApiMethod(name="update", path="update", httpMethod = HttpMethod.PUT)
	public PatientModel updatePatient(@Named("id") String id, @Named("name")@Nullable String name,
															  @Named("address") @Nullable String address,
															  @Named("caretakersName") @Nullable String caretakersName,
															  @Named("caretakersPhone") @Nullable String caretakersPhone) throws NotFoundException {
		
		
		Patient p = DatastoreManager.getPatient(id);
		if (p == null) {
			throw new NotFoundException("Patient not found");
		} else {
			if (name != null) {
				p.setName(name);
			}
			if (address != null) {
				p.setAddress(address);
			}
			if (caretakersName != null) {
				p.setCaretakersName(caretakersName);
			}
			if (caretakersPhone != null) {
				p.setCaretakersPhone(caretakersPhone);
			}
			
			p.commit();
			
			return p.getModel();
		}
	}
	
	@ApiMethod(name="list", path="list")
	public List<PatientModel> getAllPatients() {
		return DatastoreManager.getAllPatientModels();
	}

}
