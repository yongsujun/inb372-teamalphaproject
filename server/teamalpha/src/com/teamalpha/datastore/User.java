package com.teamalpha.datastore;

//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;



public class User {
	
	String pwd;
	String fname;
	String lname;
	String email;
	String phone;
	String role;
	
	
	public User(String id){
		
		
		// Get the Datastore Service
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//Filter emailFilter = new FilterPredicate("email", FilterOperator.EQUAL, session.getAttribute("userid"));
		Filter emailFilter =
				  new FilterPredicate("email", FilterOperator.EQUAL, id);
		
		// Use class Query to assemble a query
		Query q = new Query("User").setFilter(emailFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);


		for (Entity result : pq.asIterable()) {
			
		  this.pwd = (String) result.getProperty("pwd");
		  this.fname = (String) result.getProperty("firstName");
		  this.lname = (String) result.getProperty("lastName");
		  this.email = (String) result.getProperty("email");
		  this.phone = (String) result.getProperty("phone");
		  this.role = (String) result.getProperty("role");
		}		
	}
	
	public String getPwd(){
		return this.pwd;
	}
	
	public String getFname(){
		return this.fname;
	}
	public String getLname(){
		return this.lname;
	}
	public String getEmail(){
		return this.email;
	}
	public String getPhone(){
		return this.phone;
	}
	public String getRole(){
		return this.role;
	}
}
