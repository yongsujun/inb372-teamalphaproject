<%@ page import = "com.teamalpha.datastore.*" %>

<%

String id = request.getParameter("id");
String pwd1 = request.getParameter("pwd");

Caretaker caretaker = DatastoreManager.getCaretakerByEmail(id);

if(caretaker == null){
	
	//response.sendRedirect("/login.jsp");
}

if(pwd1.equals(caretaker.getPassword())){
	
	session.setAttribute("userid", caretaker.getID());
	response.sendRedirect("/index.jsp");
}else{	
	response.sendRedirect("/login.jsp?login=false");
}


%>
