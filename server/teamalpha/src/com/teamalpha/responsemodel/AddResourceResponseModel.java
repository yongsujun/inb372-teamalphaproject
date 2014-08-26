package com.teamalpha.responsemodel;

public class AddResourceResponseModel {
	boolean success;
	String id;
	String message;
	
	public AddResourceResponseModel(boolean success, String id) {
		this.success = success;
		this.id = id;
	}

	public AddResourceResponseModel(boolean success, String id, String message) {
		this.success = success;
		this.id = id;
		this.message = message;
	}	
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
