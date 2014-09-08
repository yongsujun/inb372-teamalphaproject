<%@ page import = "java.util.Date" %>
<%@ page import = "javax.servlet.http.HttpSession"%>
<%@ page import = "com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import = "com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import = "com.google.appengine.api.datastore.Entity" %>


<%   
String pwd = request.getParameter("pass");
String fname = request.getParameter("fname");
String lname = request.getParameter("lname");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
String role = request.getParameter("optionsRadios");



DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


Entity user = new Entity("User");
user.setProperty("pwd", pwd);
user.setProperty("firstName", fname);
user.setProperty("lastName", lname);
user.setProperty("email", email);
user.setProperty("phone", phone);
user.setProperty("role", role);

datastore.put(user);

//HttpSession session = request.getSession();
session.setAttribute("userid", email);
    
response.sendRedirect("/index.jsp");
   
%>
