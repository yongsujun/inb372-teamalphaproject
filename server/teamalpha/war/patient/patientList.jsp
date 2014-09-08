

<%@ include file="/inc/session.jsp"%>
<jsp:include page="/inc/header.jsp" /> 
<div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
      
      
      <div class="row">
      <div class="col-md-2">	      
	      	<jsp:include page="/inc/myMenu.jsp" />		  
      </div>
      <div class="col-md-10">
	      <h2>Patient List</h2>
	      <br/>
	      <div class="panel panel-primary">
	      <!-- Default panel contents -->
	      <div class="panel-heading">Total : 2</div>

	      <!-- Table -->
	      <table class="table table-striped">
	        <tr>
	        	<td><strong>#</strong></td>
	        	<td><strong>Patient Name</strong></td>
	        	<td><strong>Date</strong></td>
	        	<td><strong>Service</strong></td>
	        </tr>
	        <tr>
	        	<td>2</td>
	        	<td>Jane</td>
	        	<td>11/08/2014</td>
	        	<td>Not Active</td>
	        </tr>
	        <tr>
		    	<td>1</td>
		    	<td>Tom</td>
		    	<td>18/06/2014</td>
		    	<td>Active</td>
		    </tr>
	      </table>

	    </div>
	        
	        
      </div>
    </div>

      
        
    </div>

    </div> <!-- /container -->
    
<jsp:include page="/inc/footer.jsp" /> 