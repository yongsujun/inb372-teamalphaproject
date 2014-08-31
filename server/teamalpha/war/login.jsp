<jsp:include page="/inc/header.jsp" /> 
<div class="container">
<div class="container">
<div class="col-md-4"></div>
	<div class="col-md-4">
		<form class="form-signin"  method="post" action="login_ok.jsp" role="form" size="50%">
		<h2 class="form-signin-heading">Please sign in</h2>
		<%if(request.getParameter("login") != null){%>
		<%if(request.getParameter("login").equals("false")){%>
		<div class="alert alert-danger" role="alert">
		Failed to login<br>
		Please check ID & Password
		</div>
		
		<% }} %>
		<input name ="id" type="email" class="form-control" placeholder="Email address" required autofocus>
		<input name = "pwd" type="password" class="form-control" placeholder="Password" required>
		<div class="checkbox">
		  <label>
		    <input type="checkbox" value="remember-me"> Remember me
		  </label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>
	</div>
<div class="col-md-4"></div>


      

   </div> </div> <!-- /container -->
    
<jsp:include page="/inc/footer.jsp" /> 