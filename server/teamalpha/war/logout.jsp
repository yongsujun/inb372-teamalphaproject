<%
session.setAttribute("userid", "");
session.invalidate(); 
response.sendRedirect("/index.jsp");
%>
