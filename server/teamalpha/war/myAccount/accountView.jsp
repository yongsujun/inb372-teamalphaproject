
<%@ page import = "com.teamalpha.datastore.User" %>

<%@ include file="/inc/session.jsp"%>
<%
User user = new User((String)session.getAttribute("userid"));
String email = (String)session.getAttribute("userid");
//Patient pt = new Patient();
//String fullname = user.getFname();
%>
<jsp:include page="/inc/header.jsp" /> 
<div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
      
      
      <div class="row">
        <div class="col-md-2">       
      	<jsp:include page="/inc/myMenu.jsp" />      
        </div>
        <div class="col-md-10">
        <h2>My Account</h2>
        <br/>
        <div class="panel panel-primary">
	      <!-- Default panel contents -->
	      <div class="panel-heading">My Account Details</div>

	      <!-- Table -->
	      <table class="table table-striped">
	        <tr>
	        	<td><strong>Name</strong></td>
	        	<td><%=user.getFname()%> <%=user.getLname()%></td>	        	
	        </tr>
	        <tr>
	        	<td><strong>Email</strong></td>
	        	<td><%=user.getEmail()%></td>	        	
	        </tr>
	        <tr>
		    	<td><strong>Phoen</strong></td>
		    	<td><%=user.getPhone()%></td>	        	
		    </tr>
		    <tr>
		    	<td><strong>Role</strong></td>
		    	<td><%=user.getRole()%></td>	        	
		    </tr>
		    
	      </table>

	    </div>
	        
	        
   
	        
	        <p>
	        <a class="btn btn-lg btn-primary" href="/myAccount/accountUpdate.jsp" role="button">Update &raquo;</a>
	      </p>
        </div>
      </div>

      
        
    </div>

    </div> <!-- /container -->
    
<jsp:include page="/inc/footer.jsp" /> 