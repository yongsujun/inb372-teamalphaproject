package com.teamalpha.endpoint;

import com.teamalpha.datastore.*;
import com.teamalpha.model.*;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.*;



@Api(name="location", version="v1")
public class LocationServiceAPI {
	
	@ApiMethod(name="get", path="locations/{id}", httpMethod = HttpMethod.GET)
	public GeoPt getLocation(@Named("id") String id) throws NotFoundException {
		Patient patient = DatastoreManager.getPatient(id);
		if (patient == null) {
			throw new NotFoundException("Patient not found");
		}
		return patient.getLocation();
	}
	
	@ApiMethod(name="update", path="locations/{id}", httpMethod = HttpMethod.PUT)
	public GeoPt updateLocation(@Named("id") String id, GeoPt location) throws NotFoundException { 
		Patient patient = DatastoreManager.getPatient(id);
		if (patient == null) {
			throw new NotFoundException("Patient not found");
		}
		patient.setLocation(location);
		patient.commit();
		return patient.getLocation();
	}
}
