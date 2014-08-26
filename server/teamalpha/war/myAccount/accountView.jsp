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
	        	<td>Tom Smith</td>	        	
	        </tr>
	        <tr>
	        	<td><strong>Email</strong></td>
	        	<td>tom@gmail.com</td>	        	
	        </tr>
	        <tr>
		    	<td><strong>Address</strong></td>
		    	<td>123 Queen St, Brisbane QLD 4000</td>	        	
		    </tr>
		    <tr>
		    	<td><strong>Phone</strong></td>
		    	<td>07 3333 3333</td>	        	
		    </tr>
		    <tr>
		    	<td><strong>####</strong></td>
		    	<td>######</td>	        	
		    </tr>
		    <tr>
		    	<td><strong>######</strong></td>
		    	<td>########</td>	        	
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