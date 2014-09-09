<%@ page import = "com.teamalpha.datastore.*,java.util.*,com.teamalpha.model.*" %>

<%


String userid = (String)session.getAttribute("userid");

if ((String)request.getParameter("add") != null) {

	String email = (String)request.getParameter("add");
	int rv = DatastoreManager.addPatientCaretaker(userid, email);
}

Caretaker caretaker = DatastoreManager.getCaretaker(userid);


%>

<%@ include file="/inc/session.jsp"%>
<jsp:include page="/inc/header.jsp" /> 
<div class="container">
     
	<div class="row">
		<div class="col-md-3">
		    <div class="col-md-12">
		    	<div class="panel panel-default">
	  				<div class="panel-heading">Add Patient</div>
	  				<div class="panel-body">
	  					<form method="get" action="/patient/patientList.jsp" role="form">
	    					<div class="input-group">
	  							<span class="input-group-addon">@</span>
	  							<input type="text" class="form-control" name="add" placeholder="Patient Email">
							</div>
							<br/>
							<button type="submit">Add</button>
						</form>
	  				</div>
				</div>
		    </div>
		    
			<div class="col-md-12">
		    	<div class="panel panel-default">
	  				<div class="panel-heading">FindPatient</div>
	  				<div class="panel-body">
	  					<form method="get" action="/patient/patientList.jsp" role="form">
	    					<div class="input-group">
	  							<span class="input-group-addon">@</span>
	  							<input type="text" class="form-control" name="add" placeholder="Patient Email">
							</div>
							<br/>
							<button type="submit">Find</button>
						</form>
	  				</div>
				</div>
		    </div>	    
	    
	    </div>

	    
	    <div class="col-md-9">
	      	<div class="panel panel-primary">
	      		<!-- Default panel contents -->
	      		

	      		<!-- Table -->
	      		<table class="table table-striped">
	        		<tr>
	        			<td><strong>Name</strong></td>
	        			<td><strong>Address</strong></td>
	        			<td><strong></strong></td>
	        		</tr>
	        		<%
	        			for (PatientModel p : caretaker.getPatients()) {
	        			%>
	        				<tr>
	        					<td><%=p.getName()%></td>
	        					<td><%=p.getAddress()%></td>
	        					<td><a class="btn" href="/map.jsp?patient=<%=p.getId()%>">View Map</a></td>
	        				</tr>
	        			<%
	        			}
	        		%>
	        		
	        		
	      		</table>
	      	</div>
	    </div>
	</div>
 </div> <!-- /container -->
    
<jsp:include page="/inc/footer.jsp" /> 