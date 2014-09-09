<%@ page import = "com.teamalpha.datastore.*,java.util.*,com.teamalpha.model.*" %>

<%


String userid = (String)session.getAttribute("userid");

if (userid == null) {	
	response.sendRedirect("/login.jsp");
}


Caretaker caretaker = DatastoreManager.getCaretaker(userid);

String patientID = request.getParameter("patient");

%>
<style>
	#map_canvas { 
    	height: 80%;
        width: 40%;
    }    
</style>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>
<script>

	var map = null;
    var patientMarker = null;
      
    function initialize() {
            
    	var myLatlng = new google.maps.LatLng( 51.50, -0.12  );
        var myOptions = {
        	zoom: 15,
            center: myLatlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        }

        map = new google.maps.Map( document.getElementById( "map_canvas" ), myOptions );
          
        patientMarker = new google.maps.Marker({
            map: map,
            icon: "images/dull-green-circle.png"
        });
            
        setInterval(updatemap, 3500);
            
    }
    
    function updatemap() {
    	var ROOT = "http://localhost:8888/_ah/api";
    	
    	gapi.client.load("location", "v1", function() {
    		gapi.client.location.get({"id":"<%=patientID%>"}).execute(function(response) {
    			var newPos = new google.maps.LatLng(response.latitude, response.longitude);
    			console.log(newPos);
    			map.panTo(newPos);
    			patientMarker.setPosition(newPos);
    		});
    	}, ROOT);
    	
    }
    
    google.maps.event.addDomListener(window, "load", initialize);
</script>

<%@ include file="/inc/session.jsp"%>
<jsp:include page="/inc/header.jsp" /> 

<div class="container">

<div id="map_canvas"></div>

</div> <!-- /container -->
    
<jsp:include page="/inc/footer.jsp" /> 