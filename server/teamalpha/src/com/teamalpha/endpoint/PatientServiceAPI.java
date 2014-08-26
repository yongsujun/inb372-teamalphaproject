package com.teamalpha.endpoint;

import java.util.List;

import com.teamalpha.datastore.*;
import com.teamalpha.model.*;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;

@Api(name="patient", version="v1")
public class PatientServiceAPI {
	
	@ApiMethod(name="add", path="patients", httpMethod = HttpMethod.POST)
	public AddResourceModel addPatient(PatientModel model) {
		Patient patient = DatastoreManager.createPatient();
		patient.copyModel(model);
		patient.commit();
		return new AddResourceModel(true, patient.getID());
	}
	
	@ApiMethod(name="get", path="patients/{id}", httpMethod = HttpMethod.GET)
	public PatientModel getPatient(@Named("id") String id) throws NotFoundException {
		Patient p = DatastoreManager.getPatient(id);
		if (p == null) {
			throw new NotFoundException("Patient not found");
		} else {
			return p.getModel();
		}
	}
	
	@ApiMethod(name="update", path="patients/{id}", httpMethod = HttpMethod.PUT)
	public PatientModel updatePatient(@Named("id") String id, PatientModel model) throws NotFoundException {
		Patient patient = DatastoreManager.getPatient(id);
		if (patient == null) {
			throw new NotFoundException("Patient not found");
		} else {
			patient.copyModel(model);
			patient.commit();
			return patient.getModel();
		}
	}
	
	@ApiMethod(name="list", path="patients", httpMethod = HttpMethod.GET)
	public List<PatientModel> getAllPatients() {
		return DatastoreManager.getAllPatientModels();
	}

}
