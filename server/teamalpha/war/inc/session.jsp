<%
if((String)session.getAttribute("userid") == null || (String)session.getAttribute("userid") == ""){

	response.sendRedirect("/login.jsp");
}	
%>