package com.teamalpha.endpoint;

import java.util.List;

import com.teamalpha.datastore.*;
import com.teamalpha.model.*;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;

@Api(name="Caretaker", version="v1")
public class CaretakerServiceAPI {
	
	@ApiMethod(name="add", path="caretaker", httpMethod = HttpMethod.POST)
	public AddResourceModel addCaretaker(CaretakerModel model) {
		Caretaker caretaker = DatastoreManager.createCaretaker();
		caretaker.copyModel(model);
		caretaker.commit();
		return new AddResourceModel(true, caretaker.getID());
	}
	
	@ApiMethod(name="get", path="caretaker/{id}", httpMethod = HttpMethod.GET)
	public CaretakerModel getCaretaker(@Named("id") String id) throws NotFoundException {
		Caretaker c = DatastoreManager.getCaretaker(id);
		if (c == null) {
			throw new NotFoundException("Caretaker not found");
		} else {
			return c.getModel();
		}
	}
	
	@ApiMethod(name="update", path="caretaker/{id}", httpMethod = HttpMethod.PUT)
	public CaretakerModel updateCaretaker(@Named("id") String id, CaretakerModel model) throws NotFoundException {
		Caretaker caretaker = DatastoreManager.getCaretaker(id);
		if (caretaker == null) {
			throw new NotFoundException("Caretaker not found");
		} else {
			caretaker.copyModel(model);
			caretaker.commit();
			return caretaker.getModel();
		}
	}
	
	@ApiMethod(name="list", path="caretaker", httpMethod = HttpMethod.GET)
	public List<CaretakerModel> getAllCaretaker() {
		return DatastoreManager.getAllCaretakerModels();
	}


}
