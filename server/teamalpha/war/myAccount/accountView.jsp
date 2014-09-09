<!DOCTYPE HTML>
<%@ page import = "com.teamalpha.datastore.*" %>

<%@ include file="/inc/session.jsp"%>
<%

String email = (String)session.getAttribute("userid");

Caretaker caretaker = DatastoreManager.getCaretaker((String)session.getAttribute("userid"));

%>
<jsp:include page="/inc/header.jsp" /> 
<div class="container">

      <!-- Main component for a primary marketing message or call to action -->
     
      
	<div class="row">
        <div class="col-md-9">
        	<div class="panel panel-primary">
	      		<!-- Default panel contents -->
	      		<div class="panel-heading">My Account Details</div>

	      		<!-- Table -->
	      		<table class="table table-striped">
	        		<tr>
	        			<td><strong>Name</strong></td>
	        			<td><%=caretaker.getName()%></td>	        	
	        		</tr>
	        		<tr>
	        			<td><strong>Address</strong></td>
	        			<td><%=caretaker.getAddress()%></td>
	        		</tr>
	        		<tr>
	        			<td><strong>Email</strong></td>
	        			<td><%=caretaker.getEmail()%></td>	        	
	        		</tr>
	        		<tr>
		    			<td><strong>Phone</strong></td>
		    			<td><%=caretaker.getPhoneNumber()%></td>	        	
		    		</tr>
	      		</table>
	    	</div>
	        <p>
	        	<a class="btn btn-lg btn-primary" href="/myAccount/accountUpdate.jsp" role="button">Update &raquo;</a>
	      	</p>
        </div>
 	</div> <!-- /container -->
<jsp:include page="/inc/footer.jsp" /> 