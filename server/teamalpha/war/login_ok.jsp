<%@ page import = "com.teamalpha.datastore.User" %>

<%

String id = request.getParameter("id");
String pwd1 = request.getParameter("pwd");

User user = new User(id);
String pwd2 = user.getPwd();

if(user.getPwd() == null){
	
	response.sendRedirect("/login.jsp");
}

if(pwd1.equals(pwd2)){
	
	session.setAttribute("userid", id);
	response.sendRedirect("/index.jsp");
}else{	
	%>
	<script type="text/javascript">
    alert("Login Failed!! Please check your ID and Password!!");
    </script>
    <%
	response.sendRedirect("/login.jsp?login=false");
}


%>
