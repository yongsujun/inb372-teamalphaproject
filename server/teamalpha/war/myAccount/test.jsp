<%@ page import = "com.google.appengine.api.datastore.DatastoreServiceFactory"%>
<%@ page import = "com.google.appengine.api.datastore.DatastoreService"%>
<%@ page import = "com.google.appengine.api.datastore.Query.Filter"%>
<%@ page import = "com.google.appengine.api.datastore.Query.FilterPredicate"%>
<%@ page import = "com.google.appengine.api.datastore.Query.FilterOperator"%>
<%@ page import = "com.google.appengine.api.datastore.Query.CompositeFilter"%>
<%@ page import = "com.google.appengine.api.datastore.Query.CompositeFilterOperator"%>
<%@ page import = "com.google.appengine.api.datastore.Query"%>
<%@ page import = "com.google.appengine.api.datastore.PreparedQuery"%>
<%@ page import = "com.google.appengine.api.datastore.Entity"%>

<%

String pwd;
String fname;
String lname;
String email;
String phone;
String role;

DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//Filter emailFilter = new FilterPredicate("email", FilterOperator.EQUAL, session.getAttribute("userid"));
Filter emailFilter =
		  new FilterPredicate("email", FilterOperator.EQUAL, "yongsu.jun@gmail.com");

// Use class Query to assemble a query
Query q = new Query("User").setFilter(emailFilter);

// Use PreparedQuery interface to retrieve results
PreparedQuery pq = datastore.prepare(q);



for (Entity result : pq.asIterable()) {
	
  pwd = (String) result.getProperty("pwd");
  fname = (String) result.getProperty("firstName");
  lname = (String) result.getProperty("lastName");
  email = (String) result.getProperty("email");
  phone = (String) result.getProperty("phone");
  role = (String) result.getProperty("role");
  
  System.out.println(pwd + ", " + fname + ", " + lname);

}		
%>
