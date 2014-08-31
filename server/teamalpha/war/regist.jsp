<jsp:include page="/inc/header.jsp" /> 
<div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
      <center>
      
      <form method="post" action="regist_ok.jsp">
      <table class="table table-condensed">
          <thead>
              <tr>
                  <th colspan="2">Enter Information Here</th>
              </tr>
          </thead>
          <tbody>
              <tr>
                  <td>First Name</td>
                  <td><input type="text" name="fname" value="" /></td>
              </tr>
              <tr>
                  <td>Last Name</td>
                  <td><input type="text" name="lname" value="" /></td>
              </tr>
              <tr>
                  <td>Email</td>
                  <td><input type="text" name="email" value="" /></td>
              </tr>
              <tr>
              <td>Phone</td>
              <td><input type="text" name="phone" value="" /></td>
          </tr>
              <tr>
                  <td>User Name</td>
                  <td><input type="radio" name="optionsRadios" id="option1" value="Carer" checked>
                  Carer</br>
                  <input type="radio" name="optionsRadios" id="option2" value="Doctor">
                  Doctor
                  </td>
              </tr>
              <tr>
                  <td>Password</td>
                  <td><input type="password" name="pass" value="" /></td>
              </tr>
              <tr>
                  <td></td>
                  <td><input type="submit" value="Submit" />   <input type="reset" value="Reset" /></td>
              </tr>
              <tr>
                  <td colspan="2">Already registered!! <a href="/login.jsp">Login Here</a></td>
              </tr>
          </tbody>
      </table>
      </form>
      
      
      </center>
    </div>
      

    </div> <!-- /container -->
    
<jsp:include page="/inc/footer.jsp" /> 