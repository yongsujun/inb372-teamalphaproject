<%
if((String)session.getAttribute("userid") == null || (String)session.getAttribute("userid") == ""){

	response.sendRedirect("/login.jsp");
	//out.flush(); // Send out whatever hasn't been sent out yet.
	//out.close(); // Close the stream. Future calls will fail.
	return; // Return from the JSP servelet handler.
}
%>